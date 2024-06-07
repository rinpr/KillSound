package net.java_rin.KillSound.commands;

import net.java_rin.KillSound.manager.ConfigManager;
import net.java_rin.KillSound.manager.PlayerDataHolder;
import net.java_rin.KillSound.manager.SoundGUI;
import net.java_rin.KillSound.sounds.DiscData;
import net.java_rin.KillSound.utilities.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class KillSoundCommand implements CommandExecutor {
    @Override
    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {
                Message.send(player, "&7------------------------");
                Message.send(player, "         &7KillSound Plugin");
                Message.send(player, "            &fby &aJava_Rin");
                Message.send(player, "&7------------------------");
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                ConfigManager.reload();
                PlayerDataHolder.shutdown();
                PlayerDataHolder.autoSave(ConfigManager.AUTOSAVE_INTERVAL);
                Message.send( player, ConfigManager.PREFIX + "&fReloaded!");
                return true;
            }
            if (args[0].equalsIgnoreCase("open")) {
                SoundGUI gui = new SoundGUI(PlayerDataHolder.getPlayerData(player));
                gui.open();
                return true;
            }
            if (args[0].equalsIgnoreCase("give")) {
                if (args.length < 2) {
                    Message.send(player, ConfigManager.PREFIX + "&cPlease enter the player name.");
                    return false;
                }
                if (args.length < 3) {
                    Message.send(player, ConfigManager.PREFIX + "&cPlease specify disc name.");
                    return false;
                }
                if (args.length < 4) {
                    Message.send(player, ConfigManager.PREFIX + "&cPlease specify amount of disc.");
                    return false;
                }
                if (!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[1]))) {
                    Message.send( player, ConfigManager.PREFIX + "&cThat player isn't online!");
                    return false;
                }
                if (!Arrays.asList("disc1", "disc2", "disc3", "disc4", "disc5", "disc6", "disc7").contains(args[2])) {
                    Message.send(player, ConfigManager.PREFIX + "&cInvalid disc name!");
                    return false;
                }
                DiscData.giveDisc(Bukkit.getPlayer(args[1]), args[2], Integer.parseInt(args[3]));
                return true;
            } else {
                Message.send( player, ConfigManager.PREFIX + "&cNo such commands!");
            }
        } else {
            if (args.length < 1) {
                Message.log( "Not enough arguments!");
                return true;
            } else {
                if (args[0].equalsIgnoreCase("reload")) {
                    ConfigManager.reload();
                    PlayerDataHolder.shutdown();
                    PlayerDataHolder.autoSave(ConfigManager.AUTOSAVE_INTERVAL);
                    Message.log( "Reloaded!");
                } else {
                    Message.log("&cNo such commands!");
                }
            }
        }
        return false;
    }
}
