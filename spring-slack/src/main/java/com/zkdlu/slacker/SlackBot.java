package com.zkdlu.slacker;

import com.slack.api.Slack;
import com.slack.api.app_backend.interactive_components.ActionResponseSender;
import com.slack.api.app_backend.interactive_components.payload.BlockActionPayload;
import com.slack.api.app_backend.interactive_components.response.ActionResponse;
import com.slack.api.model.block.Blocks;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.BlockCompositions;
import com.slack.api.model.block.composition.TextObject;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.BlockElements;
import com.slack.api.util.json.GsonFactory;
import com.slack.api.webhook.WebhookPayloads;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;

@Service
public class SlackBot {
    private static Set<Vote> votes = new HashSet<>();

    public String vote(String message) throws IOException {
        List<LayoutBlock> layoutBlocks = Blocks.asBlocks(
                getHeader("골라 주세요!"),
                Blocks.divider(),
                getSection(message),
                Blocks.divider(),
                Blocks.actions(getActionBlocks())
        );

        Slack.getInstance().send(" web hook ",
                WebhookPayloads.payload(p -> p.text("골라 골라~").blocks(layoutBlocks)));

        return message;
    }

    public String callbackVote(BlockActionPayload blockPayload) throws IOException {
        var user = blockPayload.getUser().getUsername();
        var actionId = blockPayload.getActions().get(0).getActionId();

        Vote vote = new Vote(user, actionId);
        votes.add(vote);

        var fields = votes.stream()
                .map(this::getField)
                .collect(Collectors.toList());

        List<LayoutBlock> blocks = Blocks.asBlocks(
                getHeader("집계 결과"),
                Blocks.divider(),
                getFieldSection(fields)
        );

        ActionResponse response = ActionResponse.builder()
                .replaceOriginal(false)
                .blocks(blocks)
                .build();

        ActionResponseSender sender = new ActionResponseSender(Slack.getInstance());
        sender.send(blockPayload.getResponseUrl(), response);

        return user;
    }

    @NotNull
    private LayoutBlock getSection(String message) {
        return Blocks.section(s -> s.text(
                BlockCompositions.markdownText(message)));
    }


    @NotNull
    private LayoutBlock getHeader(String text) {
        return Blocks.header(h -> h.text(
                BlockCompositions.plainText(pt -> pt
                        .emoji(true)
                        .text(text))));
    }

    @NotNull
    private TextObject getField(Vote vote) {
        return BlockCompositions.markdownText("*" +
                vote.getUser() + "*\n" +
                (vote.getActionId().equals("action_success") ? "동의" : "거부"));
    }

    @NotNull
    private LayoutBlock getFieldSection(List<TextObject> fields) {
        return Blocks.section(s -> s.fields(fields));
    }

    @NotNull
    private List<BlockElement> getActionBlocks() {
        List<BlockElement> actions = new ArrayList<>();
        actions.add(getActionButton("확인", "ok", "primary", "action_success"));
        actions.add(getActionButton("취소", "fail", "danger", "action_fail"));
        return actions;
    }

    @NotNull
    private BlockElement getActionButton(String plainText, String value, String style, String actionId) {
        return BlockElements.button(b -> b.text(plainText(plainText, true))
                .value(value)
                .style(style)
                .actionId(actionId));
    }
}
