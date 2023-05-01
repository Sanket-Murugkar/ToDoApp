package com.demo.todoapp.util;

import com.demo.todoapp.domain.ToDo;
import com.demo.todoapp.model.ToDoDTO;

import static com.demo.todoapp.util.DateUtil.convertToDate;

public class ToDoMapper {

    public static ToDoDTO mapToDTO(final ToDo toDo, final ToDoDTO toDoDTO) {
        toDoDTO.setId(toDo.getId());
        toDoDTO.setName(toDo.getName());
        toDoDTO.setDescription(toDo.getDescription());
        toDoDTO.setStatus(toDo.getStatus());
        toDoDTO.setStartDate(toDo.getStartDate().toString());
        toDoDTO.setDueDate(toDo.getDueDate().toString());
        return toDoDTO;
    }

    public static void mapToEntity(final ToDoDTO toDoDTO, final ToDo toDo) {
        toDo.setName(toDoDTO.getName());
        toDo.setDescription(toDoDTO.getDescription());
        toDo.setStatus(toDoDTO.getStatus());
        toDo.setStartDate(convertToDate(toDoDTO.getStartDate()));
        toDo.setDueDate(convertToDate(toDoDTO.getDueDate()));
    }
}
