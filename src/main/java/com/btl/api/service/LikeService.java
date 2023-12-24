package com.btl.api.service;

import com.btl.api.model.Like;
import com.btl.api.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Transactional(readOnly = true)
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    @Transactional
    public Like createLike(Like like)  {
        if (likeRepository.findByUserIdAndPostId(like.getUserId(), like.getPostId()) != null)
            throw new RuntimeException("Like already exists");

        return likeRepository.save(like);
    }

    @Transactional
    public void deleteLike(Long likeId) {
        likeRepository.findById(likeId).orElseThrow(() -> new RuntimeException("Like not found"));
        likeRepository.deleteById(likeId);
    }

    @Transactional(readOnly = true)
    public Like getLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElseThrow(() -> new RuntimeException("Like not found"));
    }

    @Transactional(readOnly = true)
    public boolean isLiked(Long userId, Long postId) {
        return likeRepository.findByUserIdAndPostId(userId, postId) != null;
    }

    @Transactional(readOnly = true)
    public List<Like> getLikes(Long postId) {
        return likeRepository.findByPostId(postId);
    }

    @Transactional(readOnly = true)
    public List<Like> getLikesByUserId(Long userId) {
        return likeRepository.findByUserId(userId);
    }

}
