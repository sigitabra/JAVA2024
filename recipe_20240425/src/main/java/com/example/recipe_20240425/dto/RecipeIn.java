package com.example.recipe_20240425.dto;

import com.example.recipe_20240425.enums.RecipeDifficultyLevel;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class RecipeIn {
    @NotBlank
    private String title;

    @NotBlank
    private RecipeDifficultyLevel difficulty;

    @NotBlank
    private String description;

    private List<Long> ingredientIds;

}
