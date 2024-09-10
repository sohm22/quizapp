package com.learning.quizapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoryQuizCountDTO {
    private String category;
    private Long quizCount;
}
