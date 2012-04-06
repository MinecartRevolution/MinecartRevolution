
package de.MRTeam.MinecartRevolution.action;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import de.MRTeam.MinecartRevolution.MinecartRevolution;
import de.MRTeam.MinecartRevolution.addon.ControlBlock;

public class BlockPlaceAction implements Listener {

    public BlockPlaceAction(MinecartRevolution plugin) {

        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        if (event.getBlockPlaced().getTypeId() == 66 || event.getBlockPlaced().getTypeId() == 27 || event.getBlockPlaced().getTypeId() == 28) {
            Location controlBlock = event.getBlockPlaced().getLocation();
            if (MinecartRevolution.configUtil.prettyControlBlocks.equalsIgnoreCase("true")) {
                controlBlock.setY(controlBlock.getY() - 2.0D);
            } else {
                controlBlock.setY(controlBlock.getY() - 1.0D);
            }

            if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.boosterBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.boosterBlockId[1] || MinecartRevolution.configUtil.boosterBlockId[1] == -1)) {
                if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "booster")) {
                    MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdBooster", "placeCtrlBlock"), true);
                    return;
                }
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.brakeBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.brakeBlockId[1] || MinecartRevolution.configUtil.brakeBlockId[1] == -1)) {
                if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "brake")) {
                    MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdBrake", "placeCtrlBlock"), true);
                    return;
                }
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.reverseBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.reverseBlockId[1] || MinecartRevolution.configUtil.reverseBlockId[1] == -1)) {
                if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "reverse")) {
                    MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdReverse", "placeCtrlBlock"), true);
                    return;
                }
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.ejectBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.ejectBlockId[1] || MinecartRevolution.configUtil.ejectBlockId[1] == -1)) {
                if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "eject")) {
                    MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdEject", "placeCtrlBlock"), true);
                    return;
                }
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.elevatorBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.elevatorBlockId[1] || MinecartRevolution.configUtil.elevatorBlockId[1] == -1)) {
                if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "elevator")) {
                    MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdElevator", "placeCtrlBlock"), true);
                    return;
                }
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.stationBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.stationBlockId[1] || MinecartRevolution.configUtil.stationBlockId[1] == -1)) {
                if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "station")) {
                    MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdStation", "placeCtrlBlock"), true);
                    return;
                }
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.killBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.killBlockId[1] || MinecartRevolution.configUtil.killBlockId[1] == -1)) {
                if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "kill")) {
                    MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdMinecartDestroyer", "placeCtrlBlock"), true);
                    return;
                }
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.clearInvBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.clearInvBlockId[1] || MinecartRevolution.configUtil.clearInvBlockId[1] == -1)) {
                if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "clearInv")) {
                    MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdInvClear", "placeCtrlBlock"), true);
                    return;
                }
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.flyBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.flyBlockId[1] || MinecartRevolution.configUtil.flyBlockId[1] == -1)) {
                if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "fly")) {
                    MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdFly", "placeCtrlBlock"), true);
                    return;
                }
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.healBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.healBlockId[1] || MinecartRevolution.configUtil.healBlockId[1] == -1)) {
                if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "heal")) {
                    MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdHeal", "placeCtrlBlock"), true);
                    return;
                }
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.grabBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.grabBlockId[1] || MinecartRevolution.configUtil.grabBlockId[1] == -1)) {
                if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "grab")) {
                    MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdGrab", "placeCtrlBlock"), true);
                    return;
                }
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.intersectionBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.intersectionBlockId[1] || MinecartRevolution.configUtil.intersectionBlockId[1] == -1)) {
                if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeCtrlBlock", "intersection")) {
                    MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("createdIntersection", "placeCtrlBlock"), true);
                    return;
                }
            } else {
                for (int counter = 0; counter < MinecartRevolution.blockAction.blockList.size(); counter++) {
                    ControlBlock controlBlockC = MinecartRevolution.blockAction.blockList.get(counter);
                    int[] controlBlockCId = MinecartRevolution.blockUtil.splitBlockData((String) MinecartRevolution.configUtil.getAddonSetting(controlBlockC.plugin, "blockId." + controlBlockC.getBlockName()));
                    if (controlBlock.getBlock().getTypeId() == controlBlockCId[0] && (controlBlock.getBlock().getData() == controlBlockCId[1] || controlBlockCId[1] == -1)) {
                        if (MinecartRevolution.permissionUtil.hasPermission(event.getPlayer(), "placeControlBlock", controlBlockC.getBlockName())) {
                            MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getAddonMessage(controlBlockC.plugin, "placeCtrlBlock.created" + controlBlockC.getBlockName()), true);
                        } else {
                            noPermissions(controlBlock, event.getPlayer(), event);
                            return;
                        }
                    }
                }
                return;
            }

            noPermissions(event.getBlock().getLocation(), event.getPlayer(), event);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (event.getBlock().getTypeId() == 66 || event.getBlock().getTypeId() == 27 || event.getBlock().getTypeId() == 28) {
            Location controlBlock = event.getBlock().getLocation();
            if (MinecartRevolution.configUtil.prettyControlBlocks.equalsIgnoreCase("true")) {
                controlBlock.setY(controlBlock.getY() - 2.0D);
            } else {
                controlBlock.setY(controlBlock.getY() - 1.0D);
            }

            if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.boosterBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.boosterBlockId[1] || MinecartRevolution.configUtil.boosterBlockId[1] == -1)) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedBooster", "destroyedCtrlBlock"), true);
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.brakeBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.brakeBlockId[1] || MinecartRevolution.configUtil.brakeBlockId[1] == -1)) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedBrake", "destroyedCtrlBlock"), true);
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.reverseBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.reverseBlockId[1] || MinecartRevolution.configUtil.reverseBlockId[1] == -1)) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedReverse", "destroyedCtrlBlock"), true);
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.ejectBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.ejectBlockId[1] || MinecartRevolution.configUtil.ejectBlockId[1] == -1)) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedEject", "destroyedCtrlBlock"), true);
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.elevatorBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.elevatorBlockId[1] || MinecartRevolution.configUtil.elevatorBlockId[1] == -1)) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedElevator", "destroyedCtrlBlock"), true);
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.stationBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.stationBlockId[1] || MinecartRevolution.configUtil.stationBlockId[1] == -1)) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedStation", "destroyedCtrlBlock"), true);
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.killBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.killBlockId[1] || MinecartRevolution.configUtil.killBlockId[1] == -1)) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedMinecartDestroyer", "destroyedCtrlBlock"), true);
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.clearInvBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.clearInvBlockId[1] || MinecartRevolution.configUtil.clearInvBlockId[1] == -1)) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedInvClear", "destroyedCtrlBlock"), true);
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.flyBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.flyBlockId[1] || MinecartRevolution.configUtil.flyBlockId[1] == -1)) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedFly", "destroyedCtrlBlock"), true);
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.healBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.healBlockId[1] || MinecartRevolution.configUtil.healBlockId[1] == -1)) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedHeal", "destroyedCtrlBlock"), true);
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.grabBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.grabBlockId[1] || MinecartRevolution.configUtil.grabBlockId[1] == -1)) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedGrab", "destroyedCtrlBlock"), true);
            } else if (controlBlock.getBlock().getTypeId() == MinecartRevolution.configUtil.intersectionBlockId[0] && (controlBlock.getBlock().getData() == MinecartRevolution.configUtil.intersectionBlockId[1] || MinecartRevolution.configUtil.intersectionBlockId[1] == -1)) {
                MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getMessage("destroyedIntersection", "destroyedCtrlBlock"), true);
            } else {
                for (int counter = 0; counter < MinecartRevolution.blockAction.blockList.size(); counter++) {
                    ControlBlock controlBlockC = MinecartRevolution.blockAction.blockList.get(counter);
                    int[] controlBlockCId = MinecartRevolution.blockUtil.splitBlockData((String) MinecartRevolution.configUtil.getAddonSetting(controlBlockC.plugin, "blockId." + controlBlockC.getBlockName()));
                    if (controlBlock.getBlock().getTypeId() == controlBlockCId[0] && (controlBlock.getBlock().getData() == controlBlockCId[1] || controlBlockCId[1] == -1)) {
                        MinecartRevolution.messagesUtil.sendMessage(event.getPlayer(), MinecartRevolution.messagesUtil.getAddonMessage(controlBlockC.plugin, "destroyedCtrlBlock.destroyed" + controlBlockC.getBlockName()), true);
                    }
                }
                return;
            }
        }
    }

    private void noPermissions(Location location, Player player, BlockPlaceEvent event) {

        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("noPermission", ""), true);
        location.getBlock().breakNaturally();

        event.setCancelled(true);
    }

    MinecartRevolution plugin;

}
