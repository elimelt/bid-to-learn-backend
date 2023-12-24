package com.btl.api.dto;

public class PostRequest {
    private Long userId;
    private String content;

    // Constructors, getters, setters, etc.

    public PostRequest() {
    }

    public PostRequest(Long userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
