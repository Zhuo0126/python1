package com.springboot.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.springboot.demo.mapper.*", sqlSessionTemplateRef = "mySqlSessionTemplate")
public class MyBatisConfig {

    private final DataSource dataSource;

    @Autowired
    public MyBatisConfig(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SqlSessionFactory mySqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/springboot/demo/mapper/*.xml"));
        return sessionFactoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager myTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate mySqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(mySqlSessionFactory());
    }
}
