package com.umnirium.mc.commandhider;

import com.umnirium.mc.commandhider.utils.MessageUtils;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandHider extends JavaPlugin {
    public static CommandHider plugin;
    public static ComponentLogger LOGGER;

    @Override
    public void onEnable() {
        plugin = this;
        LOGGER = this.getComponentLogger();

        saveDefaultConfig();

        LOGGER.info(MessageUtils.component("<gold>CommandHider successfully enabled!</gold>"));
    }

    @Override
    public void onDisable() {
        LOGGER.info(MessageUtils.component("<gold>CommandHider successfully disabled!</gold>"));
    }
}