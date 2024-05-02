package com.example.recipe_20240425.services;

import com.example.recipe_20240425.entities.Ingredient;
import com.example.recipe_20240425.repositories.IngredientRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public List<Ingredient> findAllIngredients() {
        List<Ingredient> result = null;
        try {
            result = ingredientRepository.findAll();
        } catch (Exception e) {
            System.out.println("Ingredient list not found");
        }
        return result;
    }

    public Ingredient findAllIngredientById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public void deleteIngredientById(Long id) {
        ingredientRepository.deleteById(id);
    }

    public Ingredient findAllIngredientByName(String name) {
        return ingredientRepository.findByName(name);
    }

    public List<Ingredient> addNewIngredient(List<Ingredient> ingredients) {
        if (ingredients.isEmpty()) {
            return null;
        }
        for (Ingredient ingredient : ingredients) {
            Ingredient dbIngredient = findAllIngredientByName(ingredient.getName());
            if (dbIngredient == null) {
                ingredientRepository.saveAndFlush(ingredient);
            } else {
                ingredients.remove(ingredient);
                ingredients.add(dbIngredient);
            }
        }
        return ingredients;
    }

}

