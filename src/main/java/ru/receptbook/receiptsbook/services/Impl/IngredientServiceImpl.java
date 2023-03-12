package ru.receptbook.receiptsbook.services.Impl;

import org.springframework.stereotype.Service;
import ru.receptbook.receiptsbook.model.Ingredient;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Long, Ingredient> ingredients = new HashMap<>();
    private static long ingredientNumber = 0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.putIfAbsent(ingredientNumber++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(long ingredientNumber) {
        return ingredients.get(ingredientNumber);
    }
}
