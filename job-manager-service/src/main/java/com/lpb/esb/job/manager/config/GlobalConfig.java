package com.lpb.esb.job.manager.config;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tudv1 on 2023-08-23
 */
public class GlobalConfig {
    public static ConcurrentHashMap<String, String> MAP_JOB_ACTIVE;

    static {
        MAP_JOB_ACTIVE = new ConcurrentHashMap<>();
    }
}
