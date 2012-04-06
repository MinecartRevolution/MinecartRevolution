
package de.MRTeam.MinecartRevolution.action;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import de.MRTeam.MinecartRevolution.MinecartRevolution;
import de.MRTeam.MinecartRevolution.addon.ControlSign;

public class SignPlaceAction implements Listener {

    public SignPlaceAction(MinecartRevolution plugin) {

        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {

        if (event.getLine(0).equalsIgnoreCase("[Announce]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "announceSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdAnnounceSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[Station]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "stationSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdStationSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[Hold]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "holdSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdHoldSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[TrainStation]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "trainStationSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdTrainStationSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[Spawn]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "spawnSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdSpawnSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(0).equalsIgnoreCase("[FlyBoost]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "flySign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdFlyBoostSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(0).equalsIgnoreCase("[Chest]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "chestSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdChestSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(0).equalsIgnoreCase("[Collect]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "collectSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdCollectSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(0).equalsIgnoreCase("[Craft]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "craftSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdCraftSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[Smelt]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "smeltSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdSmeltSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(0).equalsIgnoreCase("[Farm]")) {
            try {
                if (Integer.parseInt(event.getLine(2)) > MinecartRevolution.configUtil.farmMaxRadius) {
                    event.getBlock().breakNaturally();
                    MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), ChatColor.RED + "The radius is too large. Make sure your radius is below " + MinecartRevolution.configUtil.farmMaxRadius + "!", true);
                    return;
                }
            }
            catch (NumberFormatException ex) {
            }
            if (event.getLine(1).equalsIgnoreCase("Wood") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "farmSign.wood")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdFarmSign", "placeCtrlSign"), true);
            } else if (event.getLine(1).equalsIgnoreCase("Wheat") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "farmSign.wheat")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdFarmSign", "placeCtrlSign"), true);
            } else if (event.getLine(1).equalsIgnoreCase("Pumpkin") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "farmSign.pumpkin")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdFarmSign", "placeCtrlSign"), true);
            } else if (event.getLine(1).equalsIgnoreCase("Melon") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "farmSign.melon")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdFarmSign", "placeCtrlSign"), true);
            } else if (event.getLine(1).equalsIgnoreCase("SugarCane") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "farmSign.sugarcane")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdFarmSign", "placeCtrlSign"), true);
            } else if (event.getLine(1).equalsIgnoreCase("Remove") || event.getLine(2).equalsIgnoreCase("Remove") || event.getLine(3).equalsIgnoreCase("Remove")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdFarmSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[Grab]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "grabSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdGrabSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[Lock]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "lockSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdLockSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[MaxSpeed]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "maxspeedSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdMaxspeedSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[Kill]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "killSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdKillSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[Time]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "timeSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdTimeSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[Weather]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "weatherSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdWeatherSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(0).equalsIgnoreCase("[Sensor]")) {
            if (event.getLine(1).equalsIgnoreCase("Direction") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "sensorSign.direction")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdSensorSign", "placeCtrlSign"), true);
            } else if (event.getLine(1).equalsIgnoreCase("Minecart") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "sensorSign.minecart")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdSensorSign", "placeCtrlSign"), true);
            } else if (event.getLine(1).equalsIgnoreCase("Player") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "sensorSign.player")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdSensorSign", "placeCtrlSign"), true);
            } else if (event.getLine(1).equalsIgnoreCase("Mob") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "sensorSign.mob")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdSensorSign", "placeCtrlSign"), true);
            } else if (event.getLine(1).equalsIgnoreCase("Empty") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "sensorSign.empty")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdSensorSign", "placeCtrlSign"), true);
            } else if (event.getLine(1).equalsIgnoreCase("Nocargo") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "sensorSign.nocargo")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdSensorSign", "placeCtrlSign"), true);
            } else if (event.getLine(1).equalsIgnoreCase("Item") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "sensorSign.item")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdSensorSign", "placeCtrlSign"), true);
            } else if (event.getLine(1).equalsIgnoreCase("St") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "sensorSign.st")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdSensorSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(0).equalsIgnoreCase("[Intersection]")) {
            boolean allPermissions = true;
            for (int counter = 1; counter <= 3; counter++) {
                String[] lineContent = event.getLine(counter).split(":");
                if ( (lineContent[0].contains("N") || lineContent[0].equalsIgnoreCase("O") || lineContent[0].equalsIgnoreCase("S") || lineContent[0].equalsIgnoreCase("W")) && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "intersectionSign.direction")) {
                } else if ( (lineContent[0].contains("Standard") || lineContent[0].contains("Storage") || lineContent[0].contains("Powered")) && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "intersectionSign.minecart")) {
                } else if (lineContent[0].contains("Player") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "intersectionSign.player")) {
                } else if (lineContent[0].contains("Mob") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "intersectionSign.mob")) {
                } else if (lineContent[0].contains("Empty") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "intersectionSign.empty")) {
                } else if (lineContent[0].contains("Nocargo") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "intersectionSign.nocargo")) {
                } else if (lineContent[0].contains("Redstone") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "intersectionSign.redstone")) {
                } else if (lineContent[0].contains("p-") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "intersectionSign.player")) {
                } else if (lineContent[0].contains("invc-") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "intersectionSign.item")) {
                } else if (lineContent[0].contains("ihold-") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "intersectionSign.item")) {
                } else if (lineContent[0].contains("st-") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "intersectionSign.st")) {
                } else {
                    if (!event.getLine(counter).isEmpty()) {
                        allPermissions = false;
                    }
                }
            }
            if (!allPermissions) {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
                return;
            }
            MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdIntersectionSign", "placeCtrlSign"), true);
        } else if (event.getLine(1).equalsIgnoreCase("[Eject]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "ejectSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdEjectSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(0).equalsIgnoreCase("[Command]")) {
            String command = (event.getLine(1) + event.getLine(2));
            String permissionsCommand = command.replaceAll(".*", "%").replaceAll(" ", "_");

            if ( ( (event.getLine(3).equalsIgnoreCase("Player") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "commandSign.executer.player")) || (event.getLine(3).equalsIgnoreCase("Console") && MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "commandSign.executer.console"))) && (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "commandSign.executer.command.*") || MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "commandSign.executer.command." + permissionsCommand))) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdCommandSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[Descent]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "descentSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdDescentSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[Destination]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "destinationSign")) {
                if (MinecartRevolution.databaseDestinationLocationUtil.readDestinationLocation(event.getLine(2)) == null) {
                    MinecartRevolution.databaseDestinationLocationUtil.saveDestinationLocation(event.getLine(2), event.getBlock().getLocation());
                }
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdDestinationSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[Effect]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "effectSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdEffectSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else if (event.getLine(1).equalsIgnoreCase("[InvClear]")) {
            if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "invClearSign")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdInvClearSign", "placeCtrlSign"), true);
            } else {
                noPermissions(event.getBlock().getLocation(), event.getPlayer());
            }
        } else {
            for (int counter = 0; counter < MinecartRevolution.signAction.signList.size(); counter++) {
                ControlSign controlSign = MinecartRevolution.signAction.signList.get(counter);
                if ( (event.getLine(0).equalsIgnoreCase(controlSign.getLine(0)) || controlSign.getLine(0).equalsIgnoreCase("")) && (event.getLine(1).equalsIgnoreCase(controlSign.getLine(1)) || controlSign.getLine(1).equalsIgnoreCase("")) && (event.getLine(2).equalsIgnoreCase(controlSign.getLine(2)) || controlSign.getLine(2).equalsIgnoreCase("")) && (event.getLine(3).equalsIgnoreCase(controlSign.getLine(3)) || controlSign.getLine(3).equalsIgnoreCase(""))) {
                    if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeControlBlock", controlSign.getBlockName())) {
                        MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getAddonMessage(controlSign.plugin, "placeCtrlSign.created" + controlSign.getBlockName()), true);
                    } else {
                        noPermissions(event.getBlock().getLocation(), event.getPlayer());
                        return;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Block block = event.getBlock();
        BlockState state = block.getState();
        if (state instanceof Sign) {
            Sign sign = (Sign) state;

            if (sign.getLine(0).equalsIgnoreCase("[Announce]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedAnnounceSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[Station]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedStationSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[Hold]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedHoldSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[TrainStation]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedTrainStationSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[Spawn]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedSpawnSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(0).equalsIgnoreCase("[FlyBoost]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedFlyBoostSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(0).equalsIgnoreCase("[Chest]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedChestSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(0).equalsIgnoreCase("[Collect]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedCollectSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(0).equalsIgnoreCase("[Craft]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedCraftSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[Smelt]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedSmeltSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(0).equalsIgnoreCase("[Farm]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedFarmSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[Grab]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedGrabSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[Lock]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedLockSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[MaxSpeed]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedMaxspeedSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[Kill]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedKillSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[Time]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedTimeSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[Weather]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedWeatherSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(0).equalsIgnoreCase("[Sensor]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedSensorSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(0).equalsIgnoreCase("[Intersection]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedIntersectionSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[Eject]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedEjectSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(0).equalsIgnoreCase("[Command]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedCommandSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[Descent]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedDescentSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[Destination]")) {
                MinecartRevolution.databaseDestinationLocationUtil.deleteDestinationLocation(sign.getLine(2));
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedDestinationSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[Effect]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedEffectSign", "destroyedCtrlSign"), true);
            } else if (sign.getLine(1).equalsIgnoreCase("[InvClear]")) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedInvClearSign", "destroyedCtrlSign"), true);
            } else {
                for (int counter = 0; counter < MinecartRevolution.signAction.signList.size(); counter++) {
                    ControlSign controlSign = MinecartRevolution.signAction.signList.get(counter);
                    if ( (sign.getLine(0).equalsIgnoreCase(controlSign.getLine(0)) || controlSign.getLine(0).equalsIgnoreCase("")) && (sign.getLine(1).equalsIgnoreCase(controlSign.getLine(1)) || controlSign.getLine(1).equalsIgnoreCase("")) && (sign.getLine(2).equalsIgnoreCase(controlSign.getLine(2)) || controlSign.getLine(2).equalsIgnoreCase("")) && (sign.getLine(3).equalsIgnoreCase(controlSign.getLine(3)) || controlSign.getLine(3).equalsIgnoreCase(""))) {
                        MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getAddonMessage(controlSign.plugin, "destroyedCtrlSign.destroyed" + controlSign.getBlockName()), true);
                    }
                }
            }
        }
    }

    private void noPermissions(Location location, Player player) {

        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("noPermission", ""), true);
        location.getBlock().breakNaturally();
    }

    MinecartRevolution plugin;

}
