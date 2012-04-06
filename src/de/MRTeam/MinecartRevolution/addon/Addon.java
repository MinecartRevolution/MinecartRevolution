
package de.MRTeam.MinecartRevolution.addon;

import org.bukkit.plugin.java.JavaPlugin;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class Addon {

    public Addon(MinecartRevolution plugin) {

        Addon.plugin = plugin;

        register();
    }

    public static void registerAddon(JavaPlugin plugin) {

        MinecartRevolution.addons.put(plugin.getName(), plugin);
        MinecartRevolution.addonsIndexList.add(plugin);

        MinecartRevolution.messagesUtil.sendLogMessage(null, MinecartRevolution.messagesUtil.getMessage("addon", "enable").replaceAll("%addon%", plugin.getDescription().getName()).replaceAll("%plugin%", MinecartRevolution.descFile.getName()), false, "info");
    }

    private void register() {

        addonMinecart = new AddonMinecart();
    }

    public static MinecartRevolution plugin;

    public static AddonMinecart      addonMinecart;

}
