package ru.receptbook.receiptsbook.services;

import ru.receptbook.receiptsbook.model.Ingredient;

import java.util.Collection;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);
    Ingredient getIngredient(long id);

    Ingredient editIngredient(long id, Ingredient ingredient);

    boolean deleteIngredient(long id);
    Collection<Ingredient> getIngredient();
}
