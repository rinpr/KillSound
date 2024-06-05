package net.java_rin.KillSound.sounds;

import net.java_rin.KillSound.manager.ConfigManager;
import net.java_rin.KillSound.manager.PlayerDataHolder;
import net.java_rin.KillSound.utilities.Message;
import net.java_rin.KillSound.utilities.RandomList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SoundData {

    @SuppressWarnings("deprecation")
    public static void play(Player player) {
        Sound sound = PlayerDataHolder.getPlayerData(player).getEnabledSound();
        Message.send(player, "&aPlaying sound: &f" + sound);
        if (sound == Sound.NONE) return;
        if (sound == Sound.ONE) player.playSound(player.getLocation(), RandomList.random(ConfigManager.SOUNDS_1_STRING), 100F, 1F);
        if (sound == Sound.TWO) player.playSound(player.getLocation(), RandomList.random(ConfigManager.SOUNDS_2_STRING), 100F, 1F);
        if (sound == Sound.THREE) player.playSound(player.getLocation(), RandomList.random(ConfigManager.SOUNDS_3_STRING), 100F, 1F);
        if (sound == Sound.FOUR) player.playSound(player.getLocation(), RandomList.random(ConfigManager.SOUNDS_4_STRING), 100F, 1F);
        if (sound == Sound.FIVE) player.playSound(player.getLocation(), RandomList.random(ConfigManager.SOUNDS_5_STRING), 100F, 1F);
        if (sound == Sound.SIX) player.playSound(player.getLocation(), RandomList.random(ConfigManager.SOUNDS_6_STRING), 100F, 1F);
        if (sound == Sound.SEVEN) player.playSound(player.getLocation(), RandomList.random(ConfigManager.SOUNDS_7_STRING), 100F, 1F);
    }

    public static ItemStack getSoundItem(Sound sound) {
        if (sound == Sound.ONE) return ConfigManager.SOUNDS_ITEM.get(0);
        if (sound == Sound.TWO) return ConfigManager.SOUNDS_ITEM.get(1);
        if (sound == Sound.THREE) return ConfigManager.SOUNDS_ITEM.get(2);
        if (sound == Sound.FOUR) return ConfigManager.SOUNDS_ITEM.get(3);
        if (sound == Sound.FIVE) return ConfigManager.SOUNDS_ITEM.get(4);
        if (sound == Sound.SIX) return ConfigManager.SOUNDS_ITEM.get(5);
        if (sound == Sound.SEVEN) return ConfigManager.SOUNDS_ITEM.get(6);
        return new ItemStack(Material.STONE);
    }

    public static List<ItemStack> getSoundItems() {
        return ConfigManager.SOUNDS_ITEM;
    }

}
