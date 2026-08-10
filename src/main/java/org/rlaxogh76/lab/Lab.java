package org.rlaxogh76.lab;

import org.bukkit.plugin.java.JavaPlugin;
import org.rlaxogh76.lab.commands.InventoryListener;

import java.util.Objects;

public final class Lab extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("test")).setExecutor(new TestCommand(this));
        this.getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
