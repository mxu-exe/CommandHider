package com.umnirium.mc.commandhider.utils;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static com.umnirium.mc.commandhider.CommandHider.plugin;

@SuppressWarnings("UnstableApiUsage")
public class CommandUtils {

    public static LiteralCommandNode<CommandSourceStack> createCommand() {
        return Commands.literal("commandhider")
                .requires(ctx -> ctx.getSender().hasPermission("commandhider.command"))
                .then(Commands.literal("refresh")
                        .requires(ctx -> ctx.getSender().hasPermission("commandhider.command.refresh"))
                        .executes(CommandUtils::executeRefresh)
                )
                .then(Commands.literal("reload")
                        .requires(ctx -> ctx.getSender().hasPermission("commandhider.command.reload"))
                        .executes(CommandUtils::executeReload)
                )
                .then(Commands.literal("version")
                        .requires(ctx -> ctx.getSender().hasPermission("commandhider.command.version"))
                        .executes(CommandUtils::executeVersion)
                )
                .build();
    }

    private static int executeRefresh(CommandContext<CommandSourceStack> context) {
        CommandHideManager.refreshPlayers();

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.updateCommands();
        }

        context.getSource().getSender().sendMessage(MessageUtils.component("<aqua>Players refreshed.</aqua>"));

        return Command.SINGLE_SUCCESS;
    }

    private static int executeReload(CommandContext<CommandSourceStack> context) {
        ConfigManager.reloadConfig();

        context.getSource().getSender().sendMessage(MessageUtils.component("<aqua>Configuration reloaded.</aqua>"));

        return Command.SINGLE_SUCCESS;
    }

    private static int executeVersion(CommandContext<CommandSourceStack> context) {
        context.getSource().getSender().sendMessage(MessageUtils.componentReplace("<gold>[CommandHider]</gold> <aqua>Version <version></aqua>", "version", Component.text(plugin.getPluginMeta().getVersion())));

        return Command.SINGLE_SUCCESS;
    }
}
