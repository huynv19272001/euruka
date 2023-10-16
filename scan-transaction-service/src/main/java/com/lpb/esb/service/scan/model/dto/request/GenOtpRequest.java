package com.lpb.esb.service.scan.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenOtpRequest {
    @JsonProperty("partner_id")
    private String partner_id;
    @JsonProperty("application_id")
    private String application_id;
    @JsonProperty("trans_id")
    private String trans_id;
}
