package com.zkdlu.slacker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slack.api.Slack;
import com.slack.api.app_backend.interactive_components.ActionResponseSender;
import com.slack.api.app_backend.interactive_components.payload.BlockActionPayload;
import com.slack.api.app_backend.interactive_components.response.ActionResponse;
import com.slack.api.util.json.GsonFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;

@RestController
public class SlackApi {
    private final SlackService slackService;
    private final ObjectMapper mapper;

    public SlackApi(SlackService slackService, ObjectMapper mapper) {
        this.slackService = slackService;
        this.mapper = mapper;
    }

    @GetMapping("/test")
    public String test() {
        slackService.test();

        return "test";
    }

    @PostMapping(value = "/slack/callback", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String callback(@RequestParam String payload) throws JsonProcessingException {
        System.out.println(payload);

        var blockPayload = GsonFactory.createSnakeCase()
                .fromJson(payload, BlockActionPayload.class);

        blockPayload.getMessage().getBlocks().remove(0);
        blockPayload.getActions().forEach(action -> {
            if (action.getActionId().equals("action_success")) {
                blockPayload.getMessage().getBlocks().add(0,
                        section(section -> section.text(markdownText("확인"))));
            } else {
                blockPayload.getMessage().getBlocks().add(0,
                        section(section -> section.text(markdownText("취소"))));
            }
        });

        ActionResponse response = ActionResponse.builder()
                .replaceOriginal(true)
                .blocks(blockPayload.getMessage().getBlocks())
                .build();

        Slack slack = Slack.getInstance();
        ActionResponseSender sender = new ActionResponseSender(slack);
        try {
            sender.send(blockPayload.getResponseUrl(), response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "block kit";
    }

    @GetMapping("/plane/{text}")
    public String knock(@PathVariable String text) {
        slackService.sendPlane(text);

        return "knock knock";
    }

    @GetMapping("/link/{text}")
    public String link(@PathVariable String text) {
        slackService.sendLink(text);

        return "knock knock link";
    }

    @GetMapping("/emoji/{text}")
    public String custom(@PathVariable String text) {
        slackService.sendEmoji(text);

        return "knock knock emoji";
    }

    @GetMapping("/icon/{text}")
    public String icon(@PathVariable String text) {
        slackService.sendIcon(text);

        return "knock knock icon";
    }

    @GetMapping("/attach/{text}")
    public String attach(@PathVariable String text) {
        slackService.sendAttatchments(text);

        return "knock knock attach";
    }

    @GetMapping("/button/{text}")
    public String button(@PathVariable String text) {
        slackService.sendButtons(text);

        return "knock knock attach";
    }
}
