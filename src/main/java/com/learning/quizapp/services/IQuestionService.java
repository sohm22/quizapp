package com.learning.quizapp.services;

import com.learning.quizapp.model.Question;
import java.util.List;

public interface IQuestionService {

    List<Question> getAllQuestions();

    List<Question> getQuestionsByCategory(String category);

    Question addQuestion(Question question);

    Question updateQuestionIfNotNull(int id, Question updatedQuestion);

    Question getQuestionById(int id);

    Question updateQuestion(int id, Question question);
}
