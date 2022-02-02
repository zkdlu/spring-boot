package com.zkdlu.slacker;

import com.slack.api.Slack;
import com.slack.api.model.block.Blocks;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.webhook.WebhookPayloads;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.slack.api.model.block.Blocks.divider;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.asElements;
import static com.slack.api.model.block.element.BlockElements.button;

@Service
public class SlackService {
    private final RestTemplate restTemplate;
    private final Map<String, Object> pload;

    @Value("${slack.web-hook}")
    private String webHook;

    public SlackService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.pload = new HashMap<>();
        //payload.put("username", "실험용쥐");
    }

    void sendPlane(String text) {
        pload.put("username", "실험용 쥐");
        pload.put("text", "테스트용 메시지 <https://zkdlu.tistory.com|링크>");
        pload.put("icon_url", "https://avatars.githubusercontent.com/u/22608617?s=60&v=4");
        //payload.put("text", text);

        send(pload);
    }

    void sendLink(String text) {
        pload.put("text", text + " <https://zkdlu.tistory.com|클릭> 하세요");

        send(pload);
    }

    void sendIcon(String text) {
        pload.put("text", text);
        pload.put("icon_url", "https://avatars.githubusercontent.com/u/22608617?s=60&v=4");

        send(pload);
    }

    void sendEmoji(String text) {
        pload.put("text", text);
        pload.put("icon_emoji", ":ghost");

        send(pload);
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
        pload.put("attachments", attachments);

        send(pload);
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
        pload.put("attachments", attachments);

        send(pload);
    }

    private void send(Map<String, Object> payload) {
        restTemplate.postForObject(webHook, payload, String.class);
    }

    public void test() {
        List<LayoutBlock> layoutBlocks = new ArrayList<>();
        layoutBlocks.add(section(section -> section.text(markdownText("메시지에요"))));
        layoutBlocks.add(divider());
        layoutBlocks.add(Blocks.actions(actions -> actions
                .elements(asElements(
                        button(b -> b.text(plainText(pt -> pt.emoji(true).text("확인")))
                                .value("확인")
                                .style("primary")
                                .actionId("action_success")
                        ),
                        button(b -> b.text(plainText(pt -> pt.emoji(true).text("거부")))
                                .value("거부")
                                .style("danger")
                                .actionId("action_fail")
                        )
                ))
        ));

        try {
            Slack.getInstance().send("",
                    WebhookPayloads.payload(p -> p.text("슬랙 메시지 ㄴㄴ").blocks(layoutBlocks)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
