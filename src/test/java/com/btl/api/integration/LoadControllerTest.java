package com.btl.api;

import com.btl.api.controller.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class LoadControllerTest {

    @Autowired
    private LikeController likeController;

    @Autowired
    private RecommendationController recommendationController;

    @Autowired
    private PreferenceController preferenceController;

    @Autowired
    private UserController userController;

    @Autowired
    private PostController postController;

    @Autowired
    private TopicController topicController;


    @Test
    void contextLoads() {
        assertThat(likeController).isNotNull();
        assertThat(recommendationController).isNotNull();
        assertThat(preferenceController).isNotNull();
        assertThat(userController).isNotNull();
        assertThat(postController).isNotNull();
        assertThat(topicController).isNotNull();
    }

}
