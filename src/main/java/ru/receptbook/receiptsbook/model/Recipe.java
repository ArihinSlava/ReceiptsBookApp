package ru.receptbook.receiptsbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {

    private String name;
    private int cookingTime;
    List<Ingredient> ingredients = new ArrayList<>();
    List<String> cookingSteps = new ArrayList<>();

}
