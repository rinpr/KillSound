package net.java_rin.KillSound.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KillSoundTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> argument = new ArrayList<>();
        if (args.length == 1) {
            argument.add("reload");
            if (sender instanceof Player) argument.add("debug");
            Collections.sort(argument);
        }
        return argument;
    }
}
