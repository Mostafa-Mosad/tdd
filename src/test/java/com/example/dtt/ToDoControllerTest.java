package com.example.dtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class ToDoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    @Test
    void getAllToDos() throws Exception {
        List<ToDo> toDoList = new ArrayList<>();
        toDoList.add(new ToDo(1L, "Study TDD", true));
        toDoList.add(new ToDo(2L, "TDD Demo", false));

        //Mock service response with above static data
        when(toDoService.findAll()).thenReturn(toDoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/todos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

    @Test
    void save() throws Exception {
        ToDo newToDo = ToDo.builder()
                .text("Add new ToDo")
                .completed(true)
                .build();
        when(toDoService.save(any(ToDo.class))).thenReturn(newToDo);

        ObjectMapper objectMapper = new ObjectMapper();
        String newToDoJson = objectMapper.writeValueAsString(newToDo);

        ResultActions result = mockMvc.perform(post("/add-todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newToDoJson));
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value(newToDo.getText()))
                .andExpect(jsonPath("$.completed").value(newToDo.isCompleted()))
                .andDo(print());
    }
}
