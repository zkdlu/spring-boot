package com.zkdlu.slacker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class SlackService {
    private final RestTemplate restTemplate;
    private final Map<String, Object> payload;

    @Value("${slack.web-hook}")
    private String webHook;

    public SlackService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.payload = new HashMap<>();
        payload.put("username", "실험용쥐");
    }

    void sendPlane(String text) {
        payload.put("text", text);

        send(payload);
    }

    void sendLink(String text) {
        payload.put("text", text + " <https://zkdlu.tistory.com|클릭> 하세요");

        send(payload);
    }

    void sendIcon(String text) {
        payload.put("text", text);
        payload.put("icon_url", "https://avatars.githubusercontent.com/u/22608617?s=60&v=4");

        send(payload);
    }

    void sendEmoji(String text) {
        payload.put("text", text);
        payload.put("icon_emoji", ":ghost");

        send(payload);
    }

    private void send(Map<String, Object> payload) {
        HttpEntity<Map<String, Object>> data = new HttpEntity<>(payload);

        restTemplate.postForEntity(webHook, payload, String.class);
    }
}
