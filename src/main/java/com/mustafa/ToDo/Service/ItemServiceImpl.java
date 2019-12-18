package com.mustafa.ToDo.Service;

import com.mustafa.ToDo.DTO.ItemCreateRequestDTO;
import com.mustafa.ToDo.DTO.ItemUpdateRequestDTO;
import com.mustafa.ToDo.Entity.Item;
import com.mustafa.ToDo.Entity.ToDoList;
import com.mustafa.ToDo.Enum.Status;
import com.mustafa.ToDo.Repository.ItemRepository;
import com.mustafa.ToDo.Repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Override
    public Item createItem(ItemCreateRequestDTO itemCreateRequestDTO, long todolistid) {

        ToDoList toDoList = toDoListRepository.findById(todolistid)
                .orElseThrow(() -> new RuntimeException("To-Do list not found"));
        Item item = new Item();
        item.setName(itemCreateRequestDTO.getName());
        item.setDescription(itemCreateRequestDTO.getDescription());
        item.setDeadline(LocalDateTime.now().plusDays(itemCreateRequestDTO.getDeadline()));
        item.setStatus(Status.NOT_COMPLETED);
        item.setToDoList(toDoList);
        itemRepository.save(item);
        return item;
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void updateStatusItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item failed to change status"));
        if (item.getStatus() == Status.NOT_COMPLETED) {
            item.setStatus(Status.COMPLETED);
        } else if (item.getStatus() == Status.COMPLETED) {
            item.setStatus(Status.NOT_COMPLETED);
        }
        itemRepository.save(item);
    }
}
