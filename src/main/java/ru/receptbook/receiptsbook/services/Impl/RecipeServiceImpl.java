package ru.receptbook.receiptsbook.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.receptbook.receiptsbook.model.Recipe;
import ru.receptbook.receiptsbook.services.FilesServiceRecipe;
import ru.receptbook.receiptsbook.services.RecipeService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Service
public class RecipeServiceImpl implements RecipeService {

    final private FilesServiceRecipe fileService;
    private static  Map<Long, Recipe> recipes = new HashMap<>();
    private static long id = 0;

    public RecipeServiceImpl(FilesServiceRecipe fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    private void init() {
        File file = fileService.getDataFile();
        if ((file.exists())) {
            readFromFile();
        }
    }


    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipes.putIfAbsent(id++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(long id) {
        return recipes.get(id);
    }


    @Override
    public Recipe deleteRecipe(long id) {
        return recipes.remove(id);
    }

    @Override
    public Recipe editRecipe(long id, Recipe recipe) {
        recipes.replace(id, recipe);
        saveToFile();
        return recipes.get(id);
    }

    @Override
    public Collection<Recipe> getRecipes() {
        return recipes.values();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    void readFromFile() {
        String json = fileService.readFromFile();
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
