package com.learning.quizapp.controllers;

import com.learning.quizapp.services.impl.AIChatService;
import org.springframework.ai.chat.model.ChatResponse;
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
    public ResponseEntity<String> generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {

        String response =  aiChatService.queryAiService(message, "Generate only 10 questions and Format the response as a valid JSON in below format [{\"questionTitle\": \"What is a constructor?\", \"option1\": \"A member of a class\", \"option2\": \"A loop in Python\", \"option3\": \"A data type\", \"option4\": \"A special method\", \"correctAnswer\": \"A special method\", \"difficultyLevel\": \"Medium\", \"category\": \"java\"}] ");
        return ResponseEntity.ok(response);
    }

}