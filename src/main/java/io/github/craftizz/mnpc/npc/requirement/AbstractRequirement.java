package io.github.craftizz.mnpc.npc.requirement;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractRequirement {

    protected final String placeholder;
    protected final String denyMessage;

    protected AbstractRequirement(final @NotNull String placeholder,
                                  final @NotNull String denyMessage) {
        this.placeholder = placeholder;
        this.denyMessage = denyMessage;
    }

    public abstract boolean meets(final @NotNull Player player);
    public abstract void denyMessage(final @NotNull Player player);
}
