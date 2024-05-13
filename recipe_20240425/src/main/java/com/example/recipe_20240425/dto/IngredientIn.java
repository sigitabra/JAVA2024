package com.example.recipe_20240425.dto;

import com.example.recipe_20240425.enums.IngredientCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class IngredientIn {
    @NotBlank
    @Size(min = 1, max = 255)
    private String name;
    @NotBlank
    private IngredientCategory category;

}
