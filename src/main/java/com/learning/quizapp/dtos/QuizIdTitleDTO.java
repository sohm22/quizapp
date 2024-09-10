package com.learning.quizapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class QuizIdTitleDTO {
    private int id;
    private String title;
}