package com.lpb.esb.job.manager.repository;

import com.lpb.esb.job.manager.model.EsbJobScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EsbJobSchedulerRepository extends JpaRepository<EsbJobScheduler, String> {
    List<EsbJobScheduler> findByUdf5AndRecordStatsAndType(String status, String recordStats,String type);
}
