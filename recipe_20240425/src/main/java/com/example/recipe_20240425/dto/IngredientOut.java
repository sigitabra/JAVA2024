package com.example.recipe_20240425.dto;

import com.example.recipe_20240425.enums.IngredientCategory;
import lombok.Data;

@Data
public class IngredientOut {

    private Long id;

    private String name;

    private IngredientCategory category;

}
