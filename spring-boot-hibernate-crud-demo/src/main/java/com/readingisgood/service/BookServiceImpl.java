package com.readingisgood.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readingisgood.dto.BookDTO;
import com.readingisgood.exception.RecordNotFoundException;
import com.readingisgood.model.BookEntity;
import com.readingisgood.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ObjectMapper objectMapper;



    @Override
    public BookEntity createBook(BookDTO bookDTO) throws EntityExistsException {
        // valid mi ?
        BookEntity bookEntity = objectMapper.convertValue(bookDTO, BookEntity.class);
        return bookRepository.save(bookEntity);
    }

    @Override
    public BookEntity updateBook(Long id, Long stock) throws RecordNotFoundException {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new RecordNotFoundException("No book record exist for given id");
        } else {
            BookEntity entity = book.get();
            entity.setQuantityInStock(stock);
            return bookRepository.save(entity);
        }
    }

}
