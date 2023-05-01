package com.demo.todoapp.service;

import com.demo.todoapp.domain.ToDo;
import com.demo.todoapp.repository.ToDoRepository;
import com.demo.todoapp.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.demo.todoapp.util.TestUtil.getMockDto;
import static com.demo.todoapp.util.TestUtil.getMockToDo;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringJUnit4ClassRunner.class)
public class ToDoServiceTest {


    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoService toDoService;

    @Test
    public void testFindAll(){
        List<ToDo> mockList = new ArrayList<>();
        mockList.add(getMockToDo());

        Mockito.when(toDoRepository.findAll(Sort.by("id"))).thenReturn(mockList);

        Assert.assertEquals(1,toDoService.findAll().size());
    }

    @Test
    public void testFindById(){
        ToDo toDo = getMockToDo();

        Mockito.when(toDoRepository.findById(1)).thenReturn(Optional.of(toDo));

        Assert.assertEquals("Pay Bill",toDoService.get(1).getName());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowExceptionWhenItemIsNotFound(){
        Mockito.when(toDoRepository.findById(1)).thenReturn(Optional.empty());

        Assert.assertEquals("test",toDoService.get(1).getName());
    }

    @Test
    public void testCreateNewToDoItem(){
        ToDo toDo =getMockToDo();

        Mockito.when(toDoRepository.save(any())).thenReturn(toDo);

        Integer id = toDoService.create(getMockDto());

        Assert.assertEquals(Optional.of(789).get(),id);
    }

}