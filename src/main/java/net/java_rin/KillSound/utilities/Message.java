package net.java_rin.KillSound.utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Message {
    public static void send(CommandSender sender, String message) {
        send(sender, message, "&a");
    }
    public static void send(CommandSender sender, String message, String prefix) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }
    public static void send(String message) {
        Bukkit.getLogger().info(message);
    }
    public static void broadcast(String message) {
        Bukkit.broadcastMessage(message);
    }
}
