package com.example.recipe_20240425.dto;

import com.example.recipe_20240425.enums.RecipeDifficultyLevel;
import lombok.Data;

import java.util.List;

@Data
public class RecipeIn {

    private String title;

    private RecipeDifficultyLevel difficulty;

    private String description;

    private List<Long> ingredientIds;

}
