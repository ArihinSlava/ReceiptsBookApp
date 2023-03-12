package ru.receptbook.receiptsbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {

    public String name;
    public int amountIngredient;
    private String measureUnit;

}
