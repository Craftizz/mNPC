package io.github.craftizz.mnpc;

import io.github.craftizz.mnpc.commands.ReloadCommand;
import io.github.craftizz.mnpc.configuration.ConfigurationHandler;
import io.github.craftizz.mnpc.listeners.ClickListener;
import io.github.craftizz.mnpc.managers.NPCManager;
import me.mattstudios.mf.base.CommandManager;
import me.mattstudios.mf.base.CompletionHandler;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Collectors;

public final class MNPC extends JavaPlugin {

    private ConfigurationHandler configurationHandler;
    private CommandManager commandManager;
    private NPCManager npcManager;

    @Override
    public void onEnable() {

        this.configurationHandler = new ConfigurationHandler(this);
        this.npcManager = new NPCManager();

        final PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new ClickListener(this), this);

        this.commandManager = new CommandManager(this);
        registerCompletions();
        commandManager.register(new ReloadCommand(this));

        configurationHandler.loadNPcs();
        // npcManager.startTask();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerCompletions() {

        final CompletionHandler handler = commandManager.getCompletionHandler();
        handler.register("#npcs", input -> npcManager.getNpcMap().keySet()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList()));
    }

    public void reload() {
        npcManager.clearNPCs();
        configurationHandler.loadNPcs();
    }

    public ConfigurationHandler getConfigurationHandler() {
        return configurationHandler;
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }
}
