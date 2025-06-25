package com.umnirium.mc.commandhider.utils;

import org.bukkit.entity.Player;
import java.util.*;

public class CommandHideManager  {
    public static Map<Player, Set<String>> handledPlayers = new HashMap<>();

    public static void refreshPlayers() {handledPlayers.clear();}

    public static void removePlayer(Player player) {
        handledPlayers.remove(player);
    }

    public static Set<String> setHiddenCommands(Player player) {
        if (handledPlayers.containsKey(player)) {
            return handledPlayers.get(player);
        }

        Map<String, GroupData> groups = ConfigManager.groups;
        Set<String> commands = new HashSet<>();
        Set<String> groupsDone = new HashSet<>();

        for (String group : groups.keySet()) {
            if (!player.hasPermission(groups.get(group).bypassPermission())) {
                setHiddenCommandsRecursive(groups, group, commands, groupsDone);
            }
        }

        handledPlayers.put(player, commands);

        return commands;
    }

    private static void setHiddenCommandsRecursive(Map<String, GroupData> groups, String group, Set<String> commands, Set<String> groupsDone) {
        if (!groupsDone.contains(group)) {
            commands.addAll(groups.get(group).commands());
            groupsDone.add(group);

            for (String inherit : groups.get(group).inherits()) {
                setHiddenCommandsRecursive(groups, inherit, commands, groupsDone);
            }
        }
    }
}
