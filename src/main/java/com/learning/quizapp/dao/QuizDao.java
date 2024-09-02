package com.learning.quizapp.dao;

import com.learning.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

    // Fetch unique categories and count of quizzes per category
    @Query("SELECT q.category, COUNT(q) FROM Quiz q GROUP BY q.category")
    List<Object[]> findCategoriesWithQuizCount();
}
