package io.github.craftizz.mnpc.listeners;

import io.github.craftizz.mnpc.MNPC;
import io.github.craftizz.mnpc.managers.NPCManager;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class ClickListener implements Listener {

    private final NPCManager npcManager;

    public ClickListener(final @NotNull MNPC plugin) {
        this.npcManager = plugin.getNpcManager();
    }

    @EventHandler
    public void onNPCLeftClickEvent(final NPCLeftClickEvent event) {
        npcManager.handleSocialNPC(event.getClicker(), event.getNPC().getId());
    }

    @EventHandler
    public void onNPCRightClickEvent(final NPCRightClickEvent event) {
        npcManager.handleSocialNPC(event.getClicker(), event.getNPC().getId());
    }
}
