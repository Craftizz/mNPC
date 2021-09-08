package io.github.craftizz.mnpc.configuration;

import de.leonhard.storage.LightningBuilder;
import de.leonhard.storage.Yaml;
import de.leonhard.storage.sections.FlatFileSection;
import io.github.craftizz.mnpc.MNPC;
import io.github.craftizz.mnpc.managers.NPCManager;
import io.github.craftizz.mnpc.npc.Page;
import io.github.craftizz.mnpc.npc.SocialNPC;
import io.github.craftizz.mnpc.npc.actions.CommandAction;
import io.github.craftizz.mnpc.npc.actions.ConsoleAction;
import io.github.craftizz.mnpc.npc.actions.MessageAction;
import io.github.craftizz.mnpc.npc.actions.SoundAction;
import io.github.craftizz.mnpc.npc.requirement.EqualsRequirement;
import io.github.craftizz.mnpc.npc.requirement.GreaterThanRequirement;
import io.github.craftizz.mnpc.npc.requirement.LessThanRequirement;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.intellij.lang.annotations.Subst;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigurationHandler {

    private final MNPC plugin;

    private final Yaml npcConfig;
    private final Pattern bracketPattern;

    public ConfigurationHandler(final @NotNull MNPC plugin) {
        this.plugin = plugin;
        this.bracketPattern = Pattern.compile("(?<=\\[).+?(?=\\])");
        this.npcConfig = LightningBuilder
                .fromPath("npc", plugin.getDataFolder().getAbsolutePath())
                .addInputStreamFromResource("npc.yml")
                .createYaml();
    }

    public void loadNPcs() {

        final NPCManager npcManager = plugin.getNpcManager();


        for (final String keyName : npcConfig.singleLayerKeySet()) {
            
            final SocialNPC socialNPC = new SocialNPC(npcConfig.getInt(keyName + ".npc-id"));

            addRequirements(socialNPC, npcConfig.getSection(keyName + ".requirements"));

            final List<String> firstClick = npcConfig.getStringList(keyName + ".first-click-page");

            if (!firstClick.isEmpty()) {
                socialNPC.setFirstClickPage(loadPage(firstClick));
            }

            for (final String pageName : npcConfig.singleLayerKeySet(keyName + ".pages")) {
                final Page page = loadPage(npcConfig.getStringList(keyName + ".pages." + pageName));
                socialNPC.addPage(page);
            }

            npcManager.addSocialNPC(socialNPC);
        }
    }

    public void addRequirements(final @NotNull SocialNPC socialNPC,
                                final @NotNull FlatFileSection section) {

        for (final String keyName : section.singleLayerKeySet()) {

            final String type = section.getString(keyName + ".type");
            final String placeholder = section.getString(keyName + ".placeholder");
            final String requirement = section.getString(keyName + ".requirement");
            final String denyMessage = section.getString(keyName + ".deny-message");

            switch (type) {
                case ">" -> socialNPC.addRequirement(new GreaterThanRequirement(placeholder, Double.parseDouble(requirement), denyMessage));
                case "<" -> socialNPC.addRequirement(new LessThanRequirement(placeholder, Double.parseDouble(requirement), denyMessage));
                case "=" -> socialNPC.addRequirement(new EqualsRequirement(placeholder, requirement, denyMessage));
                default -> throw new IllegalArgumentException(keyName + ":" + type);
            }
        }
    }

    public Page loadPage(final @NotNull List<String> rawActions) {

        final Page page = new Page();

        for (final String action : rawActions) {

            final Matcher matcher = bracketPattern.matcher(action);

            if (!matcher.find()) {
                throw new IllegalArgumentException(action);
            }

            final String actionData = action.substring(matcher.end() + 2);

            switch (matcher.group()) {
                case "message" -> page.addAction(new MessageAction(actionData));
                case "command" -> page.addAction(new CommandAction(actionData));
                case "console" -> page.addAction(new ConsoleAction(actionData));
                case "sound" -> {

                    final String[] soundData = actionData.split(" ");
                    final String[] musicKey = soundData[0].split(":");
                    if (musicKey.length != 2 || soundData.length != 3) {
                        throw new IllegalArgumentException(action);
                    }
                    @Subst("minecraft") final String namespace = musicKey[0];
                    @Subst("empty") final String soundKey = musicKey[1];
                    final Key key = Key.key(namespace, soundKey);
                    final Sound sound = Sound.sound(key, Sound.Source.MASTER,
                            Float.parseFloat(soundData[1]),
                            Float.parseFloat(soundData[2]));
                    page.addAction(new SoundAction(sound));
                }
                default -> throw new IllegalArgumentException(action);
            }
        }

        return page;
    }
}
