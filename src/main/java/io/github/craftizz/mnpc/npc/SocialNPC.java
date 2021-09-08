package io.github.craftizz.mnpc.npc;

import io.github.craftizz.mnpc.npc.requirement.AbstractRequirement;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SocialNPC {

    private final int npcId;

    private final List<Page> pages;
    private final List<AbstractRequirement> requirements;

    private Page firstClickPage;


    public SocialNPC(final @NotNull Integer npcId) {
        this.npcId = npcId;
        this.pages = new ArrayList<>();
        this.requirements = new ArrayList<>();
    }

    public void addPage(final @NotNull Page page) {
        pages.add(page);
    }

    public void addRequirement(final @NotNull AbstractRequirement requirement) {
        requirements.add(requirement);
    }

    public boolean meetsRequirement(final @NotNull Player player) {
        for (final AbstractRequirement requirement : requirements) {
            if (!requirement.meets(player)) {
                requirement.denyMessage(player);
                return false;
            }
        }
        return true;
    }

    public @NotNull Page getPage(final int page) {
        if (page >= pages.size()) {
            return pages.get(0);
        }
        return pages.get(page);
    }

    public @Nullable Page getFirstClickPage() {
        return firstClickPage;
    }

    public int getNpcId() {
        return npcId;
    }

    public void setFirstClickPage(final @NotNull Page firstClickPage) {
        this.firstClickPage = firstClickPage;
    }
}
