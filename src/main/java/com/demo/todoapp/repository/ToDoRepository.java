package com.demo.todoapp.repository;

import com.demo.todoapp.domain.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
}
