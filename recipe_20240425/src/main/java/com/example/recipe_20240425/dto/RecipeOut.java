package com.example.recipe_20240425.dto;

import com.example.recipe_20240425.enums.RecipeDifficultyLevel;
import lombok.Data;

@Data
public class RecipeOut {

    private Long id;

    private String title;

    private RecipeDifficultyLevel difficulty;

    private String description;

}
