package com.example.dtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @PostMapping("/add-todo")
    public ResponseEntity<ToDo> save(@RequestBody ToDo toDo) {
        return new ResponseEntity<ToDo>(toDoService.save(toDo), HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ToDo>> getAllToDos() {
        List<ToDo> toDoList = toDoService.findAll();
        return new ResponseEntity<List<ToDo>>(toDoList, HttpStatus.OK);
    }
}
