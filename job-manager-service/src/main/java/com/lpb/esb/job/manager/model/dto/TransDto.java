package com.lpb.esb.job.manager.model.dto;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class TransDto {
    private String job_id;
    private String trn_ref_no;
    private String acc_no;
    private String trn_desc;
    private String value_dt;
    private String scan_dt;
    private String trn_dt;
    private String record_stats;
    private String udf_1;
    private String udf_2;
    private String udf_3;
    private String udf_4;
    private String lcy_amount;
    private String source_ac_no;
    private String ac_desc;
}
