package io.github.craftizz.mnpc.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class MessageUtil {

    public static MiniMessage miniMessage = MiniMessage.get();

    public static void sendMessage(final @NotNull Player player,
                                   final @NotNull String message) {
        player.sendMessage(miniMessage.parse(message));
    }

    public static void sendMessage(final @NotNull Player player,
                                   final @NotNull String message,
                                   final @NotNull String... placeholders) {
        player.sendMessage(miniMessage.parse(message, placeholders));
    }

    public static Component parse(final @NotNull String message) {
        return miniMessage.parse(message);
    }

    public static Component parse(final @NotNull String message,
                                  final @NotNull String... placeholders) {
        return miniMessage.parse(message, placeholders);
    }

    /**
     * Use to parse many language for GUIs
     *
     * @param messages the list of string messages to be parsed
     * @return the parsed languages in component
     */
    public static List<Component> parseMany(List<String> messages) {
        return messages.stream().map(MessageUtil::parse).collect(Collectors.toList());
    }

    /**
     * Use to parse many language for GUIs with placeholders as parsed
     *
     * @param messages the list of string messages to be parsed
     * @param placeholders the text values and placeholders to be parsed
     * @return the parsed languages in component
     */
    public static List<Component> parseMany(List<String> messages, String... placeholders) {
        return messages.stream().map(message -> parse(message, placeholders)).collect(Collectors.toList());
    }

}
