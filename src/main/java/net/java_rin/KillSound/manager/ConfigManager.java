package net.java_rin.KillSound.manager;

import net.java_rin.KillSound.KillSound;
import net.java_rin.KillSound.utilities.Message;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigManager {

    private static final File configPath = new File(KillSound.getInstance().getDataFolder() + File.separator + "config.yml");
    private static FileConfiguration config;

    public static String PREFIX;
    public static int AUTOSAVE_INTERVAL;
    public static String GUI_NAME;
    public static List<ItemStack> SOUNDS_ITEM;
    public static List<String> SOUNDS_1_STRING;
    public static List<String> SOUNDS_2_STRING;
    public static List<String> SOUNDS_3_STRING;
    public static List<String> SOUNDS_4_STRING;
    public static List<String> SOUNDS_5_STRING;
    public static List<String> SOUNDS_6_STRING;
    public static List<String> SOUNDS_7_STRING;

    public static void loadConfig() {
        // If the config.yml is not exists will generate a new one with default value
        if (!configPath.exists()) {
            KillSound.getInstance().saveResource("config.yml", false);
        }
        // load config.yml and save to memory
        config = YamlConfiguration.loadConfiguration(configPath);
        // Setting new value to variables
        PREFIX = config.getString("prefix");
        AUTOSAVE_INTERVAL = config.getInt("autosave");
        GUI_NAME = config.getString("sound.gui_name");
        SOUNDS_ITEM = setItem();
        SOUNDS_1_STRING = config.getStringList("sound.sound_1.sounds");
        SOUNDS_2_STRING = config.getStringList("sound.sound_2.sounds");
        SOUNDS_3_STRING = config.getStringList("sound.sound_3.sounds");
        SOUNDS_4_STRING = config.getStringList("sound.sound_4.sounds");
        SOUNDS_5_STRING = config.getStringList("sound.sound_5.sounds");
        SOUNDS_6_STRING = config.getStringList("sound.sound_6.sounds");
        SOUNDS_7_STRING = config.getStringList("sound.sound_7.sounds");
    }

    public static void reload() {
        loadConfig();
    }

    private static List<ItemStack> setItem() {
        List<ItemStack> result = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            String basePath = "sound.sound_" + i;
            ItemStack item = createItemStack(basePath);
            result.add(item);
        }
        return result;
    }

    /**
     * Creates an ItemStack based on the specified configuration path.
     * <p>
     * Note: This method is designed for use with Bukkit API version 1.7.10.
     *
     * @param basePath The base path in the configuration.
     * @return An ItemStack representing the configured material with display name and lore.
     */
    private static ItemStack createItemStack(String basePath) {
        String itemString = config.getString(basePath + ".material");
        ItemStack item;
        if (itemString.contains(":")) {
            String[] parts = itemString.split(":");
            item = new ItemStack(Material.matchMaterial(parts[0]), 1, (short) Integer.parseInt(parts[1]));
        } else {
            item = (Material.getMaterial(itemString) != null) ? new ItemStack(Material.getMaterial(itemString)) : new ItemStack(Material.STONE);
        }

        String displayName = config.getString(basePath + ".display_name", "cant.read.display_name.value");
        List<String> lore = config.getStringList(basePath + ".lore");

        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));

            List<String> coloredLore = lore.stream()
                    .map(line -> ChatColor.translateAlternateColorCodes('&', line))
                    .collect(Collectors.toList());

            meta.setLore(coloredLore);
            item.setItemMeta(meta);
        }

        return item;
    }
}
