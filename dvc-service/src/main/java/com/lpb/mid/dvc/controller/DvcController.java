package com.lpb.mid.dvc.controller;

import com.lpb.mid.dvc.service.HCCHauGiangService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dvc-service", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DvcController {

    private final HCCHauGiangService hccHauGiangService;

    public DvcController(HCCHauGiangService hccHauGiangService) {
        this.hccHauGiangService = hccHauGiangService;
    }

    @PostMapping("/bill-debt")
    public String getBillDebt() {
        return hccHauGiangService.getBillDebt();
    }
}
