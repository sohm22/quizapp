package com.learning.quizapp.services;

import com.learning.quizapp.dao.QuestionsDao;
import com.learning.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsService {

    @Autowired
    QuestionsDao questionsDao;
    public List<Question> getAllQuestions() {
        return questionsDao.findAll();
    }
}
