package org.rlaxogh76.lab;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.rlaxogh76.lab.commands.InventoryListener;
import org.rlaxogh76.lab.commands.TimerStart;

import java.util.Objects;

public final class Lab extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("test")).setExecutor(new TestCommand(this));
        this.getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("타이머").setExecutor(new TimerStart(this));
    }

    @EventHandler
    public void onPlayerJoinBoard(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        updateScoreBoard(player);
    }

    private void updateScoreBoard(Player player) {
        int deathCount = player.getStatistic(Statistic.DEATHS);

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        Objective o = board.registerNewObjective(
                "yarhanServer", // 내부 식별용 이름 (16자 제한, 영문/숫자 권장)
                "dummy",
                ChatColor.BOLD + "야르한 서버" // 실제 표시될 제목
        );
        o.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score score = o.getScore("죽은 횟수: ");
        score.setScore(deathCount);

        player.setScoreboard(board);
    }

    @Override
    public void onDisable() {
    }
}