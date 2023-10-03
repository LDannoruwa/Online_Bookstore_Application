package com.ijse.backend.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.backend.entity.SubCategory;
import com.ijse.backend.service.SubCategoryService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/subcategories")
public class SubCategoryController {
    
    @Autowired
    SubCategoryService subCategoryService;
    
    @GetMapping
    public ResponseEntity<List<SubCategory>> getAllSubCategory(){
        List<SubCategory> subCategory = subCategoryService.getAllSubCategories();
        return ResponseEntity.status(HttpStatus.OK).body(subCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable long id){
        try {
            SubCategory subCategory = subCategoryService.getSubCategoryById(id);
            return ResponseEntity.status(HttpStatus.OK).body(subCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<SubCategory> createSubCategory(@RequestBody SubCategory subCategory){
        try {
            SubCategory subCategoryControll = subCategoryService.createSubCategory(subCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(subCategoryControll);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable long id, @RequestBody SubCategory subCategory){
        try {
            SubCategory updateSubCategory = subCategoryService.updateSubCategory(id, subCategory);
            return ResponseEntity.status(HttpStatus.OK).body(updateSubCategory);
        } catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable long id){
        try {
            subCategoryService.deleteSubCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
