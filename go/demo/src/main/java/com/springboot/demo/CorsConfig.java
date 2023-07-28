package com.springboot.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // 設置允許跨域請求的路徑
                .allowedOrigins("http://localhost:3000") // 設置允許跨域請求的來源，可以配置多個來源，例如：.allowedOrigins("http://localhost:3000", "http://example.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 設置允許的請求方法
                .allowedHeaders("*") // 設置允許的請求頭
                .allowCredentials(true); // 允許攜帶驗證信息，例如 cookie
    }
}
