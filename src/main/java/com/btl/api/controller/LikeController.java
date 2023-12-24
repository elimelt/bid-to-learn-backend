package com.btl.api.controller;

import com.btl.api.service.LikeService;
import com.btl.api.model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping
    public ResponseEntity<List<Like>> getAllLikes() {
        List<Like> likes = likeService.getAllLikes();
        return ResponseEntity.ok(likes);
    }

    @GetMapping("/{likeId}")
    public ResponseEntity<Like> getLikeById(@PathVariable Long likeId) {
        try {
            Like like = likeService.getLikeById(likeId);
            return ResponseEntity.ok(like);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Like> createLike(@RequestBody Like like) {
        try {
            Like createdLike = likeService.createLike(like);
            return ResponseEntity.ok(createdLike);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long likeId) {
        try {
            likeService.deleteLike(likeId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}