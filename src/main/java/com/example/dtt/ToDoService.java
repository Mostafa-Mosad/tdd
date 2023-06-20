package com.example.dtt;

import com.example.dtt.ToDo;

import java.util.List;

public interface ToDoService {
    List<ToDo> findAll();

    ToDo save(ToDo toDo);
}
