package com.example.demoit2.service;

import com.example.demoit2.entity.items.ItemEntity;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Optional<ItemEntity> findItemById(Long id);

    ItemEntity saveItem(ItemEntity item);

    List<ItemEntity> getItems();

    void deleteItem(Long id);

    List<ItemEntity> findAllItemsByCollectionId(Long id);

    Optional<ItemEntity> updateItem(ItemEntity item);
}
