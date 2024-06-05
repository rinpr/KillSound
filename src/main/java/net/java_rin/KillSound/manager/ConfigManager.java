package net.java_rin.KillSound.manager;

import net.java_rin.KillSound.KillSound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    private static ItemStack createItemStack(String basePath) {
        String materialName = config.getString(basePath + ".material");
        Material material = Material.matchMaterial(materialName);
        ItemStack item = (material != null) ? new ItemStack(material) : new ItemStack(Material.STONE);

        String displayName = config.getString(basePath + ".display_name", "Default Display Name");
        List<String> lore = config.getStringList(basePath + ".lore");

        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));

            List<String> coloredLore = new ArrayList<>();
            for (String line : lore) {
                coloredLore.add(ChatColor.translateAlternateColorCodes('&', line));
            }
            meta.setLore(coloredLore);

            item.setItemMeta(meta);
        }

        return item;
    }
}
