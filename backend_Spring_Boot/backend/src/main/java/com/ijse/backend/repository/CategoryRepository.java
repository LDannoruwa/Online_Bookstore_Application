package com.ijse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.backend.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //custom quaries can be added here
}
