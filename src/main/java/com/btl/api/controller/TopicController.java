package com.btl.api.controller;

import com.btl.api.model.Topic;
import com.btl.api.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping("/{topicId}")
    public Topic getTopicById(@PathVariable Long topicId) {
        return topicService.getTopicById(topicId).orElse(null);
    }

    @PostMapping
    public Topic createTopic(@RequestBody Topic topic) {
        return topicService.createTopic(topic);
    }

    @PutMapping("/{topicId}")
    public Topic updateTopic(@PathVariable Long topicId, @RequestBody Topic topic) {
        topic.setTopicId(topicId);
        return topicService.updateTopic(topic);
    }

    @DeleteMapping("/{topicId}")
    public void deleteTopic(@PathVariable Long topicId) {
        topicService.deleteTopic(topicId);
    }

    // Additional methods as needed
}
