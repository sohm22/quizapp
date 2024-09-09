package com.learning.quizapp.services;

import com.learning.quizapp.dtos.CategoryQuizCountDTO;
import com.learning.quizapp.dtos.QuizIdTitleDTO;
import com.learning.quizapp.dtos.QuizQuestionTitleOptionDTO;
import com.learning.quizapp.dtos.QuizRequestDTO;
import com.learning.quizapp.model.Quiz;

import java.util.List;

public interface IQuizService {

    // create quiz
    Quiz createQuiz(QuizRequestDTO quizRequest);

    // get Quiz
    QuizQuestionTitleOptionDTO getQuizById(int id);

    List<CategoryQuizCountDTO> getCategoriesWithQuizCount();

    List<QuizIdTitleDTO> getQuizzesMetaDataByCategory(String category);
}
