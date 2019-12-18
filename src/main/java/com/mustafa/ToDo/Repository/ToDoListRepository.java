package com.mustafa.ToDo.Repository;

import com.mustafa.ToDo.Entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
 }
