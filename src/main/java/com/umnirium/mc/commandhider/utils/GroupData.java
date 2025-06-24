package com.umnirium.mc.commandhider.utils;

import java.util.List;

public record GroupData(List<String> inherits, String bypassPermission, List<String> commands) {
}
