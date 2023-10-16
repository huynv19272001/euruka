package com.lpb.esb.service.scan.utils;

public abstract class Constants {

    public static final String REQUEST_TXN = "REQUEST_TXN";
    public static final String N = "N";
    public static final String J024 = "J024";
    ////// function sql
    public static final String PKG_ESB_JOB = "PKG_ESB_JOB";
    public static final String fn_fill_transaction = "fn_fill_transaction";

    public static final String Fn_Init_Transaction
        = "{? = call PKG_ESB_JOB.fn_fill_transaction(?,XMLType(?),?,?)}";

    public static final String pr_update_transaction
        = "{ call PKG_ESB_JOB.pr_update_transaction(XMLType(?),?,?,?)}";
}
