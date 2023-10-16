package com.lpb.esb.service.scan.controller;

import com.lpb.esb.service.common.model.response.ResponseModel;
import com.lpb.esb.service.common.utils.code.EsbErrorCode;
import com.lpb.esb.service.scan.model.dto.request.BaseRequestBody;
import com.lpb.esb.service.scan.model.dto.request.RequestJob;
import com.lpb.esb.service.scan.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/info",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ScanController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/scan_transaction")
    public String getInfoCustomerBill(){
        return transactionService.transDebt();

    }
}
