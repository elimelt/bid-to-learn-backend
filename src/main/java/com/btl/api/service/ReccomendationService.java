package com.btl.api.service;

import com.btl.api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ReccomendationService {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private PreferenceService preferenceService;

    @Autowired
    private LikeService likeService;

    private long similarityMatrix[][] = null;

    private Map<Long, Long> userIndex = null;

    private Map<Long, Long> postIndex = null;

    private List<User> users = null;

    private List<Post> posts = null;

    private LocalDateTime lastUpdated = null;


    public List<Post> getReccomendationsBasedOnLikes(Long userId) {
        checkRefresh();

        User user = userService.getUserById(userId);

        Set<Long> userLikes = likeService.getLikesByUserId(userId).parallelStream()
                .map(Like::getPostId).collect(Collectors.toSet());

        return posts.parallelStream()
                .filter(post -> !userLikes.contains(post.getPostId())) // Remove posts liked by the user
                .map(post -> { // Calculate score for each post
                    long score = 0;
                    for (Like like : likeService.getLikesByUserId(post.getUserId())) {
                        if (userLikes.contains(like.getPostId())) {
                            score += 1;
                        }
                    }
                    return Pair.of(post, score);
                })
                .sorted((p1, p2) -> p2.getSecond().compareTo(p1.getSecond()))
                .map(Pair::getFirst)
                .collect(Collectors.toList());
    }
    public List<Post> getReccomendationsBasedOnSimilarUsers(Long userId) {
        checkRefresh();

        User user = userService.getUserById(userId);

        long[] userSimilarity = new long[users.size()];

        for (int i = 0; i < users.size(); i++) {
            userSimilarity[i] = similarityMatrix[userIndex.get(userId).intValue()][i];
        }

        return posts.parallelStream()
                .filter(post -> !post.getUserId().equals(userId)) // Remove posts by the user
                .map(post -> { // Calculate score for each post
                    long score = 0;
                    for (int i = 0; i < users.size(); i++) {
                        if (userSimilarity[i] > 0) {
                            score += userSimilarity[i] * similarityMatrix[i][userIndex.get(post.getUserId()).intValue()]; // Similarity between user[i] and post author
                        }
                    }
                    return Pair.of(post, score);
                })
                .sorted((p1, p2) -> p2.getSecond().compareTo(p1.getSecond()))
                .map(Pair::getFirst)
                .collect(Collectors.toList());
    }
    public List<Post> getReccomendationsBasedOnPreferences(Long userId) {
        checkRefresh();

        User user = userService.getUserById(userId);

        Map<Long, Long> userPreferences =  new HashMap<>();

        for (Preference preference : preferenceService.getPreferencesByUserId(userId)) {
            userPreferences .put(preference.getPreferenceId(), preference.getScore());
        }

        return posts.parallelStream()
                .filter(post -> !userPreferences.containsKey(post.getPostId()))
                .map(post -> {
                    long score = 0;
                    for (Preference preference : preferenceService.getPreferencesByUserId(post.getUserId())) {
                        if (userPreferences.containsKey(preference.getPreferenceId())) {
                            score += userPreferences.get(preference.getPreferenceId()) * preference.getScore();
                        }
                    }
                    return Pair.of(post, score);
                })
                .sorted((p1, p2) -> p2.getSecond().compareTo(p1.getSecond()))
                .map(Pair::getFirst)
                .collect(Collectors.toList());
    }


    private long[][] calculateSimilarityMatrix() {
        List<User> users = userService.getAllUsers();
        int n = users.size();
        long[][]  matrix = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                matrix[i][j] = calculateSimilarity(users.get(i), users.get(j));
                matrix[j][i] = matrix[i][j];
            }
        }

        return matrix;
    }

    private long calculateSimilarity(User u1, User u2) {
        Set<Long> u1Posts = postService.getPostsByUserId(u1.getUserId()).parallelStream()
                .map(Post::getPostId).collect(Collectors.toSet());
        Set<Long> u2Posts = postService.getPostsByUserId(u2.getUserId()).parallelStream()
                .map(Post::getPostId).collect(Collectors.toSet());

        Set<Long> u1Likes = likeService.getLikesByUserId(u1.getUserId()).parallelStream()
                .map(Like::getPostId).collect(Collectors.toSet());
        Set<Long> u2Likes = likeService.getLikesByUserId(u2.getUserId()).parallelStream()
                .map(Like::getPostId).collect(Collectors.toSet());

        Set<Long> u1Preferences = preferenceService.getPreferencesByUserId(u1.getUserId()).parallelStream()
                .map(Preference::getPreferenceId).collect(Collectors.toSet());
        Set<Long> u2Preferences = preferenceService.getPreferencesByUserId(u2.getUserId()).parallelStream()
                .map(Preference::getPreferenceId).collect(Collectors.toSet());

        long similarity = 0;

        // Posts
        similarity += u1Posts.parallelStream().filter(u2Posts::contains).count();

        // Likes
        similarity += u1Likes.parallelStream().filter(u2Likes::contains).count();

        // Preferences
        similarity += u1Preferences.parallelStream().filter(u2Preferences::contains).count();

        return similarity;
    }

    private void checkRefresh() {
        if (similarityMatrix == null ||
                lastUpdated == null ||
                userIndex == null ||
                postIndex == null ||
                users == null ||
                lastUpdated.plusDays(1).isBefore(LocalDateTime.now())) {
            similarityMatrix = calculateSimilarityMatrix();
            lastUpdated = LocalDateTime.now();
            users = userService.getAllUsers();
            posts = postService.getAllPosts();
            int n = users.size();
            userIndex = new HashMap<>();
            for (int i = 0; i < n; i++)
                userIndex.put(users.get(i).getUserId(), (long) i);
            postIndex = new HashMap<>();
            for (int i = 0; i < posts.size(); i++)
                postIndex.put(posts.get(i).getPostId(), (long) i);
        }
    }
}
