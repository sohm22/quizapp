package com.learning.quizapp.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SubmitQuizRequestDTO {
    private List<QuestionAnswerDTO> answers;
}