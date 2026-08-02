package org.rlaxogh76.lab;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Lab extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("test")).setExecutor(new TestCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
