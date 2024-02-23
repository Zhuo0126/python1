package com.springboot.demo.config;

import com.springboot.demo.batch.MyScheduledJob;
import com.springboot.demo.batch.MyScheduledJobTest;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.web.client.RestTemplate;

@Configuration
public class QuartzConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public static JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(MyScheduledJob.class);
        jobDetailFactoryBean.setDescription("My Quartz Job");
        jobDetailFactoryBean.setDurability(true);
        return jobDetailFactoryBean;
    }

    @Bean
    public static CronTriggerFactoryBean trigger(JobDetail jobDetail) {
        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
        triggerFactoryBean.setJobDetail(jobDetail);
        // 每天六点执行的 cron 表达式
        triggerFactoryBean.setCronExpression("0 15 11 * * ?");
        return triggerFactoryBean;
    }
//    @Bean
//    public static SimpleTriggerFactoryBean trigger(JobDetail jobDetail) {
//        SimpleTriggerFactoryBean triggerFactoryBean = new SimpleTriggerFactoryBean();
//        triggerFactoryBean.setJobDetail(jobDetail);
//        triggerFactoryBean.setStartDelay(0);
//        triggerFactoryBean.setRepeatInterval(60000); // 任务执行间隔，这里设置为一分钟
//        return triggerFactoryBean;
//    }
}
