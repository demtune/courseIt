package com.example.demoit2.service;

import com.example.demoit2.entity.items.ItemEntity;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Optional<?> findItemById(Long id);

    Optional<?> saveItem(ItemEntity item);

    Optional<?> getItems();

    void deleteItem(Long id);

    List<ItemEntity> findAllItemsByCollectionId(Long id);

    Optional<?> updateItem(ItemEntity item);
}
