package io.github.craftizz.mnpc.commands;

import io.github.craftizz.mnpc.MNPC;
import io.github.craftizz.mnpc.managers.NPCManager;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Completion;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Command("mnpc")
public class PreviewCommand extends CommandBase {

    private final NPCManager npcManager;

    public PreviewCommand(final @NotNull MNPC plugin) {
        this.npcManager = plugin.getNpcManager();
    }

    @SubCommand("preview")
    @Permission("mnpc.preview")
    public void onPreviewCommand(final Player player, @Completion("#npcs") final Integer npcId) {
        npcManager.handleSocialNPC(player, npcId);
    }
}
