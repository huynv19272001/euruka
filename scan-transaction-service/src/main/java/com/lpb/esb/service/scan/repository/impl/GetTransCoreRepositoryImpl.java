package com.lpb.esb.service.scan.repository.impl;


import com.lpb.esb.service.scan.model.dto.EsbJobTransaction;
import com.lpb.esb.service.scan.repository.GetTransCoreRepository;
import com.lpb.esb.service.scan.utils.Constants;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


@Repository
public class GetTransCoreRepositoryImpl implements GetTransCoreRepository {
    @Autowired
    private EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(GetTransCoreRepositoryImpl.class);

    @Override
    public List<EsbJobTransaction> getUpdateTrans(String jobId, String xmlTrans) {
        logger.info("getUpdateTrans : call function get transaction to debt by jobId : {}", jobId);
        List<EsbJobTransaction> esbJobTransactions = new ArrayList<>();
        try {
            entityManager = entityManager.getEntityManagerFactory().createEntityManager();
            Session session = this.entityManager.unwrap(Session.class);

            session.doWork(connection -> {
                OracleCallableStatement function = connection.prepareCall(
                        Constants.Fn_Init_Transaction)
                    .unwrap(OracleCallableStatement.class);
                function.registerOutParameter(1, OracleTypes.CURSOR);
                function.setString(2, jobId);
                function.setString(3, xmlTrans);
                function.registerOutParameter(4, Types.VARCHAR);
                function.registerOutParameter(5, Types.VARCHAR);
                function.execute();
                ResultSet rs = function.getCursor(1);
                while (rs.next()) {
                    EsbJobTransaction esbJobTransaction = new EsbJobTransaction();
                    if (rs.getString("job_id") != null) {
                        esbJobTransaction.setJobId(rs.getString("job_id"));
                    }
                    if (rs.getString("trn_ref_no") != null) {
                        esbJobTransaction.setTrnRefNo(rs.getString("trn_ref_no"));
                    }
                    if (rs.getString("trn_desc") != null) {
                        esbJobTransaction.setTrnDesc(rs.getString("trn_desc"));
                    }
                    if (rs.getString("value_dt") != null) {
                        esbJobTransaction.setValueDt(rs.getString("value_dt"));
                    }
                    if (rs.getString("scan_dt") != null) {
                        esbJobTransaction.setScanDt(rs.getString("scan_dt"));
                    }
                    if (rs.getString("trn_dt") != null) {
                        esbJobTransaction.setTrnDt(rs.getString("trn_dt"));
                    }
                    if (rs.getString("record_stats") != null) {
                        esbJobTransaction.setRecordStats(rs.getString("record_stats"));
                    }
                    if (rs.getString("lcy_amount") != null) {
                        esbJobTransaction.setLcyAmount(rs.getString("lcy_amount"));
                    }
                    if (rs.getString("acc_no") != null) {
                        esbJobTransaction.setAccNo(rs.getString("acc_no"));
                    }
                    esbJobTransactions.add(esbJobTransaction);
                }
                rs.close();
                function.close();
                connection.close();
                logger.info("getUpdateTrans : call function to database success by jobId : {}", jobId);
            });
        } catch (Exception e) {
            logger.error("getUpdateTrans : get transaction error -----> {}", e.getMessage());
        }
        return esbJobTransactions;
    }
}
