package com.umnirium.mc.commandhider.utils;

import java.util.List;

public class GroupData {
    private final List<String> inherits;
    private final String bypassPermission;
    private final List<String> commands;

    public GroupData(List<String> inherits, String bypassPermission, List<String> commands) {
        this.inherits = inherits;
        this.bypassPermission = bypassPermission;
        this.commands = commands;
    }

    public List<String> getInherits() {
        return this.inherits;
    }

    public String getBypassPermission() {
        return this.bypassPermission;
    }

    public List<String> getCommands() {
        return this.commands;
    }
}
