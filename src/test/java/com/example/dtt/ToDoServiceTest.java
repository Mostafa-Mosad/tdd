package com.example.dtt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ToDoServiceTest {

    @Autowired
    private ToDoRepository toDoRepository;

    @BeforeEach
    void init() {
        toDoRepository.deleteAll();
    }


    @Test
    void getAllToDos() {
        ToDo newToDo = ToDo.builder()
                .text("New ToDo")
                .completed(false)
                .build();
        toDoRepository.save(newToDo);
        ToDoService toDoService = new ToDoServiceImpl(toDoRepository);
        List<ToDo> toDoList = toDoService.findAll();
        ToDo lastToDo = toDoList.get(toDoList.size()-1);

        assertEquals(newToDo.getText(), lastToDo.getText());
        assertEquals(newToDo.isCompleted(), lastToDo.isCompleted());
        assertEquals(newToDo.getId(), lastToDo.getId());
    }

    @Test
    void save() {
        ToDoService toDoService = new ToDoServiceImpl(toDoRepository);
        ToDo newToDo = ToDo.builder()
                .text("Test save")
                .completed(true).build();
        ToDo response = toDoService.save(newToDo);
        assertEquals(response.getText(), newToDo.getText());
        assertEquals(response.isCompleted(), newToDo.isCompleted());
    }
}
