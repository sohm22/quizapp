package com.learning.quizapp.services;

import com.learning.quizapp.dtos.CategoryQuizCountDto;
import com.learning.quizapp.model.Quiz;
import com.learning.quizapp.model.QuizRequest;

import java.util.List;

public interface IQuizService {

    // create quiz
    Quiz createQuiz(QuizRequest quizRequest);

    // get Quiz
    Quiz getQuizById(int id);

    List<CategoryQuizCountDto> getCategoriesWithQuizCount();
}
