package com.learning.quizapp.services.impl;

import com.learning.quizapp.dao.QuizDao;
import com.learning.quizapp.dtos.CategoryQuizCountDTO;
import com.learning.quizapp.dtos.FeedbackDTO;
import com.learning.quizapp.dtos.QuestionAnswerDTO;
import com.learning.quizapp.dtos.QuestionTitleOptionDTO;
import com.learning.quizapp.dtos.QuizIdTitleDTO;
import com.learning.quizapp.dtos.QuizQuestionTitleOptionDTO;
import com.learning.quizapp.dtos.QuizRequestDTO;
import com.learning.quizapp.dtos.SubmitQuizRequestDTO;
import com.learning.quizapp.dtos.SubmitQuizResponseDTO;
import com.learning.quizapp.exceptions.ResourceNotFoundException;
import com.learning.quizapp.model.Question;
import com.learning.quizapp.model.Quiz;
import com.learning.quizapp.services.IQuestionService;
import com.learning.quizapp.services.IQuizService;
import com.learning.quizapp.utils.QuestionMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public QuizQuestionTitleOptionDTO getQuizById(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        Quiz quizFound = quiz.orElseThrow(() -> new ResourceNotFoundException("quiz with id " + id + " not found"));

        //convert List of questionDAO to List of toQuestionTitleOptionDTO
        List<QuestionTitleOptionDTO> questionTitleOptionDTOList = QuestionMapper.toListOfDTO(quizFound.getQuestion());

        QuizQuestionTitleOptionDTO quizDTO = new QuizQuestionTitleOptionDTO();
        quizDTO.setId(quizFound.getId());
        quizDTO.setTitle(quizFound.getTitle());
        quizDTO.setCategory(quizFound.getCategory());
        quizDTO.setQuestion(questionTitleOptionDTOList);

        return quizDTO;
    }

    @Override
    public List<CategoryQuizCountDTO> getCategoriesWithQuizCount() {
        // Fetch raw results from the DAO
        List<Object[]> results = quizDao.findCategoriesWithQuizCount();

        // Map results to a list of CategoryQuizCountDto
        List<CategoryQuizCountDTO> categoryQuizCounts = new ArrayList<>();
        for (Object[] result : results) {
            String category = (String) result[0];
            Long count = (Long) result[1]; // COUNT returns Long in JPA
            categoryQuizCounts.add(new CategoryQuizCountDTO(category, count));
        }

        return categoryQuizCounts;
    }

    @Override
    public Quiz createQuiz(QuizRequestDTO quizRequest) {
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

    @Override
    public List<QuizIdTitleDTO> getQuizzesMetaDataByCategory(String category) {
        // Use the custom query method to fetch only id and title
        return quizDao.findQuizByCategory(category);
    }

    @Override
    public SubmitQuizResponseDTO evaluateQuiz(int id, SubmitQuizRequestDTO submitQuizRequestDTO) {
        Optional<Quiz> optionalQuiz = quizDao.findById(id);
        Quiz quiz = optionalQuiz.orElseThrow(() -> new ResourceNotFoundException("quiz with id " + id + " not found"));

        List<Question> quizQuestions = quiz.getQuestion();
        List<QuestionAnswerDTO> userAnswers = submitQuizRequestDTO.getAnswers();

        //convert user answer to map
        Map<Integer, QuestionAnswerDTO> userAnswerMap = new HashMap<>();
        for (QuestionAnswerDTO questionAnswerDTO : userAnswers) {
            userAnswerMap.put(questionAnswerDTO.getId(), questionAnswerDTO);
        }

        int score = 0;
        List<FeedbackDTO> feedbackDTOList = new ArrayList<>();
        for (Question question : quizQuestions) {
            QuestionAnswerDTO userAnswer = userAnswerMap.get(question.getId());

            FeedbackDTO feedbackDTO = new FeedbackDTO();
            feedbackDTO.setId(question.getId());
            feedbackDTO.setCorrectAnswer(question.getCorrectAnswer());
            feedbackDTO.isCorrect(false);
            if (userAnswer != null) {
                if (question.getCorrectAnswer().equals(userAnswer.getUserAnswer())) {
                    score++;
                    feedbackDTO.isCorrect(true);
                }
            }
            feedbackDTOList.add(feedbackDTO);
        }
        SubmitQuizResponseDTO submitQuizResponseDTO = new SubmitQuizResponseDTO();
        submitQuizResponseDTO.setTotalQuestions(quizQuestions.size());
        submitQuizResponseDTO.setScore(score);
        submitQuizResponseDTO.setFeedbackDTOList(feedbackDTOList);

        return submitQuizResponseDTO;
    }

    private static <T> List<T> selectRandomItems(List<T> list, int numberOfItems) {
        // Shuffle the list to randomize the order
        List<T> shuffledList = new ArrayList<>(list);
        Collections.shuffle(shuffledList);

        // Return the first 'numberOfItems' elements from the shuffled list
        return shuffledList.subList(0, Math.min(numberOfItems, shuffledList.size()));
    }
}
