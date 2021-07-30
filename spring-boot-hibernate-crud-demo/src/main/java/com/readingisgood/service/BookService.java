package com.readingisgood.service;

import com.readingisgood.dto.BookDTO;
import com.readingisgood.exception.RecordNotFoundException;
import com.readingisgood.model.BookEntity;

import javax.persistence.EntityExistsException;


public interface BookService {

     BookEntity createBook(BookDTO entity) throws EntityExistsException;

     BookEntity updateBook( Long id,  Long stock) throws RecordNotFoundException;
}
