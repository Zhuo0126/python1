//package com.springboot.demo;
//
//import com.springboot.demo.model.ApiResponse;
//import com.springboot.demo.model.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//@AutoConfigureMockMvc
//@SpringBootTest()
//public class SpringBootJDBCTest {
////    数据源
//    @Autowired
//    DataSource dataSource;
//    //用于访问数据库的组件
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void printData() throws SQLException {
//        System.out.println("默认数据源为：" + dataSource.getClass());
//        System.out.println("数据库连接实例：" + dataSource.getConnection());
//        //访问数据库user表，查询user表的数据量
//        Integer i = jdbcTemplate.queryForObject("SELECT count(*) from `user`", Integer.class);
//        System.out.println("user 表中共有" + i + "条数据。");
//    }
//
//    @Test
//    public void testLoginSuccess() throws Exception {
//        String json = "{\"username\":\"admin\",\"password\":\"password\"}";
//
//         ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json));
//        MvcResult result = action.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//        byte[] responseBytes = result.getResponse().getContentAsByteArray();
//
//        String responseContent = new String(responseBytes, "UTF-8");
//
//    }
//}