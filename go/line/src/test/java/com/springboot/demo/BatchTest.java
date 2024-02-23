package com.springboot.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo.batch.MyScheduledJob;
import com.springboot.demo.model.Game;
import com.springboot.demo.service.MybatisService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BatchTest {
//    private RestTemplate restTemplate = new RestTemplate();

    @Mock
    private JobExecutionContext jobExecutionContext;

//    @MockBean
//    private MybatisService mybatisService;
    @Autowired
    private MyScheduledJob myScheduledJob;

    @Test
    public void testExecute() throws Exception {
        // 调用 execute 方法
        myScheduledJob.execute(jobExecutionContext);

    }
}