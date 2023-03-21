package ru.receptbook.receiptsbook.services.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.receptbook.receiptsbook.services.FilesServiceIngredient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceIngredientImpl implements FilesServiceIngredient {


    @Value("${path.to.data.file}")
    private String ingredientFilePath;

    @Value("${name.of.data.file.ingredient}")
    private String ingredientFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(ingredientFilePath, ingredientFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(ingredientFilePath, ingredientFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public File getDataFile() {
        return new File(ingredientFilePath + "/" + ingredientFileName);
    }

    private boolean cleanDataFile() {
        try {
            Path path = Path.of(ingredientFilePath, ingredientFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
