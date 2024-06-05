package net.java_rin.KillSound.sounds;

import net.java_rin.KillSound.manager.ConfigManager;
import net.java_rin.KillSound.manager.PlayerData;
import net.java_rin.KillSound.manager.PlayerDataHolder;
import net.java_rin.KillSound.utilities.RandomList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.List;

/**
 * Utility class for managing sound-related data and operations.
 */
public class SoundData {

    /**
     * Plays the kill sound for the given players based on their settings.
     *
     * @param player  The player who kill.
     * @param player2 The victim player.
     */
    public static void playKillSound(Player player, Player player2) {
        PlayerData playerData = PlayerDataHolder.getPlayerData(player);
        if (!playerData.isEnabled()) return;
        Sound sound = playerData.getSound();
        if (sound == Sound.NONE) return;

        EnumMap<Sound, List<String>> soundMap = new EnumMap<>(Sound.class);
        soundMap.put(Sound.ONE, ConfigManager.SOUNDS_1_STRING);
        soundMap.put(Sound.TWO, ConfigManager.SOUNDS_2_STRING);
        soundMap.put(Sound.THREE, ConfigManager.SOUNDS_3_STRING);
        soundMap.put(Sound.FOUR, ConfigManager.SOUNDS_4_STRING);
        soundMap.put(Sound.FIVE, ConfigManager.SOUNDS_5_STRING);
        soundMap.put(Sound.SIX, ConfigManager.SOUNDS_6_STRING);
        soundMap.put(Sound.SEVEN, ConfigManager.SOUNDS_7_STRING);

        List<String> sound_list = soundMap.getOrDefault(sound, null);
        if (sound_list != null) {
            play(player, sound_list);
            play(player2, sound_list);
        }
    }

    /**
     * Plays the random kill sound for the given players.
     *
     * @param player  The killer player.
     * @param sound_list List of sound to randomly play.
     */
    @SuppressWarnings("deprecation")
    private static void play(Player player, List<String> sound_list) {
        player.playSound(player.getLocation(), RandomList.random(sound_list), 100F, 1F);
    }

    /**
     * Retrieves the item associated with the specified sound.
     *
     * @param sound The sound for which to retrieve the item.
     * @return The corresponding ItemStack representing the sound item.
     */
    public static ItemStack getSoundItem(Sound sound) {
        if (sound == Sound.NONE) new ItemStack(Material.STONE);
        int ordinal = sound.ordinal() - 1;
        if (ordinal < ConfigManager.SOUNDS_ITEM.size()) {
            return ConfigManager.SOUNDS_ITEM.get(ordinal);
        }
        return new ItemStack(Material.STONE);
    }

    /**
     * Retrieves a list of all sound items.
     *
     * @return A list of ItemStacks representing sound items.
     */
    public static List<ItemStack> getSoundItems() {
        return ConfigManager.SOUNDS_ITEM;
    }

}
