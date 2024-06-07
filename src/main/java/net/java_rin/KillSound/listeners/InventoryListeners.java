package net.java_rin.KillSound.listeners;

import net.java_rin.KillSound.manager.ConfigManager;
import net.java_rin.KillSound.manager.PlayerData;
import net.java_rin.KillSound.manager.PlayerDataHolder;
import net.java_rin.KillSound.manager.SoundGUI;
import net.java_rin.KillSound.sounds.Disc;
import net.java_rin.KillSound.sounds.DiscData;
import net.java_rin.KillSound.utilities.Message;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryListeners implements Listener {

    @EventHandler
    public void onKillSoundGUIClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', ConfigManager.GUI_NAME))) return;

        // Get the required data for later usage
        Player player = (Player) event.getWhoClicked();
        PlayerData playerData = PlayerDataHolder.getPlayerData(player);
        int clicked_slot_index = event.getSlot();
        ItemStack clicked_slot_item = event.getCurrentItem();
        Inventory inventory = event.getInventory();

        // Logic with the clicked_slot_index
        if (clicked_slot_index == 9) {
            // Toggle KillSound on/off
            ItemStack newItem = clicked_slot_item.equals(ConfigManager.DISABLE_BUTTON) ? ConfigManager.ENABLE_BUTTON : ConfigManager.DISABLE_BUTTON;
            boolean toggle_sound = playerData.isEnabled() ? playerData.setEnabled(false) : playerData.setEnabled(true);
            inventory.setItem(clicked_slot_index, newItem);
        } else if (clicked_slot_index >= 20 && clicked_slot_index <= 26) {
            // check if the clicked slot is not player's inventory
            if (
                    !(clicked_slot_item.equals(ConfigManager.UNAVAILABLE_BUTTON) ||
                    clicked_slot_item.equals(ConfigManager.AVAILABLE_BUTTON)||
                    clicked_slot_item.equals(ConfigManager.SELECTED_BUTTON))
            ) {
                Message.send(player, "&cERROR 001: gui_validation");
                event.setCancelled(true);
                return;
            }

            // If player haven't unlocked the sound disc yet will cancel the event
            if (!player.hasPermission("killsound.custom." + (clicked_slot_index - 19))) {
                event.setCancelled(true);
                return;
            }

            Disc clickedDisc = DiscData.getDiscFromAvailabilitySlot(clicked_slot_index);
            // if the clicked slot is already enabled
            if (clickedDisc == playerData.getSound()) {
                playerData.setSound(Disc.NONE);
                SoundGUI.update(inventory, playerData);
                event.setCancelled(true);
            } else {
                playerData.setSound(clickedDisc);
                SoundGUI.update(inventory, playerData);
            }
        }
        // Always cancel event to prevent bad things
        event.setCancelled(true);
    }
}
