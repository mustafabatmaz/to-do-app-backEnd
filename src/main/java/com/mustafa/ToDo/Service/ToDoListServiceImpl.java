package com.mustafa.ToDo.Service;

import com.mustafa.ToDo.DTO.ToDoListCreateRequestDTO;
import com.mustafa.ToDo.Entity.ToDoList;
import com.mustafa.ToDo.Entity.User;
import com.mustafa.ToDo.Enum.Status;
import com.mustafa.ToDo.Repository.ItemRepository;
import com.mustafa.ToDo.Repository.ToDoListRepository;
import com.mustafa.ToDo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;


@Service
public class ToDoListServiceImpl implements ToDoListService {

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public ToDoList addToDoList(ToDoListCreateRequestDTO toDoListCreateRequestDTO) {
        User loggedinUser = (User) httpServletRequest.getAttribute("user");

        ToDoList toDoList = new ToDoList();
        toDoList.setOwner(loggedinUser);
        toDoList.setName(toDoListCreateRequestDTO.getName());
        toDoList.setStatus(Status.NOT_COMPLETED);
        toDoListRepository.save(toDoList);
        return toDoList;
    }

    @Override
    public ToDoList getToDoListById(Long id, String sortString, String direction) {
        ToDoList toDoList = toDoListRepository.findById(id).orElseThrow(() -> new RuntimeException("To-Do List not found"));

        List<String> allowedSortFields = Arrays.asList("name", "deadline", "status", "createDateTime");
        if (null != sortString && allowedSortFields.contains(sortString)) {
            Sort sort = "asc".equals(direction) ? Sort.by(sortString).ascending() : Sort.by(sortString).descending();
            toDoList.setItems(itemRepository.findAllByToDoList_Id(id, sort));
        } else {
            toDoList.setItems(itemRepository.findAllByToDoList_Id(id, Sort.by("id")));
        }

        return toDoList;
    }

    @Override
    public List<ToDoList> getAllToDoList() {
        User loggedinUser = (User) httpServletRequest.getAttribute("user");
        return loggedinUser.getToDoLists();
    }

    @Override
    public void deleteToDoList(Long id) {
        itemRepository.deleteAllByToDoList_Id(id);
        toDoListRepository.deleteById(id);
    }

}
