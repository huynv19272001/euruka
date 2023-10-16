package com.lpb.mid.dvc.mapper;

import com.lpb.mid.dvc.model.request.UpdateTransRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestMapper {
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
}
