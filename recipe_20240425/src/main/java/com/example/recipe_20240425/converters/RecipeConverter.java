package com.example.recipe_20240425.converters;

import com.example.recipe_20240425.dto.RecipeIn;
import com.example.recipe_20240425.dto.RecipeOut;
import com.example.recipe_20240425.dto.RecipeWithIngredientsOut;
import com.example.recipe_20240425.entities.Recipe;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.example.recipe_20240425.converters.IngredientConverter.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecipeConverter {

    public static <T extends RecipeOut> T mapRecipe(Recipe recipe, T recipeOut) {

        if (recipe != null) {
            recipeOut.setId(recipe.getId());
            recipeOut.setTitle(recipe.getTitle());
            recipeOut.setDifficulty(recipe.getDifficulty());
            recipeOut.setDescription(recipe.getDescription());
        }
        return recipeOut;
    }

    public static RecipeOut convertEntityToRecipeOut(Recipe recipe) {
        RecipeOut recipeOut = new RecipeOut();
        return mapRecipe(recipe, recipeOut);
    }

    public static RecipeWithIngredientsOut convertEntityToRecipeWithIngredientsOut(Recipe recipe) {
        RecipeWithIngredientsOut recipeOut = new RecipeWithIngredientsOut();
        if (recipe != null) {
            mapRecipe(recipe, recipeOut);
            recipeOut.setIngredients(convertEntityListToIngredientOutList(recipe.getIngredients()));
        }
        return recipeOut;
    }

    public static List<RecipeOut> convertEntityListToRecipeOut(List<Recipe> recipes) {
        List<RecipeOut> recipesOut = null;
        if (recipes != null && !recipes.isEmpty()) {
            recipesOut = new ArrayList<>();
            for (Recipe recipe : recipes) {
                recipesOut.add(convertEntityToRecipeOut(recipe));
            }
        }
        return recipesOut;
    }

    public static List<RecipeOut> convertEntityListToRecipeWithIngredientsOut(List<Recipe> recipes) {
        List<RecipeOut> recipesOut = null;
        if (recipes != null && !recipes.isEmpty()) {
            recipesOut = new ArrayList<>();
            for (Recipe recipe : recipes) {
                recipesOut.add(convertEntityToRecipeWithIngredientsOut(recipe));
            }
        }
        return recipesOut;
    }

    public static Recipe convertRecipeInToEntity(RecipeIn recipesIn) {
        Recipe recipe = new Recipe();
        if (recipesIn != null) {
            recipe.setTitle(recipesIn.getTitle());
            recipe.setDifficulty(recipesIn.getDifficulty());
            recipe.setDescription(recipesIn.getDescription());
        }
        return recipe;
    }

}
