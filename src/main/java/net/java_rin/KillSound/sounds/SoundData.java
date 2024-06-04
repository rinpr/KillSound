package net.java_rin.KillSound.sounds;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

abstract class SoundData {
    public abstract void playSound(Player player);
    public abstract ItemStack getSoundItem();
    public abstract void giveSoundItem(Player player);
}
