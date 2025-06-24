package com.umnirium.mc.commandhider.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Set;

public class EventManager implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        CommandHideManager.removePlayer(player);
    }

    @EventHandler
    public void onCommandSend(PlayerCommandSendEvent event) {
        Player player = event.getPlayer();

        Set<String> commands = CommandHideManager.setHiddenCommands(player);

        event.getCommands().removeAll(commands);

        player.sendMessage(commands.toString());
    }

    @EventHandler
    public void onCommandProcess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        String[] parts = message.substring(1).split(" ");
        String command = parts[0].toLowerCase();

        Set<String> commands = CommandHideManager.setHiddenCommands(player);

        if (commands.contains(command)) {
            event.setCancelled(true);
            
            player.sendMessage(MiniMessage.miniMessage().deserialize("<red>Unknown or incomplete command, see below for error</red>\n<cmd><red><--[HERE]</red>",
                    Placeholder.component("cmd", Component.text(command, NamedTextColor.RED, TextDecoration.UNDERLINED))));
        }
    }
}
