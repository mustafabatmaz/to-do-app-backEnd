package com.mustafa.ToDo.Repository;

import com.mustafa.ToDo.Entity.Item;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    void deleteAllByToDoList_Id(Long id);
    List<Item> findAllByToDoList_Id(Long id);

    List<Item> findAllByToDoList_Id(Long id, Sort sort);
}
