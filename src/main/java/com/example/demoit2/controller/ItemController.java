package com.example.demoit2.controller;

import com.example.demoit2.entity.items.ItemEntity;
import com.example.demoit2.exception.ItemNotFoundException;
import com.example.demoit2.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<?> getAllItems(){
        return ResponseEntity.ok().body(itemService.getItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id){
        return ResponseEntity.ok().body(itemService.findItemById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getItemsByCollectionId(@RequestParam Long id){
        return ResponseEntity.ok().body(itemService.findAllItemsByCollectionId(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveItem(@RequestBody ItemEntity item){
        return ResponseEntity.ok().body(itemService.saveItem(item));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteItem(@RequestParam Long id){
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editItem(@RequestBody ItemEntity item) {
        try {
            return ResponseEntity.ok().body((itemService.updateItem(item)));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Exception when call editItem: ", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
