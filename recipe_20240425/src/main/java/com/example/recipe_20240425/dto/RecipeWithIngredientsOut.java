package com.example.recipe_20240425.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class RecipeWithIngredientsOut extends RecipeOut {

    private List<IngredientOut> ingredients;

}
