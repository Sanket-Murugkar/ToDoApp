package com.demo.todoapp.util;


import lombok.Getter;

@Getter
public enum ToDoStatus {

    IN_PROGRESS("In Progress"),

    DONE("Done"),

    TO_DO("To Do"),

    BACKLOG("Backlog"),

    CANCELLED("Cancelled");

    private String name;

    ToDoStatus(String name) {
        this.name = name;
    }
}
