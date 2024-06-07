package net.java_rin.KillSound.manager;

import net.java_rin.KillSound.sounds.Disc;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

/**
 * Represents player-specific data related to sound settings.
 */
public class PlayerData {
    private OfflinePlayer player;
    private String name;
    private UUID uuid;
    private boolean isEnabled;
    private Disc disc;

    /**
     * Constructs a new PlayerData instance for the given player.
     *
     * @param player The OfflinePlayer associated with this data.
     */
    public PlayerData(OfflinePlayer player) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.isEnabled = false;
        this.disc = Disc.NONE;
    }

    /**
     * Constructs a new PlayerData instance for the given player with a specified sound.
     *
     * @param player The OfflinePlayer associated with this data.
     * @param disc  The initial sound setting for the player.
     */
    public PlayerData(OfflinePlayer player, Disc disc) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.isEnabled = false;
        this.disc = disc;
    }

    /**
     * Constructs a new PlayerData instance for the given player with specified sound and enabled status.
     *
     * @param player   The OfflinePlayer associated with this data.
     * @param disc    The initial sound setting for the player.
     * @param enabled  Whether sound is enabled for the player.
     */
    public PlayerData(OfflinePlayer player, Disc disc, boolean enabled) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.isEnabled = enabled;
        this.disc = disc;
    }

    /**
     * Sets the sound setting for the player.
     *
     * @param disc The new sound setting.
     */
    public void setSound(Disc disc) { this.disc = disc; }

    /**
     * Retrieves the current sound setting for the player.
     *
     * @return The player's sound setting.
     */
    public Disc getSound() { return this.disc; }

    /**
     * Retrieves the associated OfflinePlayer.
     *
     * @return The OfflinePlayer.
     */
    public OfflinePlayer getPlayer() { return this.player; }

    /**
     * Retrieves the player's name.
     *
     * @return The player's name.
     */
    public String getName() { return this.name; }

    /**
     * Retrieves the player's UUID.
     *
     * @return The player's UUID.
     */
    public UUID getUUID() { return this.uuid; }

    /**
     * Checks if sound is enabled for the player.
     *
     * @return {@code true} if sound is enabled, otherwise {@code false}.
     */
    public boolean isEnabled() { return this.isEnabled; }

    /**
     * Sets whether sound is enabled for the player.
     *
     * @param isEnabled {@code true} to enable sound, {@code false} to disable.
     */
    public boolean setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return isEnabled;
    }

}
