//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.listener;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

    public PlayerListener(MinecartRevolution plugin) {

        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {

        if (event.getEntity() instanceof Player && event.getEntity().getVehicle() != null && event.getEntity().getVehicle() instanceof Minecart) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (MinecartRevolution.permissionUtil.hasPermission(player, "update", "update") && MinecartRevolution.configUtil.checkVersionOnJoin.equalsIgnoreCase("true")) {
            String updateMessage = MinecartRevolution.updateUtil.checkVersion(false, false);
            if (updateMessage != null) {
                MinecartRevolution.messagesUtil.sendMessage(player, updateMessage, false);
            }

            for (int counter = 0; counter < MinecartRevolution.addons.size(); counter++) {
                try {
                    String addonMessage = MinecartRevolution.updateUtil.checkAddonVersion(player, MinecartRevolution.addons.get(counter).getDescription().getName(), false);
                    if (addonMessage != null) {
                        MinecartRevolution.messagesUtil.sendMessage(player, addonMessage, false);
                    }
                }
                catch (NullPointerException ex) {
                }
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        if (player.isInsideVehicle()) {
            if (player.getVehicle() instanceof Minecart) {
                Minecart minecart = (Minecart) player.getVehicle();

                if (MinecartRevolution.signAction.signLock.lockList.contains(minecart.getEntityId())) {
                    if (MinecartRevolution.configUtil.killPlayerWhenLockedOnQuit.equalsIgnoreCase("true")) {
                        player.setHealth(0);
                        minecart.remove();
                    }
                    MinecartRevolution.signAction.signLock.lockList.remove(minecart.getEntityId());
                    return;
                } else if (MinecartRevolution.configUtil.putMinecartInInvOnQuit.equalsIgnoreCase("true")) {
                    player.getInventory().addItem(new ItemStack(328, 1));
                    minecart.remove();
                }
            }
        }
    }

    MinecartRevolution plugin;

}
