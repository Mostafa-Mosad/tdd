package com.example.dtt;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;
    @Override
    public List<ToDo> findAll() {
        return toDoRepository.findAll();
    }

    @Override
    public ToDo save(ToDo toDo) {
        return toDoRepository.save(toDo);
    }
}
