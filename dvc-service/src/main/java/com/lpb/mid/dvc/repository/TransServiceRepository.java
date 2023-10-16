package com.lpb.mid.dvc.repository;//package com.lpb.esb.service.hcchaugiang.repository;
//
//
//import com.lpb.esb.service.hcchaugiang.utils.Constants;
//import org.hibernate.Session;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import java.sql.CallableStatement;
//import java.sql.Types;
//
//
//@Repository
//public class TransServiceRepository {
//    @Autowired
//    private EntityManager entityManager;
//    private static final Logger logger = LoggerFactory.getLogger(TransServiceRepository.class);
//    public void updateStatusTrans(String xmlTrans, String jobId) {
//        logger.info("updateStatusTrans : call function update status");
//
//        try {
//            entityManager = entityManager.getEntityManagerFactory().createEntityManager();
//            Session session = this.entityManager.unwrap(Session.class);
//            session.doWork(connection -> {
//                CallableStatement function = connection.prepareCall(Constants.pr_update_transaction);
//                function.setString(1, xmlTrans);
//                function.setString(2,jobId );
//                function.registerOutParameter(3, Types.VARCHAR);
//                function.registerOutParameter(4, Types.VARCHAR);
//                function.execute();
//                });
//            logger.info("updateStatusTrans : call function update status by success request {}",xmlTrans);
//        } catch (Exception e) {
//            logger.error("getTransCore : get transaction error -----> {}", e.getMessage());
//        }
//    }
//}
