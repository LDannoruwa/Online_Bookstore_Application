package com.ijse.backend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.backend.entity.SubCategory;
import com.ijse.backend.repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Override
    public List<SubCategory> getAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory getSubCategoryById(Long id) {
        return subCategoryRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Subcategory is not found" + id));
    }

    @Override
    public SubCategory createSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory updateSubCategory(long id, SubCategory subCategory) {
        SubCategory existingSubCategory = getSubCategoryById(id);

        existingSubCategory.setId(subCategory.getId());
        existingSubCategory.setName(subCategory.getName());

        return subCategoryRepository.save(existingSubCategory);
    }

    @Override
    public void deleteSubCategory(long id) {
        subCategoryRepository.deleteById(id);
    }
    
}
