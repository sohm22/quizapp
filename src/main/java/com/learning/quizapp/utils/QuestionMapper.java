package com.learning.quizapp.utils;

import com.learning.quizapp.dtos.QuestionTitleOptionDTO;
import com.learning.quizapp.model.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionMapper {

    public static QuestionTitleOptionDTO toQuestionTitleOptionDTO(Question question) {
        QuestionTitleOptionDTO dto = new QuestionTitleOptionDTO();
        dto.setId(question.getId());
        dto.setQuestionTitle(question.getQuestionTitle());
        dto.setOption1(question.getOption1());
        dto.setOption2(question.getOption2());
        dto.setOption3(question.getOption3());
        dto.setOption4(question.getOption4());
        return dto;
    }

    public static List<QuestionTitleOptionDTO> toListOfDTO(List<Question> questions) {
        return questions.stream().map(QuestionMapper::toQuestionTitleOptionDTO).collect(Collectors.toList());
    }
}
