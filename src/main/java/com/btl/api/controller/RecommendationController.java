package com.btl.api.controller;

import com.btl.api.model.Post;
import com.btl.api.service.ReccomendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")

public class RecommendationController {

    @Autowired
    private ReccomendationService reccomendationService;

    @GetMapping("/{userId}/likes")
    public List<Post> getLikesReccomendation(@PathVariable Long userId) {
        return reccomendationService.getReccomendationsBasedOnLikes(userId);
    }

    @GetMapping("/{userId}/similar")
    public List<Post> getSimilarUsersReccomendation(@PathVariable Long userId) {
        return reccomendationService.getReccomendationsBasedOnSimilarUsers(userId);
    }

    @GetMapping("/{userId}/preferences")
    public List<Post> getPreferencesReccomendation(@PathVariable Long userId) {
        return reccomendationService.getReccomendationsBasedOnPreferences(userId);
    }
}
