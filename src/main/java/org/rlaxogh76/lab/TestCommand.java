package org.rlaxogh76.lab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String @NotNull [] args) {
        if(cmd.getName().equalsIgnoreCase("test")) {
            if (sender instanceof Player) {
                sender.sendMessage("플러그인 작동");
                return false;
            }
            sender.sendMessage("플러그인이 작동됨.");
            return false;
        }
    return true;
    }
}
