package com.opentuter.profileservice.config;


import com.opentuter.profileservice.util.Utils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //**
    // marks this class as a spring configuration file
    // Loaded when the application starts
    // */
    public void addResourceHandler(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler(Utils.ACCEPT_IMG_FORMAT)
                .addResourceLocations(Utils.UPLOAD_DIR);
    }
}

//**
// this file allows to the front end access
// image file stored on our server folder (uploads) via URL
// ceacuse spring boot can't show image stored in your local folder
//
// important thing is webConfig maps a URL path to a folder path/