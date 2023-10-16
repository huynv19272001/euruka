package com.lpb.esb.job.manager.utils;

public abstract class Constants {
    public static final String PKG_ESB_JOB = "PKG_ESB_JOB";
    public static final String fn_fill_transaction = "fn_fill_transaction";

    public static final String SCHEDULER_JOB_ACTIVE = "scheduler-job-active";

    public static final String Fn_Init_Transaction
        = "{? = call PKG_ESB_JOB.fn_fill_transaction(?,XMLType(?),?,?)}";
}
