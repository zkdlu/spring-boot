package com.zkdlu.slacker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    void sendAttatchments(String text) {
        Map<String, Object> attachment = new HashMap<>();
        attachment.put("fallback", "Required plain-text summary of the attachment.");
        attachment.put("color", "#36a64f");
        attachment.put("pretext", "안녕하세요");
        attachment.put("author_name", "이건");
        attachment.put("author_link", "https://zkdlu.tistory.com");
        attachment.put("author_icon", "https://avatars.githubusercontent.com/u/22608617?s=60&v=4");
        attachment.put("title", "이거 제목이에용");
        attachment.put("title_link", "https://zkdlu.tistory.com");
        attachment.put("text", text);

        Map<String, Object> fields = new HashMap<>();
        fields.put("title", "Priority");
        fields.put("value", "High");

        attachment.put("fields", fields);
        attachment.put("image_url", "https://avatars.githubusercontent.com/u/22608617?s=60&v=4");
        attachment.put("thumb_url", "https://avatars.githubusercontent.com/u/22608617?s=60&v=4");
        attachment.put("footer", "Slack Test");
        attachment.put("footer_icon", "https://avatars.githubusercontent.com/u/22608617?s=60&v=4");
        attachment.put("ts", 1);

        List<Map<String, Object>> attachments = new ArrayList<>();
        attachments.add(attachment);
        payload.put("attachments", attachments);

        send(payload);
    }

    void sendButtons(String text) {
        Map<String, Object> attachment = new HashMap<>();
        attachment.put("text", text);
        attachment.put("fallback", "Required plain-text summary of the attachment.");
        attachment.put("callback_id", "zkdlu");
        attachment.put("color", "#36a64f");
        attachment.put("attachment_type", "default");

        List<Map<String, Object>> actions = new ArrayList<>();
        Map<String, Object> actionChess = new HashMap<>();
        actionChess.put("name", "game");
        actionChess.put("text", "Chess");
        actionChess.put("type", "button");
        actionChess.put("value", "chess");
        actions.add(actionChess);

        Map<String, Object> actionMaze = new HashMap<>();
        actionMaze.put("name", "game");
        actionMaze.put("text", "텍스트에요");
        actionMaze.put("type", "button");
        actionMaze.put("value", "값이에요");
        actions.add(actionMaze);

        Map<String, Object> actionDanger = new HashMap<>();
        actionDanger.put("name", "game");
        actionDanger.put("text", "위험한 텍스트에요");
        actionDanger.put("style", "danger");
        actionDanger.put("type", "button");
        actionDanger.put("value", "위험한 값이에요");

        Map<String, Object> confirm = new HashMap<>();
        confirm.put("title", "정말?");
        confirm.put("text", "정말? 정말 정말?");
        confirm.put("ok_text", "좋아");
        confirm.put("dismiss_text", "싫어");

        actionDanger.put("confirm", confirm);
        actions.add(actionDanger);

        attachment.put("actions", actions);

        List<Map<String, Object>> attachments = new ArrayList<>();
        attachments.add(attachment);
        payload.put("attachments", attachments);

        send(payload);
    }

    private void send(Map<String, Object> payload) {
        restTemplate.postForEntity(webHook, payload, String.class);
    }
}
