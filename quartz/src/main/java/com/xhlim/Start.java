package com.xhlim;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by xhlim on 2017/5/23.
 */
public class Start {

    static Logger _log = LoggerFactory.getLogger(Start.class);


    public static void main(String[] args) {
        // 获取Scheduler实例
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            // 具体任务
            JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("任务1", "组1").build();

            // 触发时间点
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                    .startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                    .build();

            // 交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);

            // 为观察程序运行，此设置主程序睡眠3分钟才继续往下运行（因下一个步骤是“关闭Scheduler”）
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            // 关闭Scheduler
            scheduler.shutdown();
        } catch (SchedulerException e) {
            _log.error(e.getMessage());
            e.printStackTrace();
        }

    }

}
