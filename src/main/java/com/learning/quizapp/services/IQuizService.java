package com.learning.quizapp.services;

import com.learning.quizapp.dtos.*;
import com.learning.quizapp.model.Quiz;

import java.util.List;

public interface IQuizService {

    // create quiz
    Quiz createQuiz(QuizRequestDTO quizRequest);

    // get Quiz
    QuizQuestionTitleOptionDTO getQuizById(int id);

    List<CategoryQuizCountDTO> getCategoriesWithQuizCount();

    List<QuizIdTitleDTO> getQuizzesMetaDataByCategory(String category);

    SubmitQuizResponseDTO evaluateQuiz(int id, SubmitQuizRequestDTO submitQuizRequestDTO);
}
