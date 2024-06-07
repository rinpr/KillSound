package net.java_rin.KillSound.listeners;

import net.java_rin.KillSound.events.PlayerUnlockDiscEvent;
import net.java_rin.KillSound.hooks.implementation.VaultPermissionHook;
import net.java_rin.KillSound.manager.ConfigManager;
import net.java_rin.KillSound.manager.PlayerData;
import net.java_rin.KillSound.manager.PlayerDataHolder;
import net.java_rin.KillSound.sounds.Disc;
import net.java_rin.KillSound.sounds.DiscData;
import net.java_rin.KillSound.utilities.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onUnlockDisc(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        ItemStack hand_item = event.getItem();
        if (!ConfigManager.SOUNDS_ITEM.contains(hand_item)) return;

        Player player = event.getPlayer();
        String permission = DiscData.getPermissionFromDisc(hand_item);

        PlayerUnlockDiscEvent playerUnlockDiscEvent = new PlayerUnlockDiscEvent(player, DiscData.getDisc(hand_item));
        Bukkit.getPluginManager().callEvent(playerUnlockDiscEvent);

        if (!playerUnlockDiscEvent.isCancelled()) {
            player.getInventory().removeItem(hand_item);
            VaultPermissionHook.getInstance().playerAdd(player, permission);
            Message.send(player, ConfigManager.PREFIX + "You have obtained a new disc!");
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = event.getEntity().getKiller();
        // todo: check if killer has enabled any killsound disc and play the sound
        DiscData.playKillSound(killer, victim);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerDataHolder.add(new PlayerData(player));
    }
}
