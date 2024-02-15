//package com.springboot.demo.config;
//
//import com.springboot.demo.util.interceptors.TimingInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TimingInterceptor());
//        // 您可以添加其他攔截器或配置現有攔截器的映射
//    }
//}