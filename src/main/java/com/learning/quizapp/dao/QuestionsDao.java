package com.learning.quizapp.dao;

import com.learning.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsDao extends JpaRepository<Question, Integer> {

}
