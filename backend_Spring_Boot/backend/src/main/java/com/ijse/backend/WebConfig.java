package com.ijse.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//This class is used to show image file using browser
@Configuration
public class WebConfig implements WebMvcConfigurer{

    //Get folder location which is used to store images
    @Value("${upload.directory}")
    private String uploadDirectory;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/imageUpload/***")
				.addResourceLocations("file:"+uploadDirectory+"/");
	}

    
}
