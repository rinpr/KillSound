package net.java_rin.KillSound.manager;

import net.java_rin.KillSound.KillSound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    public static String PREFIX;
    public static String GUI_NAME;
    public static List<ItemStack> SOUNDS_ITEM;
    public static List<String> SOUNDS_1_STRING;
    public static List<String> SOUNDS_2_STRING;
    public static List<String> SOUNDS_3_STRING;
    public static List<String> SOUNDS_4_STRING;
    public static List<String> SOUNDS_5_STRING;
    public static List<String> SOUNDS_6_STRING;
    public static List<String> SOUNDS_7_STRING;

    static {
        KillSound.instance = KillSound.getInstance();
        ConfigManager.PREFIX = KillSound.instance.getConfig().getString("prefix");
        ConfigManager.GUI_NAME = KillSound.instance.getConfig().getString("sound.gui_name");
        SOUNDS_ITEM = setItem();
        ConfigManager.SOUNDS_1_STRING = KillSound.instance.getConfig().getStringList("sound.sound_1.sounds");
        ConfigManager.SOUNDS_2_STRING = KillSound.instance.getConfig().getStringList("sound.sound_2.sounds");
        ConfigManager.SOUNDS_3_STRING = KillSound.instance.getConfig().getStringList("sound.sound_3.sounds");
        ConfigManager.SOUNDS_4_STRING = KillSound.instance.getConfig().getStringList("sound.sound_4.sounds");
        ConfigManager.SOUNDS_5_STRING = KillSound.instance.getConfig().getStringList("sound.sound_5.sounds");
        ConfigManager.SOUNDS_6_STRING = KillSound.instance.getConfig().getStringList("sound.sound_6.sounds");
        ConfigManager.SOUNDS_7_STRING = KillSound.instance.getConfig().getStringList("sound.sound_7.sounds");
    }

    private static void loadFiles() {
        if (!KillSound.instance.configPath.exists()) {
            KillSound.instance.saveResource("config.yml", false);
        }
        KillSound.instance.configPath = new File(KillSound.instance.getDataFolder() + File.separator + "config.yml");
        KillSound.instance.config = YamlConfiguration.loadConfiguration(KillSound.instance.configPath);
    }

    public static void reload() {
        loadFiles();
        ConfigManager.PREFIX = KillSound.instance.config.getString("prefix");
        ConfigManager.GUI_NAME = KillSound.instance.config.getString("sound.gui_name");
        SOUNDS_ITEM = setItem();
        ConfigManager.SOUNDS_1_STRING = KillSound.instance.getConfig().getStringList("sound.sound_1.sounds");
        ConfigManager.SOUNDS_2_STRING = KillSound.instance.getConfig().getStringList("sound.sound_2.sounds");
        ConfigManager.SOUNDS_3_STRING = KillSound.instance.getConfig().getStringList("sound.sound_3.sounds");
        ConfigManager.SOUNDS_4_STRING = KillSound.instance.getConfig().getStringList("sound.sound_4.sounds");
        ConfigManager.SOUNDS_5_STRING = KillSound.instance.getConfig().getStringList("sound.sound_5.sounds");
        ConfigManager.SOUNDS_6_STRING = KillSound.instance.getConfig().getStringList("sound.sound_6.sounds");
        ConfigManager.SOUNDS_7_STRING = KillSound.instance.getConfig().getStringList("sound.sound_7.sounds");
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
        String materialName = KillSound.instance.config.getString(basePath + ".material");
        Material material = Material.matchMaterial(materialName);
        ItemStack item = (material != null) ? new ItemStack(material) : new ItemStack(Material.STONE);

        String displayName = KillSound.instance.config.getString(basePath + ".display_name", "Default Display Name");
        List<String> lore = KillSound.instance.config.getStringList(basePath + ".lore");

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
