package com.lpb.esb.service.scan.model.dto;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class TransCoreDto {
    private String trnRefNo;
    private String lcyAmount;
    private String acNo;
    private String valueDt;
    private String trnDt;
    private String amountTag;
    private String sourceAcc;
    private String acDesc;
    private String trnDesc;
}
