package ru.receptbook.receiptsbook.services.Impl;

import org.springframework.stereotype.Service;
import ru.receptbook.receiptsbook.model.Recipe;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static final Map<Long, Recipe> recipes = new HashMap<>();
    private static long recipeNumber = 0;


    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipes.putIfAbsent(recipeNumber++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(long recipeNumber) {
        return recipes.get(recipeNumber);
    }
}
