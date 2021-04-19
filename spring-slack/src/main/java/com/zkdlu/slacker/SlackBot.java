package com.zkdlu.slacker;

import com.slack.api.Slack;
import com.slack.api.model.block.Blocks;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.BlockCompositions;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.BlockElements;
import com.slack.api.webhook.WebhookPayloads;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.slack.api.model.block.composition.BlockCompositions.plainText;

@Service
public class SlackBot {
    public String vote(String message) throws IOException {
        List<LayoutBlock> layoutBlocks = getLayoutBlocks(message);

        Slack.getInstance().send("",
                WebhookPayloads.payload(p -> p.text("골라 골라~").blocks(layoutBlocks)));

        return message;
    }

    @NotNull
    private List<LayoutBlock> getLayoutBlocks(String message) {
        List<LayoutBlock> layoutBlocks = new ArrayList<>();
        var title = Blocks.section(section -> section.text(BlockCompositions.markdownText(message)));

        layoutBlocks.add(title);
        layoutBlocks.add(Blocks.divider());
        layoutBlocks.add(Blocks.actions(getActionBlocks()));

        return layoutBlocks;
    }

    @NotNull
    private List<BlockElement> getActionBlocks() {
        List<BlockElement> actions = new ArrayList<>();
        var ok = BlockElements.button(b -> b.text(plainText("확인", true))
                .value("ok")
                .style("primary")
                .actionId("action_success"));
        actions.add(ok);

        var fail = BlockElements.button(b -> b.text(plainText("취소", true))
                .value("fail")
                .style("danger")
                .actionId("action_fail"));
        actions.add(fail);
        return actions;
    }
}
