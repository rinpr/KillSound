package net.java_rin.KillSound.sounds;

import net.java_rin.KillSound.manager.ConfigManager;
import net.java_rin.KillSound.utilities.RandomList;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Sound5 extends SoundData{
    @Override
    @SuppressWarnings("deprecation")
    public void playSound(Player player) {
        player.playSound(player.getLocation(), RandomList.random(ConfigManager.SOUNDS_5_STRING), 100F, 1F);
    }

    @Override
    public ItemStack getSoundItem() {
        return ConfigManager.SOUNDS_ITEM.get(4);
    }

    @Override
    public void giveSoundItem(Player player) {
        player.getInventory().addItem(getSoundItem());
    }
}
