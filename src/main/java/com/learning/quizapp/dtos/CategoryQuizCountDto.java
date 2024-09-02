package com.learning.quizapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoryQuizCountDto {
    private String category;
    private Long quizCount;
}
