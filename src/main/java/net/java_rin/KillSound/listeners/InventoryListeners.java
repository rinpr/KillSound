package net.java_rin.KillSound.listeners;

import net.java_rin.KillSound.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class InventoryListeners implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', ConfigManager.GUI_NAME))) { event.setCancelled(true); }
        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();
        player.sendMessage(ChatColor.GREEN + "Slots: " + ChatColor.RESET + slot);
    }
}
