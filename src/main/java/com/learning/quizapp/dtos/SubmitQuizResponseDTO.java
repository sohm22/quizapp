package com.learning.quizapp.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SubmitQuizResponseDTO {
    private int totalQuestions;
    private int score;
    private List<FeedbackDTO> feedbackDTOList;
}