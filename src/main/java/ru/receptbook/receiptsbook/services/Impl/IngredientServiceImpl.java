package ru.receptbook.receiptsbook.services.Impl;

import org.springframework.stereotype.Service;
import ru.receptbook.receiptsbook.model.Ingredient;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static final Map<Long, Ingredient> ingredients = new HashMap<>();
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
    public Ingredient editIngredient(long id, Ingredient ingredient) {
            if (ingredients.containsKey(id)) {
                ingredients.put(id, ingredient);
                return ingredient;
            }
        return null;
    }

    @Override
    public boolean deleteIngredient(long id) {
        if (ingredients.containsKey(id)) {
                ingredients.remove(id);
                return true;
            }
        return false;
    }

    @Override
    public Collection<Ingredient> getIngredient() {
        return ingredients.values();
    }

}
