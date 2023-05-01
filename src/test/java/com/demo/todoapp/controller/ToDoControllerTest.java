package com.demo.todoapp.controller;

import com.demo.todoapp.model.ToDoDTO;
import com.demo.todoapp.service.ToDoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.demo.todoapp.util.TestUtil.getMockDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
public class ToDoControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private ToDoService toDoService;

    @Test
    public void verifyFetchAllTodoItemsApiReturnsSuccess() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/todos")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void verifyFetchByIdItemsApiReturns() throws Exception {

        ToDoDTO toDoDTO = getMockDto();

        when(toDoService.get(1234)).thenReturn(toDoDTO);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/todos/1234")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Pay Bill"));
    }

    @Test
    public void verifyTodoItemCreatedSuccessfully() throws Exception {
        ToDoDTO toDoDTO = getMockDto();

        Mockito.when(toDoService.create(any())).thenReturn(1234);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/todos")
                        .content(asJsonString(toDoDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("To do Item created with Id : 1234"))
                .andExpect(status().isCreated());
    }


    @Test
    public void verifyBadRequestWhenInputRequestIsInvalid() throws Exception {
        ToDoDTO toDoDTO = getMockDto();
        toDoDTO.setName(null);
        Mockito.when(toDoService.create(any())).thenReturn(1234);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/todos")
                        .content(asJsonString(toDoDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void verifyTodoItemUpdatedSuccessfully() throws Exception {
        ToDoDTO toDoDTO = getMockDto();

       Mockito.doNothing().when(toDoService).update(789,toDoDTO);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/todos/789")
                        .content(asJsonString(toDoDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("To do Item updated for Id : 789"))
                .andExpect(status().isOk());
    }


    @Test
    public void verifyTodoItemDeletedSuccessfully() throws Exception {
        ToDoDTO toDoDTO = getMockDto();

        Mockito.doNothing().when(toDoService).update(789,toDoDTO);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/todos/789")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("To do Item deleted with Id : 789"))
                .andExpect(status().isOk());
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}