package net.java_rin.KillSound.sounds;

import net.java_rin.KillSound.utilities.RandomList;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class KillSoundData {
    private List<String> soundNames = new ArrayList<String>();
    private ItemStack soundItem;

    public KillSoundData(List<String> soundName, ItemStack soundItem) {
        this.soundNames = soundName;
        this.soundItem = soundItem;
    }

    @SuppressWarnings("deprecation")
    public void playSound(Player player) {
        player.playSound(player.getLocation(), RandomList.random(this.soundNames), 100F, 1F);
    }

    public ItemStack getSoundItem() {
        return this.soundItem;
    }
}
