package com.lpb.esb.job.manager.service.impl;


import com.lpb.esb.job.manager.config.RestTemplateConfig;
import com.lpb.esb.job.manager.model.EsbJobScheduler;
import com.lpb.esb.job.manager.model.dto.request.BaseRequestBody;
import com.lpb.esb.job.manager.model.dto.request.RequestJob;
import com.lpb.esb.job.manager.service.TransService;
import com.lpb.esb.job.manager.utils.ConvertUtils;
import com.lpb.esb.job.manager.utils.Generate;
import com.lpb.esb.service.common.model.request.HeaderInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;


@Service
public class TransServiceImpl implements TransService {
    private static final Logger logger = LoggerFactory.getLogger(TransServiceImpl.class);
    @Autowired
    RestTemplateConfig restTemplate;

    @Override
    public void debtTrans(EsbJobScheduler esbJobSchedulers) {
        BaseRequestBody requestBody = new BaseRequestBody();
        HeaderInfoDTO headerInfoDTO = new HeaderInfoDTO();
        headerInfoDTO.setMsgId(Generate.generateMsg());
        RequestJob requestJob = new RequestJob();
        requestJob.setJob_id(esbJobSchedulers.getJobId());
        requestJob.setJob_name(esbJobSchedulers.getJobName());
        requestJob.setAccount(esbJobSchedulers.getUdf_1());
        requestBody.setBody(requestJob);
        requestBody.setHeader(headerInfoDTO);
        try {
            String request = ConvertUtils.convertObjectToJson(requestBody);
            logger.info("debtTrans : request scan job {}",request);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);
//             restTemplate.getRestTemplate()
//                .exchange(esbJobSchedulers.getUdf_2(),
//                HttpMethod.POST, httpEntity, String.class);
//             logger.info("debtTrans : scan transaction success by jobId : {}",esbJobSchedulers.getJobId());
        } catch (Exception e) {
            logger.error("debtTrans : scan job fail ---> {}", e.getMessage());
        }
    }


}
