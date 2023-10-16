package com.lpb.esb.job.manager.model.dto.request;

import com.lpb.esb.service.common.model.request.HeaderInfoDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class BaseRequestBody {
    HeaderInfoDTO header;
    RequestJob body;
}
