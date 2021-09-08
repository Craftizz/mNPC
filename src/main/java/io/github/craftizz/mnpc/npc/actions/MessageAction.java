package io.github.craftizz.mnpc.npc.actions;

import io.github.craftizz.mnpc.utils.MessageUtil;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MessageAction extends AbstractAction {

    private final String rawMessage;

    public MessageAction(final @NotNull String rawMessage) {
        this.rawMessage = rawMessage;
    }

    @Override
    public void execute(final @NotNull Player player) {
        MessageUtil.sendMessage(player, rawMessage, "player", player.getName());
    }
}
