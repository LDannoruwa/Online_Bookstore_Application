package com.ijse.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.backend.entity.SubCategory;

@Service
public interface SubCategoryService {
    List<SubCategory> getAllSubCategories();
    SubCategory getSubCategoryById(Long id);
    SubCategory createSubCategory(SubCategory subCategory);
    SubCategory updateSubCategory(long id, SubCategory subCategory);
    void deleteSubCategory(long id);
}
