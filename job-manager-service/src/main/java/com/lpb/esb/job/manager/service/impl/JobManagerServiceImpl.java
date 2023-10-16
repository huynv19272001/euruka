package com.lpb.esb.job.manager.service.impl;

import com.lpb.esb.job.manager.config.GlobalConfig;
import com.lpb.esb.job.manager.model.EsbJobScheduler;
import com.lpb.esb.job.manager.repository.EsbJobSchedulerRepository;
import com.lpb.esb.job.manager.service.JobManagerService;
import com.lpb.esb.job.manager.service.job.JobScheduledTasks;
import com.lpb.esb.job.manager.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class JobManagerServiceImpl implements JobManagerService {

    @Value("${dbConfig.type}")
    private String type;
    private static final Logger logger = LoggerFactory.getLogger(JobManagerServiceImpl.class);

    private final EsbJobSchedulerRepository esbJobSchedulerRepository;

    public JobManagerServiceImpl(EsbJobSchedulerRepository esbJobSchedulerRepository) {
        this.esbJobSchedulerRepository = esbJobSchedulerRepository;
    }

    @Scheduled(fixedDelay = 1000 * 60)
    @Async("threadSchedulerExecutor")
    @Override
    public void getJobTrans(){
        List<EsbJobScheduler> listAll = esbJobSchedulerRepository.findByUdf5AndRecordStatsAndType(Constants.SCHEDULER_JOB_ACTIVE, "O",type);
        logger.info("JOB ACTIVE size: {}", listAll.size());
        if (listAll.size() == 0) {
            GlobalConfig.MAP_JOB_ACTIVE.clear();
            return;
        }

        List<EsbJobScheduler> listJobCreate = new ArrayList<>();
        for (EsbJobScheduler job : listAll) {
            if (!GlobalConfig.MAP_JOB_ACTIVE.containsKey(job.getJobId())) {
                listJobCreate.add(job);
            }
        }

        // init list job running
        GlobalConfig.MAP_JOB_ACTIVE.clear();
        for (EsbJobScheduler job : listAll) {
            GlobalConfig.MAP_JOB_ACTIVE.put(job.getJobId(), job.getJobName());
        }

        for (EsbJobScheduler esbJobScheduler : listJobCreate) {
            JobScheduledTasks jobScheduledTasks = new JobScheduledTasks(esbJobScheduler);
            Thread thread = new Thread(jobScheduledTasks, "th_job_" + esbJobScheduler.getJobId());
            thread.start();
        }
    }
}
