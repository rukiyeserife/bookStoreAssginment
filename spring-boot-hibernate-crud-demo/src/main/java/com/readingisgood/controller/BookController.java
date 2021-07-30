package com.readingisgood.controller;

import com.readingisgood.dto.BookDTO;
import com.readingisgood.exception.RecordNotFoundException;
import com.readingisgood.model.BookEntity;
import com.readingisgood.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    @PostMapping
    public ResponseEntity<BookEntity> createBook(@RequestBody BookDTO bookDTO)
    {
        BookEntity book = bookService.createBook(bookDTO);
        return new ResponseEntity<BookEntity>(book, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/{stock}")
    public ResponseEntity<BookEntity> updateBookStock(@PathVariable("id") Long id,@PathVariable Long stock) throws RecordNotFoundException {
        BookEntity book = bookService.updateBook(id,stock);
        return new ResponseEntity<BookEntity>(book, new HttpHeaders(), HttpStatus.OK);
    }


}
