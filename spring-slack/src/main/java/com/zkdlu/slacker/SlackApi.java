package com.zkdlu.slacker;

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
        return slackBot.callback(payload);
    }
}
