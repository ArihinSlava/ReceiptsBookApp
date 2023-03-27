package ru.receptbook.receiptsbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private String name;
    private int amountIngredient;
    private String measureUnit;

    @Override
    public String toString() {
        return name + " - " + amountIngredient + " " + measureUnit;

    }
}
