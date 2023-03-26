package ru.receptbook.receiptsbook.services;


import java.io.File;

public interface FilesServiceIngredient {

    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();
}
