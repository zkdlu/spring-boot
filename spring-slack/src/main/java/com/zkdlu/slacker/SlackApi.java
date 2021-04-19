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

import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;

@RestController
public class SlackApi {
    private final SlackService slackService;
    private final SlackBot slackBot;

    public SlackApi(SlackService slackService, SlackBot slackBot) {
        this.slackService = slackService;
        this.slackBot = slackBot;
    }

    @GetMapping("/vote/{message}")
    public String test(@PathVariable String message) throws IOException {
        return slackBot.vote(message);
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
}
