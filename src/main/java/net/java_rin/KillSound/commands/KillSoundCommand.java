package net.java_rin.KillSound.commands;

import net.java_rin.KillSound.manager.ConfigManager;
import net.java_rin.KillSound.manager.SoundGUI;
import net.java_rin.KillSound.sounds.Sound1;
import net.java_rin.KillSound.utilities.Message;
import org.bukkit.Location;
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
                SoundGUI.open(player);
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                ConfigManager.reload();
                Message.send( player, ConfigManager.PREFIX + "&fReloaded!");
            } else if (args[0].equalsIgnoreCase("debug")) {
                Sound1.playSound(player);
                Sound1.giveSoundItem(player);
//                Message.send( player, ConfigManager.PREFIX + "&fDebugging!");
//                for (ItemStack item : ConfigManager.SOUNDS_ITEM) {
//                    Message.send( player, "Name" + ": " + item.getItemMeta().getDisplayName());
//                    Message.send( player, "Material" + ": " + item.getType());
//                    Message.send( player, "Lore" + ":  " + item.getItemMeta().getLore());
//                }
//                if (ConfigManager.SOUNDS_ITEM == null || ConfigManager.SOUNDS_ITEM.isEmpty()) {
//                    Message.send( player, ConfigManager.PREFIX + "&fNo Items Available!");
//                    return false;
//                }
//
//                Location location = player.getLocation();
//                for (ItemStack item : ConfigManager.SOUNDS_ITEM) {
//                    player.getWorld().dropItemNaturally(location, item);
//                }
//                Message.send( player, ConfigManager.PREFIX + "&aSuccessfully dropped item!");
            } else {
                Message.send( player, ConfigManager.PREFIX + "&cNo such commands!");
            }
        } else {
            if (args.length < 1) {
                Message.send( "Not enough arguments!");
                return true;
            } else {
                if (args[0].equalsIgnoreCase("reload")) {
                    ConfigManager.reload();
                    Message.send( ConfigManager.PREFIX + "Reloaded!");
                } else {
                    Message.send(ConfigManager.PREFIX + "&cNo such commands!");
                }
            }
        }
        return false;
    }
}
