package ru.receptbook.receiptsbook.services.Impl;

import org.springframework.stereotype.Service;
import ru.receptbook.receiptsbook.model.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static final Map<Long, Recipe> recipes = new HashMap<>();
    private static long id = 0;


    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipes.putIfAbsent(id++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(long id) {
        return recipes.get(id);
    }

    @Override
    public  boolean deleteRecipe(long id) {
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.containsKey(id)) {
                recipes.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Recipe editRecipe(long id, Recipe recipe) {
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.containsKey(id)) {
                recipes.put(id, recipe);
                return recipe;
            }
        }
        return null;
    }
}
