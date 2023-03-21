package ru.receptbook.receiptsbook.services;

import ru.receptbook.receiptsbook.model.Recipe;

import java.util.Collection;
import java.util.List;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(long recipeNumber);

    Recipe deleteRecipe(long id);

    Recipe editRecipe(long id, Recipe recipe);

    Collection<Recipe> getRecipes();

}
