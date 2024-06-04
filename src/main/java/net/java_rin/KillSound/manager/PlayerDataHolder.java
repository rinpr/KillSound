package net.java_rin.KillSound.manager;

import net.java_rin.KillSound.KillSound;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;

public class PlayerDataHolder {
    private static HashMap<String, PlayerData> playerData = new HashMap<>();
    private final static File dataFolder = new File(KillSound.getInstance().getDataFolder() + File.separator + "player-data");

    public static void save() {
        // todo: save PlayerData object into player-data folder in yaml format.
    }

    public static void load() {
        // todo: load PlayerData object from player-data folder with yaml format.
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
}
