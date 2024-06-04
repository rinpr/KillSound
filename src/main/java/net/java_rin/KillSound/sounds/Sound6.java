package net.java_rin.KillSound.sounds;

import net.java_rin.KillSound.manager.ConfigManager;
import net.java_rin.KillSound.utilities.RandomList;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Sound6 {

    @SuppressWarnings("deprecation")
    public static void playSound(Player player) { player.playSound(player.getLocation(), RandomList.random(ConfigManager.SOUNDS_6_STRING), 100F, 1F); }

    public static ItemStack getSoundItem() { return ConfigManager.SOUNDS_ITEM.get(5); }

    public static void giveSoundItem(Player player) { player.getInventory().addItem(getSoundItem()); }
}
