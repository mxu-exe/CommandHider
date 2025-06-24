package com.umnirium.mc.commandhider;

import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandHider extends JavaPlugin {
    public static CommandHider plugin;
    public static ComponentLogger LOGGER;

    @Override
    public void onEnable() {
        plugin = this;
        LOGGER = this.getComponentLogger();

        LOGGER.info("CommandHider successfully enabled!");
    }

    @Override
    public void onDisable() {
        LOGGER.info("CommandHider successfully disabled!");
    }
}