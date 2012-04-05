
package de.MRTeam.MinecartRevolution.util;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.entity.Player;

public class PermissionUtil {

    MinecartRevolution plugin;

    public PermissionUtil(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    public boolean hasPermission(Player player, String type, String arguments) {

        if (player == null) {
            return true;
        }

        String permissions = "minecartrevolution." + type;
        if (arguments != "") {
            permissions = permissions + "." + arguments;
        }
        return player.hasPermission(permissions);
    }

}
