package com.lpb.esb.service.scan.repository;


import com.lpb.esb.service.scan.model.dto.TransCoreDto;

import java.util.List;

public interface CoreRepository {
    List<TransCoreDto> callGetTrans(String accNo, String nextDate);
}
