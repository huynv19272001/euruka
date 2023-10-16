package com.lpb.esb.service.scan.repository;


import com.lpb.esb.service.scan.model.dto.EsbJobTransaction;

import java.util.List;

public interface GetTransCoreRepository {
    List<EsbJobTransaction> getUpdateTrans(String jobId, String xmlTrans);
}
