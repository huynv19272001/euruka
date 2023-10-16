package com.lpb.mid.dvc.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lpb.esb.service.common.model.request.infocustomerbill.BillDTO;
import com.lpb.esb.service.common.model.request.settle.DataSettleDTO;
import com.lpb.esb.service.common.model.response.LpbResCode;
import com.lpb.esb.service.common.model.response.ResponseModel;
import com.lpb.esb.service.common.utils.code.ErrorMessage;
import com.lpb.mid.dvc.config.RestTemplateConfig;
import com.lpb.mid.dvc.mapper.RequestMapper;
import com.lpb.mid.dvc.model.request.UpdateTransRequest;
import com.lpb.mid.dvc.service.HCCHauGiangService;
import com.lpb.mid.dvc.utils.Constants;
import com.lpb.mid.dvc.utils.ConvertUtils;
import net.logstash.logback.encoder.org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HCCHauGiangServiceImpl implements HCCHauGiangService {
    private static final Logger logger = LogManager.getLogger(HCCHauGiangServiceImpl.class);
    final
    RestTemplateConfig restTemplate;

    private final RequestMapper requestMapper;
    public HCCHauGiangServiceImpl(RestTemplateConfig restTemplate, RequestMapper requestMapper) {
        this.restTemplate = restTemplate;
        this.requestMapper = requestMapper;
    }

    @Override
    public String getBillDebt() {
        String errorMess = ErrorMessage.SUCCESS.description;
        String test = " test dail";
        return errorMess+test;
    }


}
