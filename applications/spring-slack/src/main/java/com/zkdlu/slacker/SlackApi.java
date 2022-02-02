package com.zkdlu.slacker;

import com.slack.api.app_backend.interactive_components.payload.BlockActionPayload;
import com.slack.api.util.json.GsonFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public String callback(@RequestParam String payload) throws IOException {
        var blockPayload = GsonFactory.createSnakeCase()
                .fromJson(payload, BlockActionPayload.class);

        return slackBot.callbackVote(blockPayload);
    }
}
