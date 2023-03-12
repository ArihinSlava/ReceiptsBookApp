package ru.receptbook.receiptsbook.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    public String name;
    public int cookingTime;
    List<Ingredient> ingredients = new ArrayList<>();
    List<String> cookingSteps = new ArrayList<>();

}
