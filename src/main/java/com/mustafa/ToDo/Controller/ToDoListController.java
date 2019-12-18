package com.mustafa.ToDo.Controller;

import com.mustafa.ToDo.DTO.ItemCreateRequestDTO;
import com.mustafa.ToDo.DTO.ItemUpdateRequestDTO;
import com.mustafa.ToDo.DTO.ToDoListCreateRequestDTO;
import com.mustafa.ToDo.Entity.Item;
import com.mustafa.ToDo.Entity.ToDoList;
import com.mustafa.ToDo.Service.ItemService;
import com.mustafa.ToDo.Service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolists")
public class ToDoListController {
    @Autowired
    private ToDoListService toDoListService;

    @Autowired
    private ItemService itemService;

    @GetMapping()
    public ResponseEntity<Object> getAllToDoLists() {
        ResponseEntity<Object> responseEntity = new ResponseEntity(toDoListService.getAllToDoList(), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ToDoList getToDoListById(@PathVariable Long id, @RequestParam(required = false) String sort, @RequestParam(required = false) String direction) {
        return toDoListService.getToDoListById(id, sort, direction);
    }

    @PostMapping
    public ToDoList createToDoList(@RequestBody ToDoListCreateRequestDTO toDoListCreateRequestDTO) {
        return toDoListService.addToDoList(toDoListCreateRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteToDoList(@PathVariable Long id) {
        toDoListService.deleteToDoList(id);
    }

    @PatchMapping("/{id}")
    public void updateStatusToDoList(@PathVariable Long id){

    }

    @PostMapping("/{toDoListId}/items")
    public Item createItem(@RequestBody ItemCreateRequestDTO itemCreateRequestDTO, @PathVariable Long toDoListId) {
        return itemService.createItem(itemCreateRequestDTO, toDoListId);
    }

    @DeleteMapping("/{toDoListId}/items/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

    @PatchMapping("/{toDoListId}/items/{id}")
    public void updateStatusItem(@PathVariable Long id){
        itemService.updateStatusItem(id);
    }

}
