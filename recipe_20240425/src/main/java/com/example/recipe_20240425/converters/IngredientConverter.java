package com.example.recipe_20240425.converters;

import com.example.recipe_20240425.dto.IngredientIn;
import com.example.recipe_20240425.dto.IngredientOut;
import com.example.recipe_20240425.dto.IngredientWithRecipesOut;
import com.example.recipe_20240425.entities.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static com.example.recipe_20240425.converters.RecipeConverter.convertEntityListToRecipeOut;


public class IngredientConverter {


    public static <T extends IngredientOut> T mapIgredient(Ingredient ingredient, T ingredientOut) {
        if (ingredient != null) {
            ingredientOut.setId(ingredient.getId());
            ingredientOut.setName(ingredient.getName());
            ingredientOut.setCategory(ingredient.getCategory());
        }
        return ingredientOut;
    }

    public static IngredientOut convertEntityToIngredientOut(Ingredient ingredient) {
        IngredientOut ingredientOut = new IngredientOut();
        return mapIgredient(ingredient, ingredientOut);
    }

    public static IngredientWithRecipesOut convertEntityToIngredientWithRecipesOut(Ingredient ingredient) {
        IngredientWithRecipesOut ingredientOut = new IngredientWithRecipesOut();
        if (ingredient != null) {
            mapIgredient(ingredient, ingredientOut);
            ingredientOut.setRecipes(convertEntityListToRecipeOut(ingredient.getRecipes()));
        }
        return ingredientOut;
    }


    public static List<IngredientOut> convertEntityListToIngredientOutList(List<Ingredient> ingredients) {
        List<IngredientOut> ingredientsOut = null;
        if (ingredients != null && !ingredients.isEmpty()) {
            ingredientsOut = new ArrayList<>();
            for (Ingredient ingredient : ingredients) {
                ingredientsOut.add(convertEntityToIngredientOut(ingredient));
            }
        }
        return ingredientsOut;
    }

    public static List<Ingredient> convertIngredientInLitsToEntityList(List<IngredientIn> ingredientsIn) {
        List<Ingredient> ingredients = null;
        if (ingredientsIn != null && !ingredientsIn.isEmpty()) {
            ingredients = new ArrayList<>();
            for (IngredientIn ingredientIn : ingredientsIn) {
                ingredients.add(convertIngredientInToEntity(ingredientIn));
            }
        }
        return ingredients;
    }

    public static Ingredient convertIngredientInToEntity(IngredientIn ingredientIn) {
        Ingredient ingredient = new Ingredient();
        if (ingredientIn != null) {
            ingredient.setName(ingredientIn.getName());
            ingredient.setCategory(ingredientIn.getCategory());
        }
        return ingredient;
    }


}
