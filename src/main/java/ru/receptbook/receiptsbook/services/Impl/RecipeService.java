package ru.receptbook.receiptsbook.services.Impl;

import ru.receptbook.receiptsbook.model.Recipe;

import java.util.List;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(long recipeNumber);

    boolean deleteRecipe(long id);

    Recipe editRecipe(long id, Recipe recipe);
}
