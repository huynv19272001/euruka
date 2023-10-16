package com.lpb.esb.service.scan.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ConvertUtils {
    private static final Logger logger = LogManager.getLogger(ConvertUtils.class);

    public static String convertObjectToJson(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public static String convertDate(Date date) {
        String dateFormat= null ;
        try {
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            dateFormat = simpleDateFormat.format(date);
        } catch (Exception e) {
            logger.error("convert date to string error ---> {}", e.toString());
        }
        return dateFormat;
    }

    public static String convertDate(String  jobInterval) {
        int sec = Integer.parseInt(jobInterval);
        Date d = new Date(sec * 1000L);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String time = df.format(d);
        Date date = new Date();
        date.setHours(date.getHours()-Integer.parseInt(time.replaceAll(":", "").substring(0, 6).substring(0, 2)));
        date.setMinutes(date.getMinutes()-Integer.parseInt(time.replaceAll(":", "").substring(0, 6).substring(2, 4)));
        date.setSeconds(date.getSeconds()-Integer.parseInt(time.replaceAll(":", "").substring(0, 6).substring(4, 6)));
        String dateFormat = null;
        try {
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            dateFormat = simpleDateFormat.format(date);
        } catch (Exception e) {
            logger.error("convert date to string error ---> {}", e.toString());
        }
        return dateFormat;
    }
    public static String formatDate(String date){
        String formatDate = "%s/%s/%s";
        return String.format(formatDate,
            date.replaceAll("-", "").substring(0, 8).substring(6, 8),
            date.replaceAll("-", "").substring(0, 8).substring(4, 6),
            date.replaceAll("-", "").substring(0, 8).substring(0, 4));
    }
}
