package io.github.craftizz.mnpc.npc.actions;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandAction extends AbstractAction {

    private final String rawPlayerCommand;

    public CommandAction(final @NotNull String rawPlayerCommand) {
        this.rawPlayerCommand = rawPlayerCommand;
    }

    @Override
    public void execute(final @NotNull Player player) {
        player.performCommand(rawPlayerCommand);
    }
}
