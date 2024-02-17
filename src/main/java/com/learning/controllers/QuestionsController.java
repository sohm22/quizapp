package com.learning.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("questions")
public class QuestionsController {

    @GetMapping("all")
    public String getAllQuestions(){
        return "Hi, these is all your questions";
    }

}
