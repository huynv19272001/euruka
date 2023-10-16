package com.lpb.mid.dvc.model.request;

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
