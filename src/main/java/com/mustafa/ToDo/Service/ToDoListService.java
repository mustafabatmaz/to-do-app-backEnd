package com.mustafa.ToDo.Service;

import com.mustafa.ToDo.DTO.ToDoListCreateRequestDTO;
import com.mustafa.ToDo.Entity.ToDoList;

import java.util.List;

public interface ToDoListService {
    ToDoList addToDoList(ToDoListCreateRequestDTO toDoListCreateRequestDTO);
    ToDoList getToDoListById(Long id, String sort, String direction);
    List<ToDoList> getAllToDoList();
    void deleteToDoList(Long id);
//    ToDoList updateStatusToDoList(Long id);

}
