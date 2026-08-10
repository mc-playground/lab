package org.rlaxogh76.lab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.rlaxogh76.lab.commands.MyInventory;

public class TestCommand implements CommandExecutor {

    private final Lab plugin;

    public TestCommand(Lab plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label,
            @NotNull String @NotNull [] args) {
        if (!cmd.getName().equalsIgnoreCase("test")) {
            return true;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage("플레이어만 사용할 수 있습니다.");
            return true;
        }

        MyInventory myInventory = new MyInventory(plugin);
        player.openInventory(myInventory.getInventory());
        return true;
    }
}