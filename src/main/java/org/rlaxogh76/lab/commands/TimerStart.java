package org.rlaxogh76.lab.commands;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class TimerStart implements CommandExecutor {

    private final Plugin plugin;
    private BossBar timerBossBar;
    private BukkitTask timerTask;

    public TimerStart(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("플레이어만 사용할 수 있습니다.");
            return true;
        }

        int seconds = 60;
        if (args.length > 0) {
            try {
                seconds = Integer.parseInt(args[0]);
                if (seconds <= 0) {
                    player.sendMessage("1초 이상의 값을 입력해주세요.");
                    return true;
                }
            } catch (NumberFormatException e) {
                player.sendMessage("숫자를 입력해주세요. 예: /타이머 60");
                return true;
            }
        }

        if (timerTask != null) {
            timerTask.cancel();
        }
        clearBossBar(timerBossBar);

        final int totalSeconds = seconds;
        timerBossBar = Bukkit.createBossBar(formatTitle(totalSeconds), BarColor.RED, BarStyle.SOLID);
        timerBossBar.setProgress(1.0);

        for (Player p : Bukkit.getOnlinePlayers()) {
            timerBossBar.addPlayer(p);
        }

        Bukkit.broadcastMessage("타이머가 시작되었습니다. (" + totalSeconds + "초)");

        final int[] remaining = { totalSeconds };
        timerTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (remaining[0] <= 0) {
                Bukkit.broadcastMessage("타이머가 종료되었습니다!");
                clearBossBar(timerBossBar);
                timerTask.cancel();
                timerTask = null;
                return;
            }

            double progress = (double) remaining[0] / totalSeconds;
            timerBossBar.setProgress(Math.max(0.0, Math.min(1.0, progress)));
            timerBossBar.setTitle(formatTitle(remaining[0]));

            remaining[0]--;
        }, 0L, 20L);

        return true;
    }

    private String formatTitle(int secondsLeft) {
        int min = secondsLeft / 60;
        int sec = secondsLeft % 60;
        return String.format("남은 시간: %02d:%02d", min, sec);
    }

    private void clearBossBar(BossBar bossBar) {
        if (bossBar == null)
            return;
        for (Player p : new ArrayList<>(bossBar.getPlayers())) {
            bossBar.removePlayer(p);
        }
    }
}