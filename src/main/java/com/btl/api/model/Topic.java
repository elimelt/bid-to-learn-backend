package com.btl.api.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    @Column(nullable = false)
    private String topicName;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Topic() { }
    public Topic(Long topicId, String topicName, LocalDateTime createdAt) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.createdAt = createdAt;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static class Builder {
        private Long topicId;
        private String topicName;
        private LocalDateTime createdAt;

        public Builder topicId(Long topicId) {
            this.topicId = topicId;
            return this;
        }

        public Builder topicName(String topicName) {
            this.topicName = topicName;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Topic build() {
            return new Topic(topicId, topicName, createdAt);
        }
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", topicName='" + topicName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
