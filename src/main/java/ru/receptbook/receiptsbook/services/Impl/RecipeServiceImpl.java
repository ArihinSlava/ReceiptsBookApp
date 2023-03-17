package ru.receptbook.receiptsbook.services.Impl;

import org.springframework.stereotype.Service;
import ru.receptbook.receiptsbook.model.Recipe;

import java.util.Collection;
import java.util.HashMap;
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
    public Recipe deleteRecipe(long id) {
        return recipes.remove(id);
    }

    @Override
    public Recipe editRecipe(long id, Recipe recipe) {
        recipes.replace(id, recipe);
        return recipes.get(id);
    }

    @Override
    public Collection<Recipe> getRecipes() {
        return recipes.values();
    }

}
