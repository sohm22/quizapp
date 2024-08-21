package com.learning.quizapp.quizapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.quizapp.controllers.QuestionController;

import com.learning.quizapp.model.Question;
import com.learning.quizapp.services.QuestionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(QuestionController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testGetAllQuestions() throws Exception {
        List<Question> questions = Arrays.asList(
                createQuestion(
                        1,
                        "What is Java?",
                        "A programming language",
                        "A coffee brand",
                        "An island",
                        "A car model",
                        "A programming language",
                        "Easy",
                        "Programming"
                ),
                createQuestion(
                        2,
                        "What is Spring Boot?",
                        "A framework",
                        "A type of shoe",
                        "A spring festival",
                        "None of the above",
                        "A framework",
                        "Medium",
                        "Framework"
                )
        );

        when(questionService.getAllQuestions()).thenReturn(questions);

        mockMvc.perform(get("/questions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].questionTitle").value("What is Java?"))
                .andExpect(jsonPath("$[0].category").value("Programming"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].questionTitle").value("What is Spring Boot?"))
                .andExpect(jsonPath("$[1].category").value("Framework"));
    }

    @Test
    public void testGetQuestionsByCategory() throws Exception {
        List<Question> questions = Arrays.asList(
                createQuestion(
                        1,
                        "What is Java?",
                        "A programming language",
                        "A coffee brand",
                        "An island",
                        "A car model",
                        "A programming language",
                        "Easy",
                        "Programming"
                )
        );

        when(questionService.getQuestionsByCategory("programming")).thenReturn(questions);

        mockMvc.perform(get("/questions/category/programming"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    public void testAddAndUpdateQuestion() throws Exception {
        int id = 10;
        Question question = createQuestion(
                id,
                "What is Java?",
                "A programming language",
                "A coffee brand",
                "An island",
                "A car model",
                "A programming language",
                "Easy",
                "Programming"
        );

        when(questionService.addQuestion(question)).thenReturn(question);
        when(questionService.updateQuestionIfNotNull(id, question)).thenReturn(question);
        when(questionService.updateQuestion(id, question)).thenReturn(question);

        mockMvc.perform(post("/questions")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(question)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
        mockMvc.perform(patch("/questions/" + id)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(question)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
        mockMvc.perform(put("/questions/" + id)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(question)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void testGetQuestionById() throws Exception {
        int id = 10;
        Question question = createQuestion(
                id,
                "What is Java?",
                "A programming language",
                "A coffee brand",
                "An island",
                "A car model",
                "A programming language",
                "Easy",
                "Programming"
        );

        when(questionService.getQuestionById(id)).thenReturn(question);

        mockMvc.perform(get("/questions/" + id))
                .andExpect(status().isOk());
    }

    private Question createQuestion(Integer id, String questionTitle, String option1, String option2,
                                    String option3, String option4, String correctAnswer,
                                    String difficultyLevel, String category) {
        Question question = new Question();
        question.setId(id);
        question.setQuestionTitle(questionTitle);
        question.setOption1(option1);
        question.setOption2(option2);
        question.setOption3(option3);
        question.setOption4(option4);
        question.setCorrectAnswer(correctAnswer);
        question.setDifficultyLevel(difficultyLevel);
        question.setCategory(category);
        return question;
    }
}
