package ru.receptbook.receiptsbook.services.Impl;

import ru.receptbook.receiptsbook.model.Ingredient;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);
    Ingredient getIngredient(long ingredientNumber);

}
