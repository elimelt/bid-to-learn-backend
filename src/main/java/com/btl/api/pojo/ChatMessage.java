package com.btl.api.pojo;

public class ChatMessage {
    private String content;
    private Long userId;

    public ChatMessage() {
    }

    public ChatMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
