package com.demo.todoapp.service;

import com.demo.todoapp.model.ToDoDTO;
import com.demo.todoapp.exception.NotFoundException;
import com.demo.todoapp.domain.ToDo;
import com.demo.todoapp.repository.ToDoRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.demo.todoapp.util.ToDoMapper.mapToDTO;
import static com.demo.todoapp.util.ToDoMapper.mapToEntity;


@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoService(final ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDoDTO> findAll() {
        final List<ToDo> toDos = toDoRepository.findAll(Sort.by("id"));
        return toDos.stream()
                .map(toDo -> mapToDTO(toDo, new ToDoDTO()))
                .collect(Collectors.toList());
    }

    public ToDoDTO get(final Integer id) {
        return toDoRepository.findById(id)
                .map(toDo -> mapToDTO(toDo, new ToDoDTO()))
                .orElseThrow(() -> NotFoundException.builder().message("No To Do item found for given ID").build());
    }


    public Integer create(final ToDoDTO toDoDTO) {
        final ToDo toDo = new ToDo();
        mapToEntity(toDoDTO, toDo);
        ToDo persistedEntity = toDoRepository.save(toDo);
        return persistedEntity.getId();
    }

    public void update(final Integer id, final ToDoDTO toDoDTO) {
        final ToDo toDo = toDoRepository.findById(id).
        orElseThrow(() -> NotFoundException.builder().message("No To Do item found for given ID").build());
        mapToEntity(toDoDTO, toDo);
        toDoRepository.save(toDo);
    }

    public void delete(final Integer id) {
        boolean existsById = toDoRepository.existsById(id);
        if(!existsById){
            throw  new NotFoundException().builder().message("No To Do item found for given ID").build();
        }
        toDoRepository.deleteById(id);
    }

}
