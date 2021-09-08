package io.github.craftizz.mnpc.npc;

import io.github.craftizz.mnpc.npc.actions.AbstractAction;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private final List<AbstractAction> actions;

    public Page() {
        this.actions = new ArrayList<>();
    }

    public void play(final @NotNull Player player) {
        for (final AbstractAction action : actions) {
            action.execute(player);
        }
    }

    public List<AbstractAction> getActions() {
        return actions;
    }

    public void addAction(final @NotNull AbstractAction action) {
        actions.add(action);
    }
}
