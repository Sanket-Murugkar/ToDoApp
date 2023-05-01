package com.demo.todoapp.controller;

import com.demo.todoapp.model.ApiResponse;
import com.demo.todoapp.model.ToDoDTO;
import com.demo.todoapp.service.ToDoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/todos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ToDoController {

    private final ToDoService toDoService;

    private final String TODO_ITEM_CREATION_SUCCESS = "To do Item created with Id : %s";

    private final String TODO_ITEM_UPDATE_SUCCESS = "To do Item updated for Id : %s";

    private final String TODO_ITEM_DELETE_SUCCESS = "To do Item deleted with Id : %s";

    public ToDoController(final ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    @Operation(summary = "Find All To Do items")
    public ResponseEntity<List<ToDoDTO>> getAllToDos() {
        return ResponseEntity.ok(toDoService.findAll());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Find To Do items for given ID")
    public ResponseEntity<ToDoDTO> getToDo(@PathVariable final Integer id) {
        return ResponseEntity.ok(toDoService.get(id));
    }

    @PostMapping
    @Operation(summary = "Create new TO Do Item")
    public ResponseEntity<ApiResponse> createToDo(@RequestBody  @Valid final ToDoDTO toDoDTO) {
        final Integer createdId = toDoService.create(toDoDTO);
        ApiResponse apiResponse = ApiResponse.builder().status("Success").message(String.format(TODO_ITEM_CREATION_SUCCESS, createdId)).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update TO Do Item for given ID")
    public ResponseEntity<ApiResponse> updateToDo(@PathVariable final Integer id,
                                                  @RequestBody @Valid final ToDoDTO toDoDTO) {
        toDoService.update(id, toDoDTO);
        ApiResponse apiResponse = ApiResponse.builder().status("Success").message(String.format(TODO_ITEM_UPDATE_SUCCESS, id)).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete TO Do Item for given ID")
    public ResponseEntity<ApiResponse> deleteToDo(@PathVariable final Integer id) {
        toDoService.delete(id);
        ApiResponse apiResponse = ApiResponse.builder().status("Success").message(String.format(TODO_ITEM_DELETE_SUCCESS, id)).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
