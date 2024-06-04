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

    public static void open(Player player) {
        Inventory gui = Bukkit.createInventory(player, GUI_COLUMN * 9, ChatColor.translateAlternateColorCodes('&', ConfigManager.GUI_NAME));
        setDisc(gui);

        player.openInventory(gui);
        Message.send(player, ConfigManager.PREFIX + "&aOpened Sound GUI");
    }

    private static void setAvailabilityDisc(Inventory inventory, Player player) {
        final short noDisc = 14;
        final short hasDisc = 7;
        final short enabledDisc = 5;

        // todo: check which disc is enabled and set it to lime wool
        for (int slot = 20; slot <= 26; slot++) {
            if (player.hasPermission("killsound.custom." + (slot - 19))) {
                inventory.setItem(slot, new ItemStack(Material.WOOL, 1, hasDisc));
            } else {
                inventory.setItem(slot, new ItemStack(Material.WOOL, 1, noDisc));
            }
        }
    }

    private static void setDisc(Inventory inventory) {
        inventory.setItem(11, Sound1.getSoundItem());
        inventory.setItem(12, Sound2.getSoundItem());
        inventory.setItem(13, Sound3.getSoundItem());
        inventory.setItem(14, Sound4.getSoundItem());
        inventory.setItem(15, Sound5.getSoundItem());
        inventory.setItem(16, Sound6.getSoundItem());
        inventory.setItem(17, Sound7.getSoundItem());
    }
}
