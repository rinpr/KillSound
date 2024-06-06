package net.java_rin.KillSound.manager;

import net.java_rin.KillSound.sounds.*;
import net.java_rin.KillSound.utilities.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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

    @Deprecated
    public static void update(Player player) {
        Inventory inventory = Bukkit.createInventory(player, GUI_COLUMN * 9, ChatColor.translateAlternateColorCodes('&', ConfigManager.GUI_NAME));
        PlayerData playerData = PlayerDataHolder.getPlayerData(player);

        // set enable buttons
        if (playerData.isEnabled()) {
            inventory.setItem(9, new ItemStack(Material.WOOL, 1, (short) 5));
        } else {
            inventory.setItem(9, new ItemStack(Material.WOOL, 1, (short) 14));
        }

        // set sound disc
        int slot = 11;
        for (ItemStack item : SoundData.getSoundItems()) {
            inventory.setItem(slot, item);
            slot++;
        }

        player.openInventory(inventory);
    }

    private void setEnable() {
        if (this.playerData.isEnabled()) {
            this.inventory.setItem(9, ConfigManager.ENABLE_BUTTON);
        } else {
            this.inventory.setItem(9, ConfigManager.DISABLE_BUTTON);
        }
    }

    private void setAvailabilityDisc() {
        Sound sound = this.playerData.getSound();
        int enabled_disc_slot = SoundData.getAvailabilitySlot(sound);

        // todo: check which disc is enabled and set it to ConfigManager.SELECTED_BUTTON
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
        for (ItemStack item : SoundData.getSoundItems()) {
            this.inventory.setItem(slot, item);
            slot++;
        }
    }
}
