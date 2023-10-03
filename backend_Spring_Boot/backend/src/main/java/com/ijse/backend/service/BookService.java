package com.ijse.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.backend.dto.BookCoverDto;
import com.ijse.backend.entity.Book;

@Service
public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book createBook(Book book);
    Book updateBook(long id, Book book);
    //---
    Book updateBookCoverImage(long id, BookCoverDto bookCoverDto);
    //--

    void deleteBook(long id);

    //---custom
    List<Book> getBooksByCategoryId(Long categoryId);

    //getBookBySubVategoryId()
}
