package com.example.recipe_20240425.controllers;

import com.example.recipe_20240425.converters.RecipeConverter;
import com.example.recipe_20240425.dto.RecipeIn;
import com.example.recipe_20240425.dto.RecipeOut;
import com.example.recipe_20240425.dto.RecipeWithIngredientsOut;
import com.example.recipe_20240425.entities.Ingredient;
import com.example.recipe_20240425.entities.Recipe;
import com.example.recipe_20240425.services.IngredientService;
import com.example.recipe_20240425.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(originPatterns = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    @PreAuthorize("hasAnyRole('CHEF', 'USER')")
    @GetMapping
    public ResponseEntity<List<RecipeOut>> getAllRecipes() {
        List<Recipe> recipeList = recipeService.findAllRecipes();
        if (recipeList == null) {
            return ResponseEntity.notFound().build();
        }
        if (recipeList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(RecipeConverter.convertEntityListToRecipeOut(recipeList));
    }

    @PreAuthorize("hasAnyRole('CHEF')")
    @PostMapping
    public ResponseEntity<List<RecipeOut>> addRecipe(@RequestBody List<RecipeIn> recipesIn) {
        List<RecipeOut> body;
        List<Recipe> recipeList = new ArrayList<>();
        try {
            for (RecipeIn recipeIn : recipesIn) {
                Recipe recipe = RecipeConverter.convertRecipeInToEntity(recipeIn);
                List<Ingredient> ingredients = ingredientService.findIngredientByListId(recipeIn.getIngredientIds());
                recipe.setIngredients(ingredients);
                recipeList.add(recipe);
            }

            body = RecipeConverter.convertEntityListToRecipeWithIngredientsOut(recipeService.addNewRecipe(recipeList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PreAuthorize("hasAnyRole('CHEF', 'USER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<RecipeWithIngredientsOut> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.findAllRecipeById(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(RecipeConverter.convertEntityToRecipeWithIngredientsOut(recipe));
    }

    @PreAuthorize("hasAnyRole('CHEF')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteRecipeById(@PathVariable Long id) {
        if (recipeService.findAllRecipeById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            recipeService.deleteRecipeById(id);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
