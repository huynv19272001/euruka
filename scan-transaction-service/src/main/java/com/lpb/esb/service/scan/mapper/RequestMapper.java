package com.lpb.esb.service.scan.mapper;

import com.lpb.esb.service.common.model.request.infocustomerbill.BillDTO;
import com.lpb.esb.service.common.model.request.infocustomerbill.HeaderDTO;
import com.lpb.esb.service.common.model.request.infocustomerbill.ServiceDTO;
import com.lpb.esb.service.common.model.request.settle.BodyDTO;
import com.lpb.esb.service.common.model.request.settle.DataSettleDTO;
import com.lpb.esb.service.common.model.request.settle.PartnerDTO;
import com.lpb.esb.service.common.model.request.settle.SettleBillDTO;
import com.lpb.esb.service.scan.model.EsbJobScheduler;
import com.lpb.esb.service.scan.model.dto.EsbJobTransaction;
import com.lpb.esb.service.scan.model.dto.TransCoreDto;
import com.lpb.esb.service.scan.model.dto.request.UpdateTransRequest;
import com.lpb.esb.service.scan.utils.Constants;
import com.lpb.esb.service.scan.utils.Generate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestMapper {
    public String listTransXmltype(List<TransCoreDto> listTransCore) {
        StringBuilder bu = new StringBuilder();
        bu.append("<TRANSACTIONS>");
        for (TransCoreDto transVO : listTransCore) {
            bu.append("<TRAN_IO>");
            bu.append("<TRN_REF_NO>").append(transVO.getTrnRefNo()).append("</TRN_REF_NO>");
            bu.append("<ACC_NO>").append(transVO.getAcNo()).append("</ACC_NO>");
            bu.append("<TRN_DESC>").append(transVO.getTrnDesc()).append("</TRN_DESC>");
            bu.append("<VALUE_DT>").append(transVO.getValueDt()).append("</VALUE_DT>");
            bu.append("<TRN_DT>").append(transVO.getTrnDt()).append("</TRN_DT>");
            bu.append("<LCY_AMOUNT>").append(transVO.getLcyAmount()).append("</LCY_AMOUNT>");
            bu.append("<SOURCE_ACC>").append(transVO.getSourceAcc()).append("</SOURCE_ACC>");
            bu.append("<OTHER>").append(transVO.getAcDesc()).append("</OTHER>");
            bu.append("</TRAN_IO>");
        }
        bu.append("</TRANSACTIONS>");

        return bu.toString();
    }

    public String requestUpdateTrans(List<UpdateTransRequest> updateTransRequests) {
        StringBuilder bu = new StringBuilder();
        bu.append("<trans>");
        for (UpdateTransRequest updateTransRequest : updateTransRequests){
            bu.append("<tran>");
            bu.append("<trnRefno>").append(updateTransRequest.getTrnAccount()).append("</trnRefno>");
            bu.append("<trnDT>").append(updateTransRequest.getDate()).append("</trnDT>");
            bu.append("<status>").append(updateTransRequest.getStatus()).append("</status>");
            bu.append("</tran>");
        }
        bu.append("</trans>");
        return bu.toString();
    }


    public DataSettleDTO mapDataSettleDTO(List<EsbJobTransaction> esbJobTransactions, EsbJobScheduler esbJobScheduler,String msgId) {
        DataSettleDTO dataSettleDTO = new DataSettleDTO();

        //HeaderDTO
        HeaderDTO headerDTO = new HeaderDTO();
        headerDTO.setMsgid(msgId);
        headerDTO.setService(esbJobScheduler.getUdf4());
        headerDTO.setOperation(Constants.REQUEST_TXN);
        ////
        BodyDTO bodyDTO = new BodyDTO();

        SettleBillDTO settleBill = new SettleBillDTO();

        settleBill.setConfirmTrn(Constants.N);
        settleBill.setCustomerNo(esbJobScheduler.getUdf4());
        settleBill.setTrnDesc("Thanh toán hóa đơn hcc hau giang");

        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId(esbJobScheduler.getUdf3());
        serviceDTO.setProductCode(esbJobScheduler.getUdf4());
        serviceDTO.setMerchantId(esbJobScheduler.getUdf4());
        serviceDTO.setRequestAccount(esbJobScheduler.getUdf1());
        settleBill.setService(serviceDTO);
        PartnerDTO partner = new PartnerDTO();
        partner.setTxnDatetime("");
        partner.setChanel(esbJobScheduler.getJobId());
        settleBill.setPartner(partner);
        List<BillDTO> billDTOS = new ArrayList<>();
        esbJobTransactions.forEach(esbJobTransaction -> {
            BillDTO billDTO = new BillDTO();
            billDTO.setBillAmount(esbJobTransaction.getLcyAmount());
            billDTO.setSettledAmount(esbJobTransaction.getLcyAmount());
            billDTO.setBillDesc(esbJobTransaction.getTrnDesc());
            billDTO.setOtherInfo(esbJobTransaction.getTrnRefNo());
            billDTO.setAmtUnit(esbJobTransaction.getValueDt());
            billDTOS.add(billDTO);
        });
        settleBill.setListBillInfo(billDTOS);

        bodyDTO.setSettleBill(settleBill);
        dataSettleDTO.setHeader(headerDTO);
        dataSettleDTO.setBody(bodyDTO);
        return dataSettleDTO;
    }
}
