package com.umnirium.mc.commandhider.utils;

import static com.umnirium.mc.commandhider.CommandHider.plugin;

public class ConfigManager {
    public static int getConfigVersion() {
        return plugin.getConfig().getInt("version");
    }
}
