//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.listener;

import java.util.ArrayList;
import de.MRTeam.MinecartRevolution.MinecartRevolution;
import de.MRTeam.MinecartRevolution.addon.ControlBlock;
import de.MRTeam.MinecartRevolution.addon.ControlSign;
import de.MRTeam.MinecartRevolution.util.dataStructure.FileHashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class MinecartListener implements Listener {

    public MinecartListener(MinecartRevolution plugin) {

        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onVehicleUpdate(VehicleUpdateEvent event) {

        if (event.getVehicle() instanceof Minecart) {
            Minecart minecart = (Minecart) event.getVehicle();
            MinecartRevolution.flyCart.doFly(minecart);

            if (minecart.isDead()) {
                return;
            }
            double speedNumber;
            if (MinecartRevolution.configUtil.constantMinecartSpeed.equalsIgnoreCase("true") && MinecartRevolution.minecartListener.isMinecartOnRail(minecart) && (minecart.getVelocity().getX() != 0.0D || minecart.getVelocity().getZ() != 0.0D)) {
                Vector speed = new Vector();
                speedNumber = 0.3913788423600029D;

                if (minecart.getVelocity().getX() > 0.0D) {
                    speed.setX(speedNumber);
                } else if (minecart.getVelocity().getX() < 0.0D) {
                    speed.setX(-speedNumber);
                } else if (minecart.getVelocity().getZ() > 0.0D) {
                    speed.setZ(speedNumber);
                } else if (minecart.getVelocity().getZ() < 0.0D) {
                    speed.setZ(-speedNumber);
                }

                minecart.setVelocity(speed);
            }

            if (MinecartRevolution.configUtil.pushNearbyEntities.equalsIgnoreCase("true")) {
                for (Entity entity : minecart.getNearbyEntities(0.8D, 0.8D, 0.8D)) {
                    if (! (entity instanceof Player) && ! (entity instanceof Minecart)) {
                        Location entityLocation = entity.getLocation();
                        entityLocation.setX(entityLocation.getBlockX() - 0.2D);
                        entity.teleport(entityLocation);
                    }
                }
            }

            MinecartRevolution.blockAction.doBlockEvent(minecart.getLocation().getBlock(), minecart);
            MinecartRevolution.signAction.doSignEvent(minecart.getLocation().getBlock(), minecart);

            try {
                if (MinecartRevolution.blockUtil.getControlBlock(minecart) != null && MinecartRevolution.blockUtil.getControlBlock(minecart).isBlockIndirectlyPowered()) {
                    if (MinecartRevolution.blockUtil.getControlBlock(minecart) != null && MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == MinecartRevolution.configUtil.stationBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == MinecartRevolution.configUtil.stationBlockId[1] || MinecartRevolution.configUtil.stationBlockId[1] == -1)) {
                        MinecartRevolution.blockAction.blockStation.execute(minecart);
                    }

                    for (int counter = 0; counter < MinecartRevolution.blockAction.blockList.size(); counter++) {
                        ControlBlock controlBlock = MinecartRevolution.blockAction.blockList.get(counter);
                        int[] controlBlockId = MinecartRevolution.blockUtil.splitBlockData((String) MinecartRevolution.configUtil.getAddonSetting(controlBlock.plugin, "blockId." + controlBlock.getBlockName()));
                        if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == controlBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == controlBlockId[1] || controlBlockId[1] == -1)) {
                            controlBlock.onRedstonePower(minecart);
                            break;
                        }
                    }
                } else if (MinecartRevolution.blockUtil.getSignBlockSign(minecart) != null && MinecartRevolution.blockUtil.getSignBlockSign(minecart).getBlock().isBlockIndirectlyPowered()) {
                    for (int counter = 0; counter < MinecartRevolution.blockAction.blockList.size(); counter++) {
                        ControlSign controlSign = MinecartRevolution.signAction.signList.get(counter);
                        if ( (MinecartRevolution.blockUtil.getSignBlockSign(minecart).getLine(0).equalsIgnoreCase(controlSign.getLine(0)) || controlSign.getLine(0).equalsIgnoreCase("")) && (MinecartRevolution.blockUtil.getSignBlockSign(minecart).getLine(1).equalsIgnoreCase(controlSign.getLine(1)) || controlSign.getLine(1).equalsIgnoreCase("")) && (MinecartRevolution.blockUtil.getSignBlockSign(minecart).getLine(2).equalsIgnoreCase(controlSign.getLine(2)) || controlSign.getLine(2).equalsIgnoreCase("")) && (MinecartRevolution.blockUtil.getSignBlockSign(minecart).getLine(3).equalsIgnoreCase(controlSign.getLine(3)) || controlSign.getLine(3).equalsIgnoreCase(""))) {
                            controlSign.onRedstonePower(minecart);
                            break;
                        }
                    }
                }
            }
            catch (Exception ex) {
            }

            MinecartRevolution.pathFindUtil.findPathUpdate(minecart);

            if (MinecartRevolution.signAction.signFarm.farmTypeMap.containsKey(minecart)) {
                if (minecart instanceof StorageMinecart) {
                    try {
                        MinecartRevolution.signAction.signFarm.farm((StorageMinecart) minecart, MinecartRevolution.signAction.signFarm.farmTypeMap.get(minecart), Integer.parseInt(MinecartRevolution.signAction.signFarm.farmRadiusMap.get(minecart)));
                    }
                    catch (NumberFormatException ex) {
                    }
                }
            }

            if (MinecartRevolution.configUtil.playEffects.equalsIgnoreCase("true")) {
                if (MinecartRevolution.signAction.signEffect.effectMinecartMap.containsKey(minecart)) {
                    ArrayList<String> effectList = MinecartRevolution.signAction.signEffect.effectMinecartMap.get(minecart);
                    for (int counter = 0; counter < effectList.size(); counter++) {
                        MinecartRevolution.playEffectUtil.playEffect(minecart, effectList.get(counter));
                    }
                }
            }

            if (MinecartRevolution.configUtil.loadMinecartChunks.equalsIgnoreCase("true")) {
                if (!minecart.getWorld().getChunkAt(minecart.getLocation()).isLoaded()) {
                    minecart.getWorld().getChunkAt(minecart.getLocation()).load();
                }
            }
        }
    }

    @EventHandler
    public void onVehicleDamage(VehicleDamageEvent event) {

        if (event.getVehicle() == null) {
            return;
        }
        if (event.getAttacker() instanceof Player && event.getVehicle() instanceof Minecart) {
            Player player = (Player) event.getAttacker();
            Minecart minecart = (Minecart) event.getVehicle();
            if (MinecartRevolution.flyCart.flyerList.contains(player.getName())) {
                return;
            }

            Block controlBlock = MinecartRevolution.blockUtil.getControlBlock(minecart);
            if (MinecartRevolution.minecartListener.isMinecartOnRail(minecart)) {
                if (controlBlock.getTypeId() == MinecartRevolution.configUtil.intersectionBlockId[0] && (controlBlock.getData() == MinecartRevolution.configUtil.intersectionBlockId[1] || MinecartRevolution.configUtil.intersectionBlockId[1] == -1)) {
                    MinecartRevolution.blockAction.blockIntersection.playerPunch(minecart, player.getLocation().getDirection());
                    event.setCancelled(true);
                } else {
                    if (MinecartRevolution.configUtil.enablePunch.equalsIgnoreCase("true")) {
                        if (player.isInsideVehicle() && isMinecartOnRail(minecart) && !MinecartRevolution.signAction.signLock.lockList.contains(minecart.getEntityId()) && MinecartRevolution.configUtil.enablePunch.equalsIgnoreCase("true")) {
                            Vector velocity = player.getLocation().getDirection();
                            minecart.setVelocity(velocity);
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onVehicleDamageFlyerMode(VehicleDamageEvent event) {

        if (event.getAttacker() instanceof Player && event.getVehicle() instanceof Minecart) {
            Player player = (Player) event.getAttacker();
            Minecart minecart = (Minecart) event.getVehicle();
            if (MinecartRevolution.flyCart.flyerList.contains(player.getName()) && player.isInsideVehicle()) {
                Vector vector = player.getLocation().getDirection();
                vector.setY(minecart.getVelocity().getY());
                vector.multiply(120.0D);
                event.setCancelled(true);
                minecart.setVelocity(vector);
            }
        }
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {

        if (event.getEntered() instanceof Player && event.getVehicle() instanceof Minecart) {
            Player player = (Player) event.getEntered();
            if (!MinecartRevolution.signAction.signLock.lockList.contains(player.getName()) && MinecartRevolution.configUtil.enablePunch.equalsIgnoreCase("true")) {
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("punch", ""), true);
            }
            MinecartRevolution.blockAction.blockStation.checkPlayerEnter((Minecart) event.getVehicle());
        }
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {

        if (event.getVehicle() instanceof Minecart) {
            Entity entity = event.getExited();
            if (entity instanceof Player) {
                Player player = (Player) event.getExited();
                if (MinecartRevolution.flyCart.flyerList.contains(player.getName())) {
                    MinecartRevolution.flyCart.flyerList.remove(player.getName());
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("flyCartDisable", ""), true);
                }
                MinecartRevolution.signAction.signLock.onVehicleExit((Minecart) event.getVehicle(), player, event);
            }
        }
    }

    @EventHandler
    public void onVehicleDestroy(VehicleDestroyEvent event) {

        if (event.getVehicle() instanceof Minecart) {
            if (event.getAttacker() instanceof Player) {
                Player player = (Player) event.getAttacker();
                if (MinecartRevolution.signAction.signLock.lockList.contains( ((Minecart) event.getVehicle()).getEntityId())) {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("cantExitHere", ""), false);
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onVehicleBlockCollision(VehicleBlockCollisionEvent event) {

        Location hitBlock = event.getBlock().getLocation();
        if (hitBlock.getBlock().getType() == Material.CHEST) {
            BlockState state = hitBlock.getBlock().getState();
            if (state instanceof Chest) {
                Chest chest = (Chest) state;
                boolean hasEmptySlot = false;
                for (ItemStack stack : chest.getInventory().getContents()) {
                    if (stack == null) {
                        hasEmptySlot = true;
                        break;
                    }
                }
                if (hasEmptySlot) {
                    if (event.getVehicle() instanceof StorageMinecart) {
                        Minecart minecart = (Minecart) event.getVehicle();
                        minecart.eject();
                        minecart.remove();
                        chest.getInventory().setItem(chest.getInventory().firstEmpty(), new ItemStack(Material.STORAGE_MINECART));
                    } else if (event.getVehicle() instanceof PoweredMinecart) {
                        Minecart minecart = (Minecart) event.getVehicle();
                        minecart.eject();
                        minecart.remove();
                        chest.getInventory().setItem(chest.getInventory().firstEmpty(), new ItemStack(Material.POWERED_MINECART));
                    } else if (event.getVehicle() instanceof Minecart) {
                        Minecart minecart = (Minecart) event.getVehicle();
                        minecart.eject();
                        minecart.remove();
                        chest.getInventory().setItem(chest.getInventory().firstEmpty(), new ItemStack(Material.MINECART));
                    }
                }
            }
        }
    }

    public boolean isMinecartOnRail(Minecart minecart) {

        return minecart.getLocation().getBlock().getType() == Material.RAILS;
    }

    public void boostMinecart(Minecart minecart, boolean boost) {

        Vector speed = minecart.getVelocity();
        double speedNumber = 0.3913788423600029D * 5;

        if (speed.getX() > 0.0D) {
            if (boost) {
                speed.setX(speedNumber);
            } else {
                speed.setX(0.1);
            }
        } else if (speed.getX() < 0.0D) {
            if (boost) {
                speed.setX(-speedNumber);
            } else {
                speed.setX(-0.1);
            }
        } else if (speed.getZ() > 0.0D) {
            if (boost) {
                speed.setZ(speedNumber);
            } else {
                speed.setZ(0.1);
            }
        } else if (speed.getZ() < 0.0D) {
            if (boost) {
                speed.setZ(-speedNumber);
            } else {
                speed.setZ(-0.1);
            }
        }

        minecart.setVelocity(speed);
    }

    public void boostMinecartWithSpeed(Minecart minecart, double boost) {

        Vector speed = minecart.getVelocity();

        if (speed.getX() > 0.0D) {
            speed.setX(boost);
        } else if (speed.getX() < 0.0D) {
            speed.setX(-boost);
        } else if (speed.getZ() > 0.0D) {
            speed.setZ(boost);
        } else if (speed.getZ() < 0.0D) {
            speed.setZ(-boost);
        }

        minecart.setVelocity(speed);
    }

    MinecartRevolution                 plugin;

    public FileHashMap<String, String> stationWordMap = new FileHashMap<String, String>("stationWordMap");
}