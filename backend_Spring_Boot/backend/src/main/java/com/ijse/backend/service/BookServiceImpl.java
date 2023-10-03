package com.ijse.backend.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ijse.backend.dto.BookCoverDto;
import com.ijse.backend.entity.Book;
import com.ijse.backend.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    //Get folder location which is used to store images
    @Value("${upload.directory}")
    private String uploadDirectory;
    
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Book is not found" + id));
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(long id, Book book) {
        Book existingBook = getBookById(id);

        existingBook.setId(book.getId());
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setDescription(book.getDescription());
        existingBook.setUnitPrice(book.getUnitPrice());
        existingBook.setQoh(book.getQoh());
        
        return bookRepository.save(existingBook);
    }

    @Override
    public Book updateBookCoverImage(long id, BookCoverDto bookCoverDto) {
        Book existingBook = getBookById(id);

        if(existingBook != null) {
            
            MultipartFile file = bookCoverDto.getBookImageFile();
            String filename = file.getOriginalFilename();
            String filePath = uploadDirectory + File.separator + filename;

            try {
                file.transferTo(new File(filePath));
            } catch (IllegalStateException e) {
                
                e.printStackTrace();
            } catch (IOException e) {
                
                e.printStackTrace();
            }

            existingBook.setBookImageName(filename);
            return bookRepository.save(existingBook);
        }

        return null;
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    //---custom
    @Override
    public List<Book> getBooksByCategoryId(Long categoryId) {
        return bookRepository.findBooksByCategoryId(categoryId);
    }

    //getBookBySubVategoryId()
}
