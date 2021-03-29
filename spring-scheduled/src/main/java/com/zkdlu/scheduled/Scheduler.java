package com.zkdlu.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Scheduler {

    /*
    second minute hour  days   month week
    0-59   0-59   0-23  1-31   1-12  0-7
     */
    @Scheduled(cron = "* * * * * *")
    public void cronJob() {
        System.out.println("cron : " + LocalDateTime.now().toString());
    }

    @Scheduled(fixedDelay = 1000)
    public void delayJob() {
        System.out.println("delay : " + LocalDateTime.now().toString());
    }

    @Scheduled(fixedRate = 1000)
    public void rateJob() {
        System.out.println("rate : " + LocalDateTime.now().toString());
    }
}
