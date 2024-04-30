package com.example.recipe_20240425.dto;

import lombok.Data;

import java.util.List;

@Data
public class IngredientWithRecipesOut extends IngredientOut {

    private List<RecipeOut> recipes;

}
