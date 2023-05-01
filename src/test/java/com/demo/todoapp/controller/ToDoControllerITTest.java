package com.demo.todoapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.demo.todoapp.ToDoApplication;
import com.demo.todoapp.model.APIResponse;
import com.demo.todoapp.model.ToDoDTO;
import com.demo.todoapp.util.TestUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;


import java.util.List;
import java.util.Objects;


@SpringBootTest(classes = ToDoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.yml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ToDoControllerITTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @Sql("classpath:data-init.sql")
    public void testFetchALlToDoItems() {

        List<ToDoDTO> list = this.restTemplate
                .getForObject("http://localhost:" + port + "/api/todos", List.class);

        Assert.assertEquals(3,list.size());

    }

    @Test
    public void testCreateToDoItem() {
        ToDoDTO employee = TestUtil.getMockDto();
        employee.setName("test");

        ResponseEntity<APIResponse> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/todos", employee, APIResponse.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getBody()).getMessage().startsWith("To do Item created with Id"));

    }

    @Test
    @Sql("classpath:data-init.sql")
    public void testPutToDoItem() {
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setName("updatedName");

        restTemplate.put("http://localhost:" + port + "/api/todos/1", toDoDTO, "1");

        ToDoDTO updatedItem = this.restTemplate
                .getForObject("http://localhost:" + port + "/api/todos/1", ToDoDTO.class);
        assertEquals("test",updatedItem.getName());

    }

    @Test
    @Sql("classpath:data-init.sql")
    public void testDeleteToDoItem() {
        ToDoDTO toDoDTO = getToDoDTO();
        HttpEntity<ToDoDTO> request = new HttpEntity<>(toDoDTO);

        ResponseEntity<APIResponse> responseEntity = restTemplate
                .exchange("http://localhost:" + port + "/api/todos/1", HttpMethod.DELETE, request, APIResponse.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getBody()).getMessage().startsWith("To do Item deleted with Id : 1"));

    }

    private static ToDoDTO getToDoDTO() {
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setId(1);
        toDoDTO.setName("test");
        return toDoDTO;
    }

}