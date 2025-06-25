package com.umnirium.mc.commandhider.utils;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

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
        return Command.SINGLE_SUCCESS;
    }

    private static int executeReload(CommandContext<CommandSourceStack> context) {
        return Command.SINGLE_SUCCESS;
    }

    private static int executeVersion(CommandContext<CommandSourceStack> context) {
        return Command.SINGLE_SUCCESS;
    }
}
