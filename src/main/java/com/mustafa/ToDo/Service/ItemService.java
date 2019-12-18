package com.mustafa.ToDo.Service;

import com.mustafa.ToDo.DTO.ItemCreateRequestDTO;
import com.mustafa.ToDo.DTO.ItemUpdateRequestDTO;
import com.mustafa.ToDo.Entity.Item;

public interface ItemService {
    Item createItem(ItemCreateRequestDTO itemCreateRequestDTO, long todolistid);
    void deleteItem(Long id);
    void updateStatusItem(Long id );

}
