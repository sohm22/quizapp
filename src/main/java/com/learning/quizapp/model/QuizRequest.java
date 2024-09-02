package com.learning.quizapp.model;

import lombok.Data;

@Data
public class QuizRequest {
    private String title;
    private String category;
    private int numberOfQuestions;
}
