package net.java_rin.KillSound.utilities;

import net.java_rin.KillSound.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Message {

    public static void send(CommandSender sender, String message) {
        send(sender, message, "&f");
    }

    public static void send(CommandSender sender, String message, String prefix) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }
    public static void log(String message) {
        Bukkit.getLogger().info("[KillSound] " + message);
    }
    public static void broadcast(String message) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
