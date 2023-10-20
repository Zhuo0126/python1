package com.springboot.demo.service;

import com.springboot.demo.config.QuartzConfig;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartzService {
    private final Scheduler scheduler;

    @Autowired
    public QuartzService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void scheduleJob() throws SchedulerException {
        // 检查触发器是否存在，如果存在则删除
        TriggerKey triggerKey = TriggerKey.triggerKey("myTrigger");
        if (scheduler.checkExists(triggerKey)) {
            scheduler.unscheduleJob(triggerKey);
        }

        // 调度任务
//        scheduler.scheduleJob(QuartzConfig.jobDetail().getObject(), QuartzConfig.trigger(QuartzConfig.jobDetail().getObject()).getObject());
    }
}
