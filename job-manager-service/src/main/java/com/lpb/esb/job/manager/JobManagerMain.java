package com.lpb.esb.job.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
@EnableAsync
public class JobManagerMain {

    public static void main(String[] args) {
        SpringApplication.run(JobManagerMain.class, args);

    }
}

