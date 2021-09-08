package io.github.craftizz.mnpc.npc.actions;

import net.kyori.adventure.sound.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SoundAction extends AbstractAction {

    private final Sound sound;

    public SoundAction(final @NotNull Sound sound) {
        this.sound = sound;
    }

    @Override
    public void execute(Player player) {
        player.playSound(sound);
    }
}
