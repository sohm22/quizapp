package com.learning.quizapp.services.impl;

import com.learning.quizapp.dao.QuizDao;
import com.learning.quizapp.dtos.CategoryQuizCountDto;
import com.learning.quizapp.exceptions.ResourceNotFoundException;
import com.learning.quizapp.model.Question;
import com.learning.quizapp.model.Quiz;
import com.learning.quizapp.model.QuizRequest;
import com.learning.quizapp.services.IQuestionService;
import com.learning.quizapp.services.IQuizService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements IQuizService {

    private final QuizDao quizDao;
    private final IQuestionService questionService;

    public QuizServiceImpl(QuizDao quizDao, IQuestionService questionService) {
        this.quizDao = quizDao;
        this.questionService = questionService;
    }

    @Override
    public Quiz getQuizById(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        return quiz.orElseThrow(() ->new ResourceNotFoundException("quiz with id " + id + " not found"));
    }

    public List<CategoryQuizCountDto> getCategoriesWithQuizCount() {
        // Fetch raw results from the DAO
        List<Object[]> results = quizDao.findCategoriesWithQuizCount();

        // Map results to a list of CategoryQuizCountDto
        List<CategoryQuizCountDto> categoryQuizCounts = new ArrayList<>();
        for (Object[] result : results) {
            String category = (String) result[0];
            Long count = (Long) result[1]; // COUNT returns Long in JPA
            categoryQuizCounts.add(new CategoryQuizCountDto(category, count));
        }

        return categoryQuizCounts;
    }

    @Override
    public Quiz createQuiz(QuizRequest quizRequest) {
        List<Question> questions = selectRandomItems(
                questionService.getQuestionsByCategory(
                        quizRequest.getCategory()), quizRequest.getNumberOfQuestions()
        );
        Quiz quiz = new Quiz();
        quiz.setTitle(quizRequest.getTitle());
        quiz.setCategory(quizRequest.getCategory());
        quiz.setQuestion(questions);
        return quizDao.save(quiz);
    }

    private static <T> List<T> selectRandomItems(List<T> list, int numberOfItems) {
        // Shuffle the list to randomize the order
        List<T> shuffledList = new ArrayList<>(list);
        Collections.shuffle(shuffledList);

        // Return the first 'numberOfItems' elements from the shuffled list
        return shuffledList.subList(0, Math.min(numberOfItems, shuffledList.size()));
    }
}
