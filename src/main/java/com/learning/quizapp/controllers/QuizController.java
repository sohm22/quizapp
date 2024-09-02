package com.learning.quizapp.controllers;

import com.learning.quizapp.dtos.CategoryQuizCountDto;
import com.learning.quizapp.model.Quiz;
import com.learning.quizapp.model.QuizRequest;
import com.learning.quizapp.services.IQuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private final IQuizService quizService;

    public QuizController(IQuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizRequest quizRequest) throws URISyntaxException {
        // Logic to create a quiz and return the response
        Quiz savedQuiz = quizService.createQuiz(quizRequest);

        // Build the URI for the newly created resource
        URI location = new URI("/quiz/" + savedQuiz.getId());
        return ResponseEntity.created(location)
                .body(savedQuiz);
    }

    @GetMapping("{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable int id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @GetMapping("categories/count")
    public ResponseEntity<List<CategoryQuizCountDto>> getCategories() {
        return ResponseEntity.ok(quizService.getCategoriesWithQuizCount());
    }
}
