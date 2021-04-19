package com.zkdlu.slacker;

import com.slack.api.Slack;
import com.slack.api.app_backend.interactive_components.ActionResponseSender;
import com.slack.api.app_backend.interactive_components.payload.BlockActionPayload;
import com.slack.api.app_backend.interactive_components.response.ActionResponse;
import com.slack.api.model.block.Blocks;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.BlockCompositions;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.BlockElements;
import com.slack.api.util.json.GsonFactory;
import com.slack.api.webhook.WebhookPayloads;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;

@Service
public class SlackBot {
    public String vote(String message) throws IOException {
        List<LayoutBlock> layoutBlocks = getLayoutBlocks(message);
        layoutBlocks.add(Blocks.actions(getActionBlocks()));

        Slack.getInstance().send("",
                WebhookPayloads.payload(p -> p.text("골라 골라~").blocks(layoutBlocks)));

        return message;
    }

    public String callback(String payload) throws IOException {
        var blockPayload = GsonFactory.createSnakeCase()
                .fromJson(payload, BlockActionPayload.class);

        var user = blockPayload.getUser().getUsername();
        var channel = blockPayload.getChannel().getName();
        var actionId = blockPayload.getActions().get(0).getActionId();

        var blockLayouts = getLayoutBlocks(user + "님이 " + (actionId.equals("action_success") ? "[승인]" : "[거부]" + " 하였습니다."));

        ActionResponse response = ActionResponse.builder()
                .replaceOriginal(false)
                .blocks(blockLayouts)
                .build();

        ActionResponseSender sender = new ActionResponseSender(Slack.getInstance());
        sender.send(blockPayload.getResponseUrl(), response);

        return payload;
    }

    @NotNull
    private List<LayoutBlock> getLayoutBlocks(String message) {
        List<LayoutBlock> layoutBlocks = new ArrayList<>();
        var title = Blocks.section(section -> section.text(BlockCompositions.markdownText(message)));

        layoutBlocks.add(title);
        layoutBlocks.add(Blocks.divider());

        return layoutBlocks;
    }

    @NotNull
    private List<BlockElement> getActionBlocks() {
        List<BlockElement> actions = new ArrayList<>();
        actions.add(getButton("확인", "ok", "primary", "action_success"));
        actions.add(getButton("취소", "fail", "danger", "action_fail"));
        return actions;
    }

    @NotNull
    private BlockElement getButton(String plainText, String value, String style, String actionId) {
        return BlockElements.button(b -> b.text(plainText(plainText, true))
                .value(value)
                .style(style)
                .actionId(actionId));
    }
}
