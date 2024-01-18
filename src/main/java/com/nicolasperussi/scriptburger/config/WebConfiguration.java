package com.nicolasperussi.scriptburger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(@NonNull CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins("http://localhost:3001", "http://localhost:4200").allowedMethods("*");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/images/**")
        .addResourceLocations("file:images/");
  }
}
