package ru.receptbook.receiptsbook.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.receptbook.receiptsbook.model.Ingredient;
import ru.receptbook.receiptsbook.services.FilesServiceIngredient;
import ru.receptbook.receiptsbook.services.IngredientService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {

    final private FilesServiceIngredient fileService;
    private static Map<Long, Ingredient> ingredients = new HashMap<>();
    private static long id = 0;

    public IngredientServiceImpl(FilesServiceIngredient fileService) {
        this.fileService = fileService;
    }


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.putIfAbsent(id++, ingredient);
        saveToFile();
        return ingredient;
    }

    @PostConstruct
    private void init() {
        File file = fileService.getDataFile();
        if (file.exists()) {
            readFromFile();
        }
    }

    @Override
    public Ingredient getIngredient(long ingredientNumber) {
        return ingredients.get(ingredientNumber);
    }

    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient) {
            if (ingredients.containsKey(id)) {
                ingredients.put(id, ingredient);
                saveToFile();
                return ingredient;
            }
        return null;
    }

    @Override
    public boolean deleteIngredient(long id) {
        if (ingredients.containsKey(id)) {
                ingredients.remove(id);
                saveToFile();
                return true;
            }
        return false;
    }

    @Override
    public Collection<Ingredient> getIngredient() {
        return ingredients.values();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    void readFromFile() {
        String json = fileService.readFromFile();
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
