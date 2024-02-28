package com.swp.birthdaybooking.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudConfig {




    @Bean
    public Cloudinary getCloudinary() {
        return new Cloudinary(Map.of(
                "cloud_name", "",
                "api_key", "",
                "api_secret", "",
                "secure", true
        ));
    }
}
