package com.lpb.esb.job.manager.service.job;


import com.lpb.esb.job.manager.config.GlobalConfig;
import com.lpb.esb.job.manager.config.StaticApplicationContext;
import com.lpb.esb.job.manager.model.EsbJobScheduler;
import com.lpb.esb.job.manager.service.impl.TransServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.concurrent.TimeUnit;

@Log4j2
public class JobScheduledTasks implements Runnable {
    private final EsbJobScheduler esbJobScheduler;

    public JobScheduledTasks(EsbJobScheduler esbJobScheduler) {
        this.esbJobScheduler = esbJobScheduler;
    }

    @Override
    public void run() {
        String jobId = esbJobScheduler.getJobId();
        log.info("Start job_id: {}", jobId);
        try {
            TransServiceImpl transService = StaticApplicationContext.getContext().getBean(TransServiceImpl.class);
            while (true) {
                log.info("start runnable job {}", jobId);
                transService.debtTrans(esbJobScheduler);
                TimeUnit.SECONDS.sleep(Long.valueOf(esbJobScheduler.getJobInterval()));

                if (!GlobalConfig.MAP_JOB_ACTIVE.containsKey(jobId)) {
                    log.info("Stop job {}", jobId);
                    break;
                }
            }
        } catch (Exception e) {
            log.error("error when run job {}, stop now {}", ExceptionUtils.getStackTrace(e), e);
        }
    }
}
