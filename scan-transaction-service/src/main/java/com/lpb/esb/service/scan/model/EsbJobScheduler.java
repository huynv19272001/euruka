package com.lpb.esb.service.scan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "MID_JOB_SCHEDULES")
public class EsbJobScheduler {
    @Id
    @Column(name = "JOB_ID")
    private String jobId;
    @Column(name = "JOB_NAME")
    private String jobName;
    @Column(name = "JOB_DESC")
    private String jobDesc;
    @Column(name = "JOB_INTERVAL")
    private String jobInterval;
    @Column(name = "MAKERID")
    private String makeId;
    @Column(name = "CHECKERID")
    private String checkerId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MAKER_DT")
    private Date makeDt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NEXT_TIME")
    private Date nextTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHECKER_DT")
    private Date checkerDt;
    @Column(name = "RECORD_STATS")
    private String recordStats;
    @Column(name = "UDF_1")
    private String udf1;
    @Column(name = "UDF_2")
    private String udf2;
    @Column(name = "UDF_3")
    private String udf3;
    @Column(name = "UDF_4")
    private String udf4;
    @Column(name = "UDF_5")
    private String udf5;
    @Column(name = "UDF_6")
    private String udf6;
    @Column(name = "UDF_7")
    private String udf7;

}
