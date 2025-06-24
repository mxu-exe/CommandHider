package com.umnirium.mc.commandhider.utils;

import org.bukkit.configuration.ConfigurationSection;
import java.util.HashMap;
import java.util.Map;

import static com.umnirium.mc.commandhider.CommandHider.plugin;

public class ConfigManager {
    public static int configVersion;
    public static Map<String, GroupData> groups;

    public static void saveConfig() {
        plugin.saveDefaultConfig();

        configVersion = getConfigVersion();
        groups = getGroups();
    }

    private static int getConfigVersion() {
        return plugin.getConfig().getInt("version");
    }

    private static Map<String, GroupData> getGroups() {
        Map<String, GroupData> groups = new HashMap<>();

        ConfigurationSection groupsSection = plugin.getConfig().getConfigurationSection("groups");

        if (groupsSection != null) {
            for (String groupName : groupsSection.getKeys(false)) {
                ConfigurationSection group = groupsSection.getConfigurationSection(groupName);

                if (group != null) {
                    groups.put(groupName, new GroupData(
                            groupName,
                            group.getStringList("inherit-groups"),
                            group.getString("bypass-permission"),
                            group.getStringList("commands")));
                }
            }
        }

        return groups;
    }
}
