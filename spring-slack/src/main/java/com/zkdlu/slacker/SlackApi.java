package com.zkdlu.slacker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlackApi {
    private final SlackService slackService;

    public SlackApi(SlackService slackService) {
        this.slackService = slackService;
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

        return "knock knock link";
    }

    @GetMapping("/icon/{text}")
    public String icon(@PathVariable String text) {
        slackService.sendIcon(text);

        return "knock knock link";
    }
}
