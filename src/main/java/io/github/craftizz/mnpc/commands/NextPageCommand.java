package io.github.craftizz.mnpc.commands;

import io.github.craftizz.mnpc.MNPC;
import io.github.craftizz.mnpc.managers.NPCManager;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Completion;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Command("mnpc")
public class NextPageCommand extends CommandBase {

    private final NPCManager npcManager;

    public NextPageCommand(final @NotNull MNPC plugin) {
        this.npcManager = plugin.getNpcManager();
    }

    @SubCommand("next")
    public void onNextCommand(final Player player, @Completion("#npcs") final Integer npcId) {
        npcManager.handleSocialNPC(player, npcId);
    }

}
