package com.learning.quizapp.dao;


import com.learning.quizapp.dtos.QuizIdTitleDTO;
import com.learning.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

    // Fetch unique categories and count of quizzes per category
    @Query("SELECT q.category, COUNT(q) FROM Quiz q GROUP BY q.category")
    List<Object[]> findCategoriesWithQuizCount();

    // Fetch only the id and title of quizzes by category using a JPQL query
    @Query("SELECT new com.learning.quizapp.dtos.QuizIdTitleDTO(q.id, q.title) FROM Quiz q WHERE q.category = :category")
    List<QuizIdTitleDTO> findQuizByCategory(@Param("category") String category);
}
