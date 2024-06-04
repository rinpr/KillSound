package net.java_rin.KillSound.sounds;

import net.java_rin.KillSound.manager.ConfigManager;
import net.java_rin.KillSound.utilities.RandomList;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Sound7 extends SoundData{
    @Override
    @SuppressWarnings("deprecation")
    public void playSound(Player player) {
        player.playSound(player.getLocation(), RandomList.random(ConfigManager.SOUNDS_7_STRING), 100F, 1F);
    }

    @Override
    public ItemStack getSoundItem() {
        return ConfigManager.SOUNDS_ITEM.get(6);
    }

    @Override
    public void giveSoundItem(Player player) {
        player.getInventory().addItem(getSoundItem());
    }
}
