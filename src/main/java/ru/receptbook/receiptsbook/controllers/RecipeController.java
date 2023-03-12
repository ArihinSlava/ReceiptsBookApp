package ru.receptbook.receiptsbook.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.receptbook.receiptsbook.model.Recipe;
import ru.receptbook.receiptsbook.services.Impl.RecipeService;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        Recipe createRecipe = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(createRecipe);

    }

    @GetMapping
    public ResponseEntity<Recipe> getRecipe(@PathVariable long recipeNumber) {
        Recipe recipe = recipeService.getRecipe(recipeNumber);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
}
