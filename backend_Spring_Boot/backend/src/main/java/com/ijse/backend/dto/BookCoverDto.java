package com.ijse.backend.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCoverDto {
    
    //this will not mapped to table
    private MultipartFile bookImageFile; //store image file
    
}
