package com.learning.quizapp.dtos;

import lombok.Data;

@Data
public class FeedbackDTO {
    private int id;
    private boolean isCorrect;
    private String correctAnswer;

    public void isCorrect(boolean correct) {
        isCorrect = correct;
    }
}