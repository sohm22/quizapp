package com.learning.quizapp.services;

import com.learning.quizapp.dao.QuestionDao;
import com.learning.quizapp.exceptions.ResourceNotFoundException;
import com.learning.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        List<Question> questionsByCategory = questionDao.findByCategory(category);
        if (questionsByCategory.isEmpty()) {
            throw new ResourceNotFoundException("Category Not found");
        }
        return questionDao.findByCategory(category);
    }

    public Question addQuestion(Question question) {
        return questionDao.save(question);
    }

    public Question updateQuestionIfNotNull(int id, Question updatedQuestion) {
        Optional<Question> optionalQuestion = questionDao.findById(id);
        if (optionalQuestion.isPresent()) {
            Question existingQuestion = optionalQuestion.get();
            updateIfNotNull(existingQuestion::setQuestionTitle, updatedQuestion.getQuestionTitle());
            updateIfNotNull(existingQuestion::setOption1, updatedQuestion.getOption1());
            updateIfNotNull(existingQuestion::setOption2, updatedQuestion.getOption2());
            updateIfNotNull(existingQuestion::setOption3, updatedQuestion.getOption3());
            updateIfNotNull(existingQuestion::setOption4, updatedQuestion.getOption4());
            updateIfNotNull(existingQuestion::setCorrectAnswer, updatedQuestion.getCorrectAnswer());
            updateIfNotNull(existingQuestion::setDifficultyLevel, updatedQuestion.getDifficultyLevel());
            updateIfNotNull(existingQuestion::setCategory, updatedQuestion.getCategory());
            return questionDao.save(existingQuestion);
        }
        throw new ResourceNotFoundException("Question with id " + id + " not found");
    }

    private <T> void updateIfNotNull(Consumer<T> setter, T value) {
        Optional.ofNullable(value).ifPresent(setter);
    }

    public Question getQuestionById(int id) {
        Optional<Question> question = questionDao.findById(id);
        if (question.isPresent()) {
            return question.get();
        }
        throw new ResourceNotFoundException("Question with id " + id + " not found");
    }

    public Question updateQuestion(int id, Question question) {
        Optional<Question> optionalQuestion = questionDao.findById(id);
        if (optionalQuestion.isPresent()) {
            question.setId(id);
            return questionDao.save(question);
        }
        throw new ResourceNotFoundException("Question with id " + id + " not found");
    }
}
