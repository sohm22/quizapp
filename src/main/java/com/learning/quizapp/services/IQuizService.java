package com.learning.quizapp.services;

import com.learning.quizapp.dtos.CategoryQuizCountDTO;
import com.learning.quizapp.dtos.QuizIdTitleDTO;
import com.learning.quizapp.dtos.QuizQuestionTitleOptionDTO;
import com.learning.quizapp.model.Quiz;
import com.learning.quizapp.model.QuizRequest;

import java.util.List;

public interface IQuizService {

    // create quiz
    Quiz createQuiz(QuizRequest quizRequest);

    // get Quiz
    QuizQuestionTitleOptionDTO getQuizById(int id);

    List<CategoryQuizCountDTO> getCategoriesWithQuizCount();

    List<QuizIdTitleDTO> getQuizzesMetaDataByCategory(String category);
}
