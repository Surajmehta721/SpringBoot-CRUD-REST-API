package com.example.BookStore.controller;

import com.example.BookStore.entity.Book;
import com.example.BookStore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    StoreService service = new StoreService();
    @GetMapping("/getbooks")
    public ResponseEntity<?> showAll()
    {
        return service.getAllBook();
    }
    @GetMapping("/get")
    public ResponseEntity<?> show(@RequestParam Long id)
    {
        return service.getBookById(id);
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Book b)
    {
        return service.add(b);
    }
    @PutMapping("/update/{id}/{title}")
    public ResponseEntity<?> show(@PathVariable Long id, @PathVariable String title)
    {
        return service.updateById(id,title);
    }
    @DeleteMapping("/remove")
    public ResponseEntity<?> remove(@PathVariable Long id)
    {
        return service.deleteById(id);
    }
}
