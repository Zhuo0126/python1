package com.springboot.demo.util.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TimingInterceptor implements HandlerInterceptor {

    // 在處理器執行前調用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }

    // 在處理器執行後和視圖返回前調用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;

        // 修改 modelAndView，增加處理時間
        if (modelAndView != null) {
            modelAndView.addObject("executeTime", executeTime);
        }

        System.out.println("[" + handler + "] executeTime : " + executeTime + "ms");
    }

    // 在請求完全結束後調用，主要用於清理工作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 可以用於清理資源，或處理異常
    }
}