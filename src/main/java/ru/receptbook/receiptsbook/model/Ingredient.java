package ru.receptbook.receiptsbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    public String name;
    public int amountIngredient;
    private String measureUnit;

}
