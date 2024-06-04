package net.java_rin.KillSound.manager;

import net.java_rin.KillSound.sounds.Sound1;
import net.java_rin.KillSound.sounds.Sound2;
import net.java_rin.KillSound.sounds.Sound3;
import net.java_rin.KillSound.utilities.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SoundGUI {

    private static final int GUI_COLUMN = 3;

    public static void open(Player player) {
        Inventory gui = Bukkit.createInventory(player, GUI_COLUMN * 9, ChatColor.translateAlternateColorCodes('&', ConfigManager.GUI_NAME));

        gui.setItem(11, Sound1.getSoundItem());
        gui.setItem(12, Sound2.getSoundItem());
        gui.setItem(13, Sound3.getSoundItem());

        player.openInventory(gui);
        Message.send(player, ConfigManager.PREFIX + "&aOpened Sound GUI");
    }
}
