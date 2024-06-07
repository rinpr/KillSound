package net.java_rin.KillSound.manager;

import net.java_rin.KillSound.sounds.*;
import net.java_rin.KillSound.utilities.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SoundGUI {

    private static final int GUI_COLUMN = 3;
    private PlayerData playerData;
    private Player player;
    private Inventory inventory;

    public SoundGUI(PlayerData playerData) {
        this.playerData = playerData;
        this.player = playerData.getPlayer().getPlayer();
        this.inventory = Bukkit.createInventory(this.player, GUI_COLUMN * 9, ChatColor.translateAlternateColorCodes('&', ConfigManager.GUI_NAME));
        setEnable();
        setDisc();
        setAvailabilityDisc();
    }

    public void open() {
        this.player.openInventory(this.inventory);
        Message.send(this.player, ConfigManager.PREFIX + "&aOpened Sound GUI");
    }

    public static void update(Inventory inventory, PlayerData playerData) {
        // set enable buttons
        if (playerData.isEnabled()) {
            inventory.setItem(9, ConfigManager.ENABLE_BUTTON);
        } else {
            inventory.setItem(9, ConfigManager.DISABLE_BUTTON);
        }

        // set sound disc
        int slot = 11;
        for (ItemStack item : DiscData.getDiscItems()) {
            inventory.setItem(slot, item);
            slot++;
        }

        int enabled_disc_slot = DiscData.getAvailabilitySlot(playerData.getSound());
        // set availability disc
        for (int loop_slot = 20; loop_slot <= 26; loop_slot++) {
            if (loop_slot == enabled_disc_slot) {
                inventory.setItem(loop_slot, ConfigManager.SELECTED_BUTTON);
                continue;
            }
            if (playerData.getPlayer().getPlayer().hasPermission("killsound.custom." + (loop_slot - 19))) {
                inventory.setItem(loop_slot, ConfigManager.AVAILABLE_BUTTON);
            } else {
                inventory.setItem(loop_slot, ConfigManager.UNAVAILABLE_BUTTON);
            }
        }
    }

    private void setEnable() {
        if (this.playerData.isEnabled()) {
            this.inventory.setItem(9, ConfigManager.ENABLE_BUTTON);
        } else {
            this.inventory.setItem(9, ConfigManager.DISABLE_BUTTON);
        }
    }

    private void setAvailabilityDisc() {
        Disc disc = this.playerData.getSound();
        int enabled_disc_slot = DiscData.getAvailabilitySlot(disc);

        for (int slot = 20; slot <= 26; slot++) {
            if (slot == enabled_disc_slot) {
                this.inventory.setItem(slot, ConfigManager.SELECTED_BUTTON);
                continue;
            }
            if (this.player.hasPermission("killsound.custom." + (slot - 19))) {
                this.inventory.setItem(slot, ConfigManager.AVAILABLE_BUTTON);
            } else {
                this.inventory.setItem(slot, ConfigManager.UNAVAILABLE_BUTTON);
            }
        }
    }

    private void setDisc() {
        int slot = 11;
        for (ItemStack item : DiscData.getDiscItems()) {
            this.inventory.setItem(slot, item);
            slot++;
        }
    }
}
