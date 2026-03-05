package com.opentuter.profileservice.config;


import com.opentuter.profileservice.util.Utils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addResourceHandler(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler(Utils.ACCEPT_IMG_FORMAT)
                .addResourceLocations(Utils.UPLOAD_DIR);
    }
}

//Use util package for hardcoded stuff
