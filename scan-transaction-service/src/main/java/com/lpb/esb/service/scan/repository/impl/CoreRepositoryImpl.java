package com.lpb.esb.service.scan.repository.impl;


import com.lpb.esb.service.scan.model.dto.TransCoreDto;
import com.lpb.esb.service.scan.repository.CoreRepository;
import com.lpb.esb.service.scan.utils.ConvertUtils;
import com.lpb.esb.service.scan.utils.builder.QueryTransCore;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CoreRepositoryImpl implements CoreRepository {
    @Value("${configDbCore.className}")
    private String className;
    @Value("${configDbCore.urlDB}")
    private String urlDB;
    @Value("${configDbCore.userDB}")
    private String userDB;
    @Value("${configDbCore.passwordDB}")
    private String passwordDB;
    @Value("${configDbCore.checkDb}")
    private boolean checkDb;

    private static final Logger logger = LoggerFactory.getLogger(CoreRepositoryImpl.class);

    @Override
    public List<TransCoreDto> callGetTrans(String accNo, String nextDate) {
        List<TransCoreDto> transDtos = new ArrayList<>();
        try {
            if (!StringUtils.isBlank(accNo) && !StringUtils.isBlank(nextDate)) {
                String queryTrans;
                if (checkDb) {
                    queryTrans = QueryTransCore.loadSQLTransactionCore(accNo, nextDate);
                    logger.info("callGetTrans database Core : query call trans core : {}", queryTrans);
                } else {
                    queryTrans = QueryTransCore.loadSQLTransactionDWH(accNo, nextDate);
                    logger.info("callGetTrans database Dwh : query call trans dwh : {}", queryTrans);
                }
                Class.forName(className);
                Connection con = DriverManager.getConnection(urlDB, userDB, passwordDB);
                PreparedStatement pstmt = con.prepareStatement(queryTrans);
                logger.info("callGetTrans database Core : call system core by accNo : {}, nextDate : {}", accNo, nextDate);
                ResultSet rs = pstmt.executeQuery();
                mapTransCore(transDtos, rs);
                rs.close();
                pstmt.close();
                con.close();
            }
        } catch (Exception e) {
            logger.error("callGetTransCore : get core transaction with error ---> {} ", e.getMessage());
        }
        return transDtos;
    }

    public static void mapTransCore(List<TransCoreDto> transCoreDtos, ResultSet rs) throws SQLException {
        while (rs.next()) {
            TransCoreDto transCoreDto = new TransCoreDto();
            transCoreDto.setTrnRefNo(rs.getString(1));
            transCoreDto.setLcyAmount(rs.getString(2));
            transCoreDto.setAcNo(rs.getString(3));
            transCoreDto.setValueDt(ConvertUtils.formatDate(rs.getString(4)));
            transCoreDto.setTrnDt(ConvertUtils.formatDate(rs.getString(5)));
            transCoreDto.setAmountTag(rs.getString(6));
            transCoreDto.setSourceAcc(rs.getString(7));
            transCoreDto.setAcDesc(rs.getString(8));
            transCoreDto.setTrnDesc(rs.getString(9));
            transCoreDtos.add(transCoreDto);
        }
    }
}
