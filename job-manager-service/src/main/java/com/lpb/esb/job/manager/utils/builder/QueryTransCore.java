package com.lpb.esb.job.manager.utils.builder;

public class QueryTransCore {
    public static String loadSQLTransactionCore(String accNo, String nextTime) {
        String bu = " SELECT A.TRN_REF_NO,A.LCY_AMOUNT,A.AC_NO,A.VALUE_DT,A.TRN_DT,A.AMOUNT_TAG, " +
            " (SELECT l.AC_NO from actb_daily_log l where l.batch_no = A.BATCH_NO and l.trn_dt = A.TRN_DT " +
            " and l.value_dt = A.value_Dt and l.Drcr_Ind = 'D' and rownum = 1) as SOURCE_ACC, " +
            " (SELECT ac_desc from sttm_cust_account where cust_ac_no = (SELECT l.AC_NO " +
            " from actb_daily_log l where l.batch_no = A.BATCH_NO and l.trn_dt = A.TRN_DT " +
            " and l.value_dt = A.value_Dt and l.Drcr_Ind = 'D' and rownum = 1)) as AC_DESC, " +
            " B.ADDL_TEXT as TRN_DESC " +
            " FROM actb_daily_log A  " +
            " LEFT JOIN detb_jrnl_txn_detail B ON A.TRN_REF_NO = B.REFERENCE_NO " +
            " WHERE AC_NO = '%s' AND DRCR_IND = 'C' AND a.auth_stat = 'A' " +
            " AND nvl(a.delete_stat, 'X') <> 'D'   AND NOT EXISTS (SELECT 1 FROM actb_daily_log b " +
            " WHERE b.trn_Ref_no = a.trn_ref_no AND b.event = 'REVR' AND b.auth_stat = 'A' " +
            " AND nvl(b.delete_stat, 'X') <> 'D')  " +
            " AND CAST(CAST(A.TXN_DT_TIME AS timestamp) AS DATE) " +
            " between TO_DATE('%s', 'RRRR/MM/DD HH24:MI:SS') " +
            " AND SYSDATE " +
            " ORDER BY A.AC_ENTRY_SR_NO ";
        return String.format(bu, accNo, nextTime);
    }

}
