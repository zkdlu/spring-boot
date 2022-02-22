package com.zkdlu.event.infra.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsClient {
    public void send(String msg) {
        log.info("메시지를 보냅니다.: {}", msg);
    }
}
