package ru.receptbook.receiptsbook.services;

import java.io.File;

public interface FilesServiceRecipe {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();
}
