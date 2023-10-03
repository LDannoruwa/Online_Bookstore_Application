package com.ijse.backend.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.backend.dto.BookCoverDto;
import com.ijse.backend.entity.Book;
import com.ijse.backend.service.BookService;

@CrossOrigin(origins = "*")

@RestController
//we don't need RequestMapping() here, because we use custom mapping here
public class BookController {
    
    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBook(){
        List<Book> book = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id){
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.status(HttpStatus.OK).body(book);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        try {
            Book bookControll = bookService.createBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookControll);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book book){
        try {
            Book updateBook = bookService.updateBook(id, book);
            return ResponseEntity.status(HttpStatus.OK).body(updateBook);
        } catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/books/{id}/cover")
    public ResponseEntity<Book> updateBookCoverImage(@PathVariable long id, @ModelAttribute BookCoverDto bookCoverDto){
        return new ResponseEntity<Book>(bookService.updateBookCoverImage(id, bookCoverDto), HttpStatus.OK);
    }
    
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id){
        try {
            bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //---custom
    @GetMapping("/categories/{categoryId}/books")
    public ResponseEntity<List<Book>> getBooksByCategoryId(@PathVariable Long categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBooksByCategoryId(categoryId));
    }

    //getBooksBySubCategoryId()
}
