package com.lpb.esb.service.common.model.request.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTransactionDTO {
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("value_dt")
    private Date valueDt;
    @JsonProperty("core_ref_no")
    private String coreRefNo;
    @JsonProperty("error_code")
    private String errorCode;
    @JsonProperty("error_desc")
    private String errorDesc;
}
