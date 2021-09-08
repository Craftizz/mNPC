package io.github.craftizz.mnpc.npc.actions;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ConsoleAction extends AbstractAction {

    private final String rawConsoleCommand;

    public ConsoleAction(final @NotNull String rawConsoleCommand) {
        this.rawConsoleCommand = rawConsoleCommand;
    }


    @Override
    public void execute(Player player) {
        final ConsoleCommandSender console = Bukkit.getConsoleSender();
        final String consoleCommand = rawConsoleCommand.replace("<player>", player.getName());
        Bukkit.dispatchCommand(console, consoleCommand);
    }
}
