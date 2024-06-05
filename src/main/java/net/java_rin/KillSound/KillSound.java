package net.java_rin.KillSound;

import net.java_rin.KillSound.commands.KillSoundCommand;
import net.java_rin.KillSound.commands.KillSoundTabCompleter;
import net.java_rin.KillSound.listeners.InventoryListeners;
import net.java_rin.KillSound.listeners.PlayerListeners;
import net.java_rin.KillSound.manager.PlayerDataHolder;
import net.java_rin.KillSound.utilities.Message;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class KillSound extends JavaPlugin {
    public static KillSound instance;
    public File configPath = new File(this.getDataFolder() + File.separator + "config.yml");
    public FileConfiguration config;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getConsoleSender().sendMessage("┏┓┏━┓━━┏┓━┏┓━┏━━━┓━━━━━━━━━━━━━━┏┓");
        getServer().getConsoleSender().sendMessage("┃┃┃┏┛━━┃┃━┃┃━┃┏━┓┃━━━━━━━━━━━━━━┃┃");
        getServer().getConsoleSender().sendMessage("┃┗┛┛━┏┓┃┃━┃┃━┃┗━━┓┏━━┓┏┓┏┓┏━┓━┏━┛┃");
        getServer().getConsoleSender().sendMessage("┃┏┓┃━┣┫┃┃━┃┃━┗━━┓┃┃┏┓┃┃┃┃┃┃┏┓┓┃┏┓┃    KillSound v1.0.0");
        getServer().getConsoleSender().sendMessage("┃┃┃┗┓┃┃┃┗┓┃┗┓┃┗━┛┃┃┗┛┃┃┗┛┃┃┃┃┃┃┗┛┃    by Java_Rin");
        getServer().getConsoleSender().sendMessage("┗┛┗━┛┗┛┗━┛┗━┛┗━━━┛┗━━┛┗━━┛┗┛┗┛┗━━┛");
        getServer().getConsoleSender().sendMessage("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        getServer().getConsoleSender().sendMessage("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        this.saveDefaultConfig();
        this.loadDefaultConfig();
        this.generatePlayerDataFolder();
        this.registerEvents();
        this.registerCommands();
        PlayerDataHolder.load();
        PlayerDataHolder.autoSave(1);
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new InventoryListeners(), instance);
        getServer().getPluginManager().registerEvents(new PlayerListeners(), instance);
    }

    private void registerCommands() {
        instance.getCommand("killsound").setExecutor(new KillSoundCommand());
        instance.getCommand("killsound").setTabCompleter(new KillSoundTabCompleter());
    }

    private void generatePlayerDataFolder() {
        File file = new File(this.getDataFolder() + File.separator + "player-data");
        if (!file.exists()) {
            boolean dirCreated = file.mkdirs();
            if (dirCreated) {
                Message.log("Successfully generated plugins folder!");
            } else {
                Message.log("An error has occurred while generating plugins folder!");
            }
        }
    }

    private void loadDefaultConfig() {
        if (!this.configPath.exists()) {
            instance.saveResource("config.yml", false);
        }
        this.config = YamlConfiguration.loadConfiguration(this.configPath);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[KillSound]" + ChatColor.WHITE + ": Disabled!");
        PlayerDataHolder.shutdown();
    }

    public static KillSound getInstance() {
        return instance;
    }
}
