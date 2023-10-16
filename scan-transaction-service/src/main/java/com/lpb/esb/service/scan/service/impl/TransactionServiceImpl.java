package com.lpb.esb.service.scan.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lpb.esb.service.common.model.request.BodyInfoDTO;
import com.lpb.esb.service.common.model.request.HeaderInfoDTO;
import com.lpb.esb.service.common.model.response.LpbResCode;
import com.lpb.esb.service.common.model.response.ResponseModel;
import com.lpb.esb.service.common.utils.code.ErrorMessage;
import com.lpb.esb.service.otp.model.dto.request.GenOtpRequest;
import com.lpb.esb.service.otp.model.dto.request.OtpRequest;
import com.lpb.esb.service.otp.service.TransactionDvcServiceOtp;
import com.lpb.esb.service.otp.service.impl.OtpServiceImpl;
import com.lpb.esb.service.scan.config.RestTemplateConfig;
import com.lpb.esb.service.scan.mapper.RequestMapper;
import com.lpb.esb.service.scan.model.EsbJobScheduler;
import com.lpb.esb.service.scan.model.dto.EsbJobTransaction;
import com.lpb.esb.service.scan.model.dto.TransCoreDto;
import com.lpb.esb.service.scan.model.dto.request.BaseRequestBody;
import com.lpb.esb.service.scan.repository.EsbJobSchedulerRepository;
import com.lpb.esb.service.scan.repository.TransServiceRepository;
import com.lpb.esb.service.scan.repository.GetTransCoreRepository;
import com.lpb.esb.service.scan.repository.CoreRepository;
import com.lpb.esb.service.scan.service.TransactionService;
import com.lpb.esb.service.scan.utils.ConvertUtils;
import com.lpb.mid.dvc.serviceMethod.TransactionDvcService;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    final RestTemplateConfig restTemplate;
    private static final Logger logger = LogManager.getLogger(TransactionServiceImpl.class);
    private final EsbJobSchedulerRepository esbJobSchedulerRepository;
    private final CoreRepository coreRepository;
    private final GetTransCoreRepository getTransCoreRepository;
    private final RequestMapper requestMapper;
    private final TransServiceRepository transService;

    public TransactionServiceImpl(EsbJobSchedulerRepository esbJobSchedulerRepository, CoreRepository coreRepository, GetTransCoreRepository getTransCoreRepository, RequestMapper requestMapper, RestTemplateConfig restTemplate, TransServiceRepository transService) {
        this.esbJobSchedulerRepository = esbJobSchedulerRepository;
        this.coreRepository = coreRepository;
        this.getTransCoreRepository = getTransCoreRepository;
        this.requestMapper = requestMapper;
        this.restTemplate = restTemplate;
        this.transService = transService;
    }
    @SneakyThrows
    @Override
    public String transDebt() {
        String a = "huy abc ";
        String b = TransactionDvcService.initTransaction(" huhuhuhu");
        com.lpb.esb.service.scan.model.dto.request.GenOtpRequest request = new com.lpb.esb.service.scan.model.dto.request.GenOtpRequest();
        request.setApplication_id("ESB");
        request.setTrans_id("19271104");
        request.setPartner_id("KHONGMUACUOITUAN");
        HeaderInfoDTO headerInfoDTO = new HeaderInfoDTO();
        headerInfoDTO.setMsgId("14855952");
        BodyInfoDTO bodyInfoDTO = new BodyInfoDTO();
        bodyInfoDTO.setData(request);
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setHeader(headerInfoDTO);
        otpRequest.setBody(bodyInfoDTO);
        OtpServiceImpl.createOtp(otpRequest);
        return a + b;
    }

}
