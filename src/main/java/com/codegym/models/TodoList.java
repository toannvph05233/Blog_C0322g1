package com.codegym.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class TodoList {
    private String todo;
    private LocalDateTime date;

    public TodoList() {
        date = LocalDateTime.now();
    }

    public TodoList(String todo, LocalDateTime date) {
        this.todo = todo;
        this.date = date;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
