package net.java_rin.KillSound.commands;

import net.java_rin.KillSound.manager.ConfigManager;
import net.java_rin.KillSound.manager.PlayerDataHolder;
import net.java_rin.KillSound.manager.SoundGUI;
import net.java_rin.KillSound.sounds.Sound;
import net.java_rin.KillSound.sounds.SoundData;
import net.java_rin.KillSound.utilities.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillSoundCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {
                SoundGUI gui = new SoundGUI(PlayerDataHolder.getPlayerData(player));
                gui.open();
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                ConfigManager.reload();
                PlayerDataHolder.shutdown();
                PlayerDataHolder.autoSave(ConfigManager.AUTOSAVE_INTERVAL);
                Message.send( player, ConfigManager.PREFIX + "&fReloaded!");
            } else if (args[0].equalsIgnoreCase("debug")) {
                player.getInventory().addItem(SoundData.getSoundItem(Sound.THREE));
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
