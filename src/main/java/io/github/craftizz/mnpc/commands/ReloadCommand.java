package io.github.craftizz.mnpc.commands;

import io.github.craftizz.mnpc.MNPC;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@Command("mnpc")
public class ReloadCommand extends CommandBase {

    private final MNPC plugin;

    public ReloadCommand(final @NotNull MNPC plugin) {
        this.plugin = plugin;
    }

    @SubCommand("reload")
    @Permission("mnpc.reload")
    public void onReloadCommand(final CommandSender commandSender) {
        plugin.reload();
        commandSender.sendMessage("Plugin Reloaded");
    }

}
