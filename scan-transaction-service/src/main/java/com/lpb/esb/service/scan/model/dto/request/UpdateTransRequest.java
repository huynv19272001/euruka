package com.lpb.esb.service.scan.model.dto.request;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTransRequest {
    private String status;
    private String trnAccount;
    private String date;
}
