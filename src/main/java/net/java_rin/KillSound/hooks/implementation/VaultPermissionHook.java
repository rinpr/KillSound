package net.java_rin.KillSound.hooks.implementation;

import net.java_rin.KillSound.KillSound;
import net.java_rin.KillSound.hooks.HooksId;
import net.java_rin.KillSound.utilities.Message;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;

public class VaultPermissionHook {

    /**
     * Used to store Vault Permission Instance
     */
    private static Permission permission;

    /**
     * This function use to initialize Vault Permission module.
     *
     * @since 1.0
     */
    public static void init() {
        permission = Bukkit.getServicesManager().getRegistration(Permission.class) == null ? null : Bukkit.getServicesManager().getRegistration(Permission.class).getProvider();
        if (KillSound.getInstance().getServer().getPluginManager().getPlugin(HooksId.VAULT) == null) {
            Message.log("&cVault dependency not found. Some module might not work as expected!");
        } else {
            Message.log("&aVault dependency found.");
        }
    }

    /**
     * This function use to get Permission instance if Vault dependency is found.
     *
     * @return Vault's Permission Instance
     * @since  1.0
     */
    public static Permission getInstance() {
        return permission;
    }

}
