package net.java_rin.KillSound.commands;

import net.java_rin.KillSound.manager.ConfigManager;
import net.java_rin.KillSound.manager.PlayerDataHolder;
import net.java_rin.KillSound.manager.SoundGUI;
import net.java_rin.KillSound.utilities.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.log("This command can only be executed by a player!");
            return false;
        }
        if (args.length > 0) {
            Message.send(sender, ConfigManager.PREFIX + "&cThis command has no arguments!");
            return false;
        }
        Player player = (Player) sender;
        SoundGUI gui = new SoundGUI(PlayerDataHolder.getPlayerData(player));
        gui.open();
        return false;
    }
}
