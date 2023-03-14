package ru.receptbook.receiptsbook.services.Impl;

import ru.receptbook.receiptsbook.model.Ingredient;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);
    Ingredient getIngredient(long id);

    boolean deleteRecipe(long id);

    Ingredient editIngredient(long id, Ingredient ingredient);

    boolean deleteIngredient(long id);
}
