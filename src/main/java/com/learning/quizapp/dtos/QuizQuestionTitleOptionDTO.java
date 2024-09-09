package com.learning.quizapp.dtos;

import com.learning.quizapp.model.Question;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
public class QuizQuestionTitleOptionDTO {
    private int id;
    private String title;
    private String category;

    @ManyToMany
    private List<QuestionTitleOptionDTO> question;
}
