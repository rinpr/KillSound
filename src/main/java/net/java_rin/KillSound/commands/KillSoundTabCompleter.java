package net.java_rin.KillSound.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class KillSoundTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> argument = new ArrayList<>();
        if (args.length == 1) {
            argument.add("reload");
            argument.add("give");
            argument.add("open");
            Collections.sort(argument);
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("give")) {
                argument.addAll(Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));
                Collections.sort(argument);
            }
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("give")) {
                argument.addAll(Arrays.asList("disc1", "disc2", "disc3", "disc4", "disc5", "disc6", "disc7"));
            }
        }
        return argument;
    }
}
