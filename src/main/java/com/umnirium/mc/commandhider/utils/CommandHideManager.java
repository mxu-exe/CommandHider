package com.umnirium.mc.commandhider.utils;

import org.bukkit.entity.Player;
import java.util.*;

public class CommandHideManager  {
    public static Map<Player, Set<String>> handledPlayers = new HashMap<>();
    public static Map<String, GroupData> groups = ConfigManager.groups;

    public static void removePlayer(Player player) {
        handledPlayers.remove(player);
    }

    public static Set<String> setHiddenCommands(Player player) {
        if (handledPlayers.containsKey(player)) {
            return handledPlayers.get(player);
        }

        Set<String> commands = new HashSet<>();
        Set<String> groupsDone = new HashSet<>();

        for (String group : groups.keySet()) {
            if (!player.hasPermission(groups.get(group).bypassPermission())) {
                setHiddenCommandsRecursive(group, commands, groupsDone);
            }
        }

        handledPlayers.put(player, commands);

        return commands;
    }

    private static void setHiddenCommandsRecursive(String group, Set<String> commands, Set<String> groupsDone) {
        if (!groupsDone.contains(group)) {
            commands.addAll(groups.get(group).commands());
            groupsDone.add(group);

            for (String inherit : groups.get(group).inherits()) {
                setHiddenCommandsRecursive(inherit, commands, groupsDone);
            }
        }
    }
}
