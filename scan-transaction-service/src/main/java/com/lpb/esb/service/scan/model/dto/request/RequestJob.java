package com.lpb.esb.service.scan.model.dto.request;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class RequestJob {
    private String job_id;
    private String account;
    private String job_name;
}
