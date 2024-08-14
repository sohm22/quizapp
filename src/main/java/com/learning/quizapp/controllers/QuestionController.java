package com.learning.quizapp.controllers;

import com.learning.quizapp.model.Question;
import com.learning.quizapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PutMapping
    @PostMapping
    public Question addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PatchMapping("{id}")
    public Optional<Question> updateQuestionIfNotNull(@PathVariable int id, @RequestBody Question question) {
        return questionService.updateQuestionIfNotNull(id, question);
    }

    @PutMapping("{id}")
    public Optional<Question> updateQuestion(@PathVariable int id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }

    @GetMapping("{id}")
    public Optional<Question> getQuestion(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }
}
