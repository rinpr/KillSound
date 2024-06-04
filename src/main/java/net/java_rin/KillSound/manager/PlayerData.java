package net.java_rin.KillSound.manager;

import net.java_rin.KillSound.sounds.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {
    private Player player;
    private String name;
    private UUID uuid;
    private Sound enabledSound;

    public PlayerData(Player player) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.enabledSound = Sound.NONE;
    }

    public PlayerData(Player player, Sound sound) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.enabledSound = sound;
    }

    public void setEnabledSound(Sound sound) { this.enabledSound = sound; }

    public Sound getEnabledSound() { return this.enabledSound; }

    public Player getPlayer() { return this.player; }

    public String getName() { return this.name; }
}
