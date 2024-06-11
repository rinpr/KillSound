package net.java_rin.KillSound.sounds;

import net.java_rin.KillSound.manager.ConfigManager;
import net.java_rin.KillSound.manager.PlayerData;
import net.java_rin.KillSound.manager.PlayerDataHolder;
import net.java_rin.KillSound.utilities.Message;
import net.java_rin.KillSound.utilities.RandomList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.List;

/**
 * Utility class for managing sound-related data and operations.
 */
public class DiscData {

    /**
     * Plays the kill sound for the given players based on their settings.
     *
     * @param player  The player who kill.
     * @param player2 The victim player.
     */
    public static void playKillSound(Player player, Player player2) {
        if (!player.isOnline() && !player2.isOnline()) return;
        PlayerData playerData = PlayerDataHolder.getPlayerData(player);
        if (!playerData.isEnabled()) return;
        Disc disc = playerData.getSound();
        if (disc == Disc.NONE) return;

        EnumMap<Disc, List<String>> soundMap = new EnumMap<>(Disc.class);
        soundMap.put(Disc.ONE, ConfigManager.SOUNDS_1_STRING);
        soundMap.put(Disc.TWO, ConfigManager.SOUNDS_2_STRING);
        soundMap.put(Disc.THREE, ConfigManager.SOUNDS_3_STRING);
        soundMap.put(Disc.FOUR, ConfigManager.SOUNDS_4_STRING);
        soundMap.put(Disc.FIVE, ConfigManager.SOUNDS_5_STRING);
        soundMap.put(Disc.SIX, ConfigManager.SOUNDS_6_STRING);
        soundMap.put(Disc.SEVEN, ConfigManager.SOUNDS_7_STRING);

        List<String> sound_list = soundMap.getOrDefault(disc, null);
        if (sound_list != null) {
            String sound = RandomList.random(sound_list);
            play(player, sound);
            play(player2, sound);
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
     * Plays the random kill sound for the given players.
     *
     * @param player  The killer player.
     * @param sound Sound to play.
     */
    @SuppressWarnings("deprecation")
    private static void play(Player player, String sound) {
        player.playSound(player.getLocation(), sound, 100F, 1F);
    }

    /**
     * Retrieves the item associated with the specified sound.
     *
     * @param disc The sound for which to retrieve the item.
     * @return The corresponding ItemStack representing the sound item.
     */
    @SuppressWarnings("unused")
    public static ItemStack getDisc(Disc disc) {
        if (disc == Disc.NONE) new ItemStack(Material.STONE);
        int ordinal = disc.ordinal() - 1;
        if (ordinal < ConfigManager.SOUNDS_ITEM.size()) {
            return ConfigManager.SOUNDS_ITEM.get(ordinal);
        }
        return new ItemStack(Material.STONE);
    }

    /**
     * Retrieves the corresponding Disc enum based on the provided ItemStack.
     *
     * @param item The ItemStack to check.
     * @return The corresponding Disc enum, or Disc.NONE if no match is found.
     */
    public static Disc getDisc(ItemStack item) {
        for (int i = 0; i < ConfigManager.SOUNDS_ITEM.size(); i++) {
            if (item.equals(ConfigManager.SOUNDS_ITEM.get(i))) {
                return Disc.values()[i];
            }
        }
        return Disc.NONE;
    }


    /**
     * Gets the permission associated with a sound disc item.
     *
     * @param item The ItemStack representing the sound disc.
     * @return The permission string or null if the item is invalid.
     */
    public static String getPermissionFromDisc(ItemStack item) {
        if (item == null) {
            return null;
        }
        int slot = ConfigManager.SOUNDS_ITEM.indexOf(item);
        if (slot >= 0) {
            return "killsound.custom." + (slot + 1);
        }
        return null;
    }


    /**
     * Retrieves a list of all sound items.
     *
     * @return A list of ItemStacks representing sound items.
     */
    public static List<ItemStack> getDiscItems() {
        return ConfigManager.SOUNDS_ITEM;
    }

    /**
     * Gets the availability slot for a given sound.
     *
     * @param disc The sound to check.
     * @return The availability slot (20 to 26) or -1 if the sound is NONE or error.
     */
    public static int getAvailabilitySlot(Disc disc) {
        if (disc == Disc.NONE) return -1;
        if (disc == Disc.ONE) return 20;
        if (disc == Disc.TWO) return 21;
        if (disc == Disc.THREE) return 22;
        if (disc == Disc.FOUR) return 23;
        if (disc == Disc.FIVE) return 24;
        if (disc == Disc.SIX) return 25;
        if (disc == Disc.SEVEN) return 26;
        return -1;
    }

    /**
     * Gets the sound corresponding to the availability slot.
     *
     * @param slot The availability slot (20 to 26).
     * @return The corresponding sound or Sound.NONE if the slot is invalid.
     */
    public static Disc getDiscFromAvailabilitySlot(int slot) {
        switch (slot) {
            case 20:
                return Disc.ONE;
            case 21:
                return Disc.TWO;
            case 22:
                return Disc.THREE;
            case 23:
                return Disc.FOUR;
            case 24:
                return Disc.FIVE;
            case 25:
                return Disc.SIX;
            case 26:
                return Disc.SEVEN;
            default:
                return Disc.NONE;
        }
    }

    /**
     * Gives the specified sound disc to the player.
     *
     * @param player The player to give the disc to.
     * @param disc   The name of the sound disc ("disc1" to "disc7").
     * @param amount The amount of the disc to give.
     */
    public static void giveDisc(Player player, String disc, int amount) {
        int discIndex = -1;
        switch (disc.toLowerCase()) {
            case "disc1": discIndex = 0; break;
            case "disc2": discIndex = 1; break;
            case "disc3": discIndex = 2; break;
            case "disc4": discIndex = 3; break;
            case "disc5": discIndex = 4; break;
            case "disc6": discIndex = 5; break;
            case "disc7": discIndex = 6; break;
        }

        if (discIndex >= 0) {
            ItemStack discItem = ConfigManager.SOUNDS_ITEM.get(discIndex);
            discItem.setAmount(amount);
            player.getInventory().addItem(discItem);
            Message.send(player, ConfigManager.PREFIX + "&fSomeone has been given you a disc.");
        }
    }
}
