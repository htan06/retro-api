package com.retro.api.config.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary.cloud-name}")
    private String CLOUD_NAME;

    @Value("${cloudinary.api-key}")
    private String CLOUD_API_KEY;

    @Value("${cloudinary.api-secret-key}")
    private String CLOUD_API_SECRET_KEY;

    @Bean
    public Cloudinary getCloudinary() {
        Map config = ObjectUtils.asMap(
                "cloud_name", CLOUD_NAME,
                "api_key", CLOUD_API_KEY,
                "api_secret", CLOUD_API_SECRET_KEY,
                "secure", true
        );
        return new Cloudinary(config);
    }
}
