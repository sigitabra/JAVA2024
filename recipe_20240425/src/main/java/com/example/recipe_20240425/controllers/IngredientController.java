package com.example.recipe_20240425.controllers;

import com.example.recipe_20240425.converters.IngredientConverter;
import com.example.recipe_20240425.dto.IngredientIn;
import com.example.recipe_20240425.dto.IngredientOut;
import com.example.recipe_20240425.dto.IngredientWithRecipesOut;
import com.example.recipe_20240425.entities.Ingredient;
import com.example.recipe_20240425.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(originPatterns = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientOut>> getAllIngredients() {
        List<Ingredient> ingredientList = ingredientService.findAllIngredients();
        if (ingredientList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (ingredientList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(IngredientConverter.convertEntityListToIngredientOutList(ingredientList));
    }

    @PostMapping
    public ResponseEntity<List<IngredientOut>> addIngredient(@RequestBody List<IngredientIn> ingredientsIn) {
        List<IngredientOut> body;
        try {
            body = IngredientConverter.convertEntityListToIngredientOutList(ingredientService.addNewIngredient(IngredientConverter.convertIngredientInLitsToEntityList(ingredientsIn)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<IngredientWithRecipesOut> getIngredientById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.findAllIngredientById(id);
        if (ingredient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(IngredientConverter.convertEntityToIngredientWithRecipesOut(ingredient));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteIngredientById(@PathVariable Long id) {
        if (ingredientService.findAllIngredientById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        try {
            ingredientService.deleteIngredientById(id);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
