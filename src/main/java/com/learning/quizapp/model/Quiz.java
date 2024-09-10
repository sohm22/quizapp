package com.learning.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String category;

    // https://github.com/sohm22/quizapp/wiki/Many%E2%80%90to%E2%80%90many-Relatationship-between-Quiz-and-Question
    @ManyToMany
    private List<Question> question;
}
