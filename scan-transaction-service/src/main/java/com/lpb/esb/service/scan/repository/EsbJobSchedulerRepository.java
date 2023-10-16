package com.lpb.esb.service.scan.repository;

import com.lpb.esb.service.scan.model.EsbJobScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsbJobSchedulerRepository extends JpaRepository<EsbJobScheduler,String> {
    EsbJobScheduler findFirstByJobIdAndJobName(String jobId,String jobName);
}
