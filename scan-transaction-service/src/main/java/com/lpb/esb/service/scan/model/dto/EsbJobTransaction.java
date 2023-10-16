package com.lpb.esb.service.scan.model.dto;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class EsbJobTransaction {

    private String jobId;
    private String trnRefNo;
    private String accNo;
    private String trnDesc;
    private String valueDt;
    private String scanDt;
    private String trnDt;
    private String recordStats;
    private String lcyAmount;
    private String sourceAcNo;
    private String udf1;
    private String udf2;
    private String udf3;
    private String udf4;
    private String acDesc;
}
