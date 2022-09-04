package com.example.demoit2.service.impl;

import com.example.demoit2.entity.items.ItemEntity;
import com.example.demoit2.exception.ItemNotFoundException;
import com.example.demoit2.repository.ItemRepository;
import com.example.demoit2.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Optional<ItemEntity> findItemById(Long id) {
        log.info("Item with id:{}", id);
        return itemRepository.findById(id);
    }

    @Override
    public ItemEntity saveItem(ItemEntity item) {
        log.info("Saving new item:{} to database", item.getItemName());
        return itemRepository.save(item);
    }

    @Override
    public List<ItemEntity> getItems() {
        log.info("Getting all items");
        return itemRepository.findAll();
    }

    @Override
    public void deleteItem(Long id) {
        log.info("Item with id:{} deleted from the database", id);
        itemRepository.deleteById(id);
    }

    @Override
    public List<ItemEntity> findAllItemsByCollectionId(Long id) {
        log.info("Items by collection id:{}", id);
        return itemRepository.findAllByCollectionsId(id);
    }

    @Override
    public Optional<ItemEntity> updateItem(ItemEntity item) {
        itemRepository.findById(item.getId())
                .orElseThrow(() -> new ItemNotFoundException("Item not found in database"));
        return Optional.of(this.saveItem(item));
    }
}
