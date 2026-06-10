package com.example.BookStore.service;

import com.example.BookStore.entity.Book;
import com.example.BookStore.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    @Autowired
    Repo db;

    public ResponseEntity<?> getAllBook()
    {
        List<Book> l= db.findAll();
        if(l.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(l);
    }
    public ResponseEntity<?> getBookById(Long id)
    {
        Optional<Book> op= db.findById(id);
        if(op.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(op.get());
    }
    public ResponseEntity<?> updateById(Long id, String title)
    {
        Optional<Book> op= db.findById(id);
        if(op.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Book b=op.get();
        b.setTitle(title);
        db.save(b);
        return ResponseEntity.status(HttpStatus.OK).body(b);
    }
    public ResponseEntity<?> deleteById(Long id)
    {
        Optional<Book> op= db.findById(id);
        if(op.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        db.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(op.get());
    }

    public ResponseEntity<?> add(Book book)
    {
        Optional<Book> op= db.findById(book.getId());
        if(op.isEmpty()){
            db.save(book);
            return ResponseEntity.status(HttpStatus.OK).body(book);}
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
