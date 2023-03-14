package ru.receptbook.receiptsbook.services.Impl;

import org.springframework.stereotype.Service;
import ru.receptbook.receiptsbook.model.Ingredient;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Long, Ingredient> ingredients = new HashMap<>();
    private static long id = 0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.putIfAbsent(id++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(long ingredientNumber) {
        return ingredients.get(ingredientNumber);
    }

    @Override
    public boolean deleteRecipe(long id) {
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.containsKey(id)) {
                ingredients.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient) {
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.containsKey(id)) {
                ingredients.put(id, ingredient);
                return ingredient;
            }
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(long id) {
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.containsKey(id)) {
                ingredients.remove(id);
                return true;
            }
        }
        return false;
    }
}
