package com.learning.quizapp.controllers;

import com.learning.quizapp.services.impl.AIChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("quiz")
@RestController
public class AIChatController {

    private final AIChatService aiChatService;

    public AIChatController(AIChatService aiChatService) {
        this.aiChatService = aiChatService;
    }

    @GetMapping("/ai/generate")
    public ResponseEntity<String> generate(@RequestParam(value = "message") String message) {
        String response =  aiChatService.queryAiService(message);
        return ResponseEntity.ok(response);
    }
}