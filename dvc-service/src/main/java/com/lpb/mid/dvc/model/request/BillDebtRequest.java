package com.lpb.mid.dvc.model.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDebtRequest {
    private String Key;
    private String Amount;
}
