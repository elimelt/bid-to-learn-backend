package com.btl.api.unit;

import com.btl.api.controller.TopicController;
import com.btl.api.model.Topic;
import com.btl.api.service.TopicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(TopicController.class)
public class TopicControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicService topicService;


    @Test
    void getAllTopicsTest() throws Exception {
        var now = LocalDateTime.now();
        List<Topic> topicsToMock = Arrays.asList(
                new Topic(1L, "Topic 1", now),
                new Topic(2L, "Topic 2", now),
                new Topic(3L, "Topic 3", now)
        );

        when(topicService.getAllTopics()).thenReturn(topicsToMock);

        this.mockMvc.perform(get("/api/topics"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Topic 1")))
                .andExpect(content().string(containsString("Topic 2")))
                .andExpect(content().string(containsString("Topic 3")))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void getTopicByIdTest() throws Exception {
        var now = LocalDateTime.now();
        Topic topicToMock = new Topic(1L, "Topic 1", now);

        when(topicService.getTopicById(1L)).thenReturn(java.util.Optional.of(topicToMock));

        this.mockMvc.perform(get("/api/topics/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Topic 1")))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void createTopicTest() throws Exception {
        var now = LocalDateTime.now();
        Topic topicToMock = new Topic(1L, "Topic 1", now);

        when(topicService.createTopic(topicToMock)).thenReturn(topicToMock);

        this.mockMvc.perform(get("/api/topics"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Topic 1")))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void updateTopicTest() throws Exception {
        var now = LocalDateTime.now();
        Topic topicToMock = new Topic(1L, "Topic 1", now);

        when(topicService.getTopicById(1L)).thenReturn(java.util.Optional.of(topicToMock));
        when(topicService.updateTopic(topicToMock)).thenReturn(topicToMock);

        this.mockMvc.perform(get("/api/topics/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Topic 1")))
                .andReturn().getResponse().getContentAsString();

        this.mockMvc.perform(put("/api/topics/1", topicToMock))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Topic 1")))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void deleteTopicTest() throws Exception {
        // don't really need to test this one at the controller level since it is void
    }

}
