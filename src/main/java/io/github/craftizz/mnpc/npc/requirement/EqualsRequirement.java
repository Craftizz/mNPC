package io.github.craftizz.mnpc.npc.requirement;

import io.github.craftizz.mnpc.utils.MessageUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EqualsRequirement extends AbstractRequirement {

    private final String requirement;

    public EqualsRequirement(final @NotNull String placeholder,
                             final @NotNull String requirement,
                             final @NotNull String denyMessage) {
        super(placeholder, denyMessage);
        this.requirement = requirement;
    }

    @Override
    public boolean meets(@NotNull Player player) {
        return PlaceholderAPI.setPlaceholders(player, placeholder).equals(requirement);
    }

    @Override
    public void denyMessage(@NotNull Player player) {
        MessageUtil.sendMessage(player, denyMessage, "placeholder", PlaceholderAPI.setPlaceholders(player, placeholder));
    }
}
