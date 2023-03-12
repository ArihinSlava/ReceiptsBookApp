package ru.receptbook.receiptsbook.services.Impl;

import ru.receptbook.receiptsbook.model.Recipe;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(long recipeNumber);
}
