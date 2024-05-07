package com.example.recipe_20240425.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class IngredientWithRecipesOut extends IngredientOut {

    private List<RecipeOut> recipes;

}
