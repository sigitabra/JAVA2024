package com.example.recipe_20240425.services;

import com.example.recipe_20240425.entities.Recipe;
import com.example.recipe_20240425.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;

    public List<Recipe> findAllRecipes() {
        List<Recipe> result = null;
        try {
            result = recipeRepository.findAll();
        } catch (Exception e) {
            System.out.println("Recipe list not found");
        }
        return result;
    }

    public Recipe findAllRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }

    public List<Recipe> addNewRecipe(List<Recipe> recipes) {
        if (recipes.isEmpty()) {
            return null;
        }
        return recipeRepository.saveAllAndFlush(recipes);
    }


}
