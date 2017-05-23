package com.xhlim;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xhlim on 2017/5/23.
 */
public class HelloJob implements Job {

    static Logger _log = LoggerFactory.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 此任务仅打印日志便于调试、观察
        _log.debug(this.getClass().getName() + " trigger...");
    }
}
