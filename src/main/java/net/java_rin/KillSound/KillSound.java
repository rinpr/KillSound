package net.java_rin.KillSound;

import net.java_rin.KillSound.commands.KillSoundCommand;
import net.java_rin.KillSound.commands.KillSoundTabCompleter;
import net.java_rin.KillSound.listeners.InventoryListeners;
import net.java_rin.KillSound.listeners.PlayerListeners;
import net.java_rin.KillSound.manager.ConfigManager;
import net.java_rin.KillSound.manager.PlayerDataHolder;
import net.java_rin.KillSound.utilities.Message;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class KillSound extends JavaPlugin {
    private static KillSound instance;

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
        ConfigManager.loadConfig();
        this.generatePlayerDataFolder();
        this.registerEvents();
        this.registerCommands();
        PlayerDataHolder.load();
        PlayerDataHolder.autoSave(ConfigManager.AUTOSAVE_INTERVAL);
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

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[KillSound]" + ChatColor.WHITE + ": Disabled!");
        PlayerDataHolder.shutdown();
    }

    public static KillSound getInstance() {
        return instance;
    }
}
