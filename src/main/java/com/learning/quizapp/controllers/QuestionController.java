package com.learning.quizapp.controllers;

import com.learning.quizapp.model.Question;
import com.learning.quizapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok()
                .body(questionService.getAllQuestions());
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return ResponseEntity.ok()
                .body(questionService.getQuestionsByCategory(category));
    }

    @PutMapping
    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok()
                .body(questionService.addQuestion(question));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Question> updateQuestionIfNotNull(@PathVariable int id, @RequestBody Question question) {
        return ResponseEntity.ok()
                .body(questionService.updateQuestionIfNotNull(id, question));
    }

    @PutMapping("{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable int id, @RequestBody Question question) {
        return ResponseEntity.ok()
                .body(questionService.updateQuestion(id, question));
    }

    @GetMapping("{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable int id) {
        return ResponseEntity.ok()
                .body(questionService.getQuestionById(id));
    }
}
