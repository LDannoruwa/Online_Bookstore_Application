package com.ijse.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.backend.entity.Category;

@Service
public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category createCategory(Category Category);
    Category updateCategory(long id, Category Category);
    void deleteCategory(long id);
}
