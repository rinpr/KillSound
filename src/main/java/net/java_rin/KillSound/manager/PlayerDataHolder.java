package net.java_rin.KillSound.manager;

import net.java_rin.KillSound.KillSound;
import net.java_rin.KillSound.sounds.Sound;
import net.java_rin.KillSound.utilities.Message;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * Manages player data for the KillSound plugin.
 */
public class PlayerDataHolder {
    private static HashMap<String, PlayerData> playerData = new HashMap<>();
    private final static File dataFolder = new File(KillSound.getInstance().getDataFolder() + File.separator + "player-data");
    private static int taskId;

    /**
     * Schedules auto-saving of player data at regular intervals.
     *
     * @param minute The interval in minutes.
     */
    public static void autoSave(int minute) {
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask (KillSound.getInstance(), new Runnable() {
            @Override
            public void run() {
                save();
                load();
            }
        }, 60 * 20L, minute * 60 * 20L);
    }

    /**
     * Cancels the auto-save task and manually triggers saving.
     */
    public static void shutdown() {
        save();
        Bukkit.getScheduler().cancelTask(taskId);
    }

    /**
     * Saves player data to YAML files.
     */
    public static void save() {
        Message.log("Saving player data...");
        for (PlayerData playerData : playerData.values()) {
            File file = new File(dataFolder, playerData.getUUID() + ".yml");
            FileConfiguration data_file = new YamlConfiguration();

            data_file.set("uuid", playerData.getUUID().toString());
            data_file.set("name", playerData.getName());
            data_file.set("enabled-sound", playerData.getEnabledSound().toString());

            try {
                data_file.save(file);
            } catch (IOException e) {
                Message.log("An error occurred while saving/creating player data " + playerData.getUUID() + ".yml");
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads player data from YAML files.
     */
    public static void load() {
        File[] files = dataFolder.listFiles();
        if (files == null) {
            Message.log("No player data found.");
            return;
        }
        for (File file : files) {
            FileConfiguration raw_data = YamlConfiguration.loadConfiguration(file);

            String uuid = file.getName().contains(".") ? file.getName().substring(0, file.getName().lastIndexOf(".")) : file.getName();
            OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(uuid));

            String soundString = raw_data.getString("enabled-sound");
            Sound sound = getSound(soundString);

            PlayerData data = new PlayerData(player, sound);
            add(data);
        }
        Message.log("Loaded player data successfully!");
    }

    /**
     * Retrieves all player data.
     *
     * @return A map of player names to their data.
     */
    public static HashMap<String, PlayerData> getAllPlayerData() {
        return playerData;
    }

    /**
     * Adds player data to the manager.
     *
     * @param data The player data to add.
     */
    public static void add(PlayerData data) {
        playerData.put(data.getName(), data);
    }

    /**
     * Removes player data from the manager.
     *
     * @param data The player data to remove.
     */
    public static void remove(PlayerData data) {
        playerData.remove(data.getName());
    }

    /**
     * Retrieves player data for a specific player.
     *
     * @param player The player to retrieve data for.
     * @return The player's data.
     */
    public static PlayerData getPlayerData(Player player) {
        if (!playerData.containsKey(player.getName())) {
            PlayerData data = new PlayerData(player);
            add(data);
            return data;
        } else {
            return playerData.get(player.getName());
        }
    }

    /**
     * Converts a string representation of a sound to the corresponding Sound enum.
     *
     * @param soundString The string representation of the sound.
     * @return The corresponding Sound enum value.
     */
    private static Sound getSound(String soundString) {
        try {
            return Sound.valueOf(soundString);
        } catch (IllegalArgumentException e) {
            return Sound.NONE;
        }
    }
}
