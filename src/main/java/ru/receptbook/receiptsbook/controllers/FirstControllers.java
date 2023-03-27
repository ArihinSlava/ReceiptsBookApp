package ru.receptbook.receiptsbook.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstControllers {

    @GetMapping("/")
    public String startApp() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String information() {
        return " Имя ученика: Вячеслав Арихин. " +
                " Название проекта: Книга рецептов. " +
                " Дата создания проекта: 25.02.2023. " +
                " Описание проекта: Приложение в котором можно найти книгу рецептов ";
    }
}
