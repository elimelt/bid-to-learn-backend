package com.btl.api.controller;

import com.btl.api.dto.PostRequest;
import com.btl.api.model.Post;
import com.btl.api.model.User;
import com.btl.api.repository.UserRepository;
import com.btl.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId).orElse(null);
    }

    @GetMapping("/user/{userId}")
    public List<Post> getPostByUserId(@PathVariable Long userId) {
        return postService.getPostsByUserId(userId);
    }



    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post post) {
        post.setPostId(postId);
        return postService.updatePost(post);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

}
