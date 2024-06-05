package net.java_rin.KillSound.listeners;

import net.java_rin.KillSound.manager.PlayerData;
import net.java_rin.KillSound.manager.PlayerDataHolder;
import net.java_rin.KillSound.sounds.SoundData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = event.getEntity().getKiller();
        // todo: check if killer has enabled any killsound disc and play the sound
        SoundData.playKillSound(killer, victim);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.GRAY + "(" + ChatColor.GREEN + "+" + ChatColor.GRAY + ") " + ChatColor.RESET + player.getName());
        PlayerDataHolder.add(new PlayerData(player));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.GRAY + "(" + ChatColor.RED + "-" + ChatColor.GRAY + ") " + ChatColor.RESET + player.getName());
    }
}
