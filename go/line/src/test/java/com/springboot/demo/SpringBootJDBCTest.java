package com.springboot.demo;

import com.springboot.demo.impl.MQServiceImpl;
import com.springboot.demo.util.SpringBeanFactoryUtil;
import com.springboot.demo.util.interceptors.TimingInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SpringBootJDBCTest {
//    @Autowired(required = false)
//    private MQServiceImpl mqService;
//
//    @Test
//    public void testJMS() throws Exception {
//        //測試Active MQ收送
//        mqService.sendMessage("TestQueue","20231220");
//    }

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Object handler;

    private TimingInterceptor timingInterceptor;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        timingInterceptor = new TimingInterceptor();
    }

    @Test
    void testPreHandle() throws Exception {
        when(request.getAttribute("startTime")).thenReturn(System.currentTimeMillis());

        boolean result = timingInterceptor.preHandle(request, response, handler);

        verify(request, times(1)).setAttribute(eq("startTime"), anyLong());
        assertTrue(result);
    }

    @Test
    void testPostHandle() throws Exception {
        when(request.getAttribute("startTime")).thenReturn(System.currentTimeMillis());

        timingInterceptor.postHandle(request, response, handler, new ModelAndView());

        verify(request, times(1)).getAttribute("startTime");
        // 更多的验证可以添加在这里，例如检查 modelAndView 是否包含了正确的属性
    }

    @Test
    void testAfterCompletion() {
        // 在这里添加对 afterCompletion 的测试
    }
}