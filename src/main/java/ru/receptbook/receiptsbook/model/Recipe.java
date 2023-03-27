package ru.receptbook.receiptsbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    private String name;
    private int cookingTime;
    private List<Ingredient> ingredients = new ArrayList<>();
    private List<String> cookingSteps = new ArrayList<>();

    @Override
    public String toString() {
        return name + "\n" +
                "Время приготовления:" + cookingTime + "\n" +
                "Ингридиенты: \n" + ingredients + "\n" +
                "Инструкция приготовления \n" + cookingSteps + "\n" + "\n";
    }
}
