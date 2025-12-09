package com.groove.concert_appetizer.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // 모든 Origin 허용
                .allowedMethods("*") // GET, POST 등 모두 허용
                .allowedHeaders("*") // 모든 헤더 허용 (ngrok 헤더 포함)
                .maxAge(3600);
    }
}
