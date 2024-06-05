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

public class PlayerDataHolder {
    private static HashMap<String, PlayerData> playerData = new HashMap<>();
    private final static File dataFolder = new File(KillSound.getInstance().getDataFolder() + File.separator + "player-data");
    private static int taskId;

    public static void autoSave(int minute) {
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask (KillSound.getInstance(), new Runnable() {
            @Override
            public void run() {
                save();
                load();
            }
        }, 60 * 20L, minute * 60 * 20L);
    }

    public static void shutdown() {
        save();
        Bukkit.getScheduler().cancelTask(taskId);
    }

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

    public static HashMap<String, PlayerData> getAllPlayerData() {
        return playerData;
    }

    public static void add(PlayerData data) {
        playerData.put(data.getName(), data);
    }

    public static void remove(PlayerData data) {
        playerData.remove(data.getName());
    }

    public static PlayerData getPlayerData(Player player) {
        if (!playerData.containsKey(player.getName())) {
            PlayerData data = new PlayerData(player);
            add(data);
            return data;
        } else {
            return playerData.get(player.getName());
        }
    }

    private static Sound getSound(String soundString) {
        try {
            return Sound.valueOf(soundString);
        } catch (IllegalArgumentException e) {
            // Handle invalid or missing enum values (e.g., log an error)
            return Sound.NONE; // Default value
        }
    }
}
