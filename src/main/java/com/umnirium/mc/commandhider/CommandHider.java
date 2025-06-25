package com.umnirium.mc.commandhider;

import com.umnirium.mc.commandhider.utils.CommandUtils;
import com.umnirium.mc.commandhider.utils.ConfigManager;
import com.umnirium.mc.commandhider.utils.EventManager;
import com.umnirium.mc.commandhider.utils.MessageUtils;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("UnstableApiUsage")
public class CommandHider extends JavaPlugin {
    public static CommandHider plugin;
    public static ComponentLogger LOGGER;

    @Override
    public void onEnable() {
        plugin = this;
        LOGGER = this.getComponentLogger();

        ConfigManager.saveConfig();

        Bukkit.getPluginManager().registerEvents(new EventManager(), this);

        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> commands.registrar().register(CommandUtils.createCommand()));

        LOGGER.info(MessageUtils.component("<gold>CommandHider successfully enabled!</gold>"));
    }

    @Override
    public void onDisable() {
        LOGGER.info(MessageUtils.component("<gold>CommandHider successfully disabled!</gold>"));
    }
}