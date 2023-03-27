package ru.receptbook.receiptsbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.receptbook.receiptsbook.services.FilesServiceIngredient;
import ru.receptbook.receiptsbook.services.FilesServiceRecipe;
import ru.receptbook.receiptsbook.services.RecipeService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")
@Tag(name = "Файлы" , description = "Экспорт/Импорт файлов")
public class FilesController {

    private FilesServiceRecipe filesServiceRecipe;
    private FilesServiceIngredient filesServiceIngredient;
    private RecipeService recipeService;

    public FilesController(FilesServiceRecipe filesServiceRecipe, FilesServiceIngredient filesServiceIngredient, RecipeService recipeService) {
        this.filesServiceRecipe = filesServiceRecipe;
        this.filesServiceIngredient = filesServiceIngredient;
        this.recipeService = recipeService;
    }

    @GetMapping(value = "/export/recipe")
    @Operation(summary = "Экспорт рецептов в формате .json")
    public ResponseEntity<InputStreamResource> downloadFile() throws FileNotFoundException {
        File file = filesServiceRecipe.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"Recipes.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/export/report")
    @Operation(summary = " Экспорт файла рецептов в формате .txt")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Всё хорошо, запрос выполнился."
            ),
            @ApiResponse(
                    responseCode = "400", description = "Есть ошибка в параметрах запроса"
            ),
            @ApiResponse(
                    responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении."
            ),
            @ApiResponse(
                    responseCode = "500", description = "Во время выполнения запроса произошла ошибка на сервере."
            )
    }
    )
    public ResponseEntity<Object> downloadFileTxt() {
        try {
            Path path = recipeService.createTxt();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }

                InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .contentLength(Files.size(path))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report.txt\"")
                        .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }

    @PostMapping(value = "/import/recipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Импорт файла рецептов в формате .json")
    public ResponseEntity<Void> uploadFile(@RequestParam MultipartFile multipartFile) {
        filesServiceRecipe.cleanDataFile();
        File file = filesServiceRecipe.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            IOUtils.copy(multipartFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/import/ingredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Импорт файла ингредиентов в формате .json")
    public ResponseEntity<Void> uploadFileIngredient(@RequestParam MultipartFile multipartFile) {
        filesServiceIngredient.cleanDataFile();
        File file = filesServiceIngredient.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            IOUtils.copy(multipartFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

