package com.demo.todoapp.model;

import com.demo.todoapp.util.Priority;
import com.demo.todoapp.util.ToDoStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToDoDTO {
    private Integer id;
    @NotNull
    private String name;

    @NotNull
    private String description;
    @NotNull
    private ToDoStatus status;

    @NotNull
    private Priority priority;

    private String startDate;

    private String dueDate;

}
