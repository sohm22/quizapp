package com.learning.quizapp.dtos;

import lombok.Data;

@Data
public class QuizRequestDTO {
    private String title;
    private String category;
    private int numberOfQuestions;
}
