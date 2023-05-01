package com.demo.todoapp.util;

import com.demo.todoapp.domain.ToDo;
import com.demo.todoapp.model.ToDoDTO;

public class TestUtil {

    public static ToDoDTO getMockDto(){
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setId(789);
        toDoDTO.setName("Pay Bill");
        toDoDTO.setDescription("Test");
        toDoDTO.setStatus(ToDoStatus.IN_PROGRESS);
        toDoDTO.setStartDate("15/05/2023");
        toDoDTO.setDueDate("25/05/2023");
        toDoDTO.setPriority(Priority.LOW);
        return toDoDTO;
    }

    public static ToDo getMockToDo(){
        ToDo toDo = new ToDo();
        toDo.setId(789);
        toDo.setName("Pay Bill");
        toDo.setDescription("Test");
        toDo.setStatus(ToDoStatus.IN_PROGRESS);
        toDo.setStartDate(DateUtil.convertToDate("15/05/2023"));
        toDo.setDueDate(DateUtil.convertToDate("25/05/2023"));

        return toDo;
    }
}
