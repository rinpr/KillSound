package net.java_rin.KillSound.manager;

import net.java_rin.KillSound.sounds.Sound;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class PlayerData {
    private OfflinePlayer player;
    private String name;
    private UUID uuid;
    private boolean isEnabled;
    private Sound sound;

    public PlayerData(OfflinePlayer player) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.isEnabled = false;
        this.sound = Sound.NONE;
    }

    public PlayerData(OfflinePlayer player, Sound sound) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.isEnabled = false;
        this.sound = sound;
    }

    public PlayerData(OfflinePlayer player, Sound sound, boolean enabled) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.isEnabled = enabled;
        this.sound = sound;
    }

    public void setSound(Sound sound) { this.sound = sound; }

    public Sound getSound() { return this.sound; }

    public OfflinePlayer getPlayer() { return this.player; }

    public String getName() { return this.name; }

    public UUID getUUID() { return this.uuid; }

    public boolean isEnabled() { return this.isEnabled; }

    public void setEnabled(boolean isEnabled) { this.isEnabled = isEnabled; }

}
