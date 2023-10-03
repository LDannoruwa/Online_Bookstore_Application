package com.ijse.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ijse.backend.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    //custom quaries can be added here   

    //1.custom quary : To get book by category id
    @Query("SELECT b FROM Book b WHERE b.category.id = :categoryId ") //b indicates book
    List<Book> findBooksByCategoryId (@Param("categoryId") Long categoryId);

    //2.custom quary : To get book by subcategory id
}
