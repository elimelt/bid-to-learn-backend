package com.btl.api.controller;

import com.btl.api.pojo.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
@Controller
public class MessageController {


    @MessageMapping("/msg")
    @SendTo("/topic/test")
    public ChatMessage test(ChatMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ChatMessage("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}