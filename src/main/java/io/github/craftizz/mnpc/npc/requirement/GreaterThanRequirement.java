package io.github.craftizz.mnpc.npc.requirement;

import io.github.craftizz.mnpc.utils.MessageUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GreaterThanRequirement extends AbstractRequirement {

    private final double requirement;

    public GreaterThanRequirement(final @NotNull String placeholder,
                                  final double requirement,
                                  final @NotNull String denyMessage) {
        super(placeholder, denyMessage);
        this.requirement = requirement;
    }

    @Override
    public boolean meets(final @NotNull Player player) {
        return Double.parseDouble(PlaceholderAPI.setPlaceholders(player, placeholder)) >= requirement;
    }

    @Override
    public void denyMessage(@NotNull Player player) {
        MessageUtil.sendMessage(player, denyMessage, "placeholder", PlaceholderAPI.setPlaceholders(player, placeholder));
    }
}
