//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.util;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.material.Lever;
import org.bukkit.util.Vector;

public class BlockUtil {

    MinecartRevolution plugin;

    public BlockUtil(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    public Block getControlBlock(Minecart minecart) {

        Location controlBlockLocation = minecart.getLocation();
        if (controlBlockLocation.getBlock().getTypeId() == 66 || controlBlockLocation.getBlock().getTypeId() == 27 || controlBlockLocation.getBlock().getTypeId() == 28) {
            if (MinecartRevolution.configUtil.prettyControlBlocks.equalsIgnoreCase("true")) {
                controlBlockLocation.setY(controlBlockLocation.getY() - 2.0D);
            } else {
                controlBlockLocation.setY(controlBlockLocation.getY() - 1.0D);
            }
            return minecart.getWorld().getBlockAt(controlBlockLocation);
        }
        return null;
    }

    public Sign getSignBlockSign(Minecart minecart) {

        return getSignBlockSign(minecart.getLocation().getBlock().getLocation());
    }

    public Sign getSignBlockSign(Location location) {

        Location oldControlBlockLocation = location;
        if (oldControlBlockLocation.getBlock().getTypeId() == 66 || oldControlBlockLocation.getBlock().getTypeId() == 27 || oldControlBlockLocation.getBlock().getTypeId() == 28) {
            Location controlBlockLocation = oldControlBlockLocation.clone();
            controlBlockLocation.setY(controlBlockLocation.getY() - 2.0D);
            if (controlBlockLocation.getBlock() != null && controlBlockLocation.getBlock().getType() == Material.SIGN_POST) {
                return (Sign) location.getWorld().getBlockAt(controlBlockLocation).getState();
            }
            if (MinecartRevolution.configUtil.prettyControlBlocks.equalsIgnoreCase("true")) {
                controlBlockLocation = oldControlBlockLocation.clone();
                controlBlockLocation.setY(controlBlockLocation.getY() - 3.0D);
                if (controlBlockLocation.getBlock() != null && controlBlockLocation.getBlock().getType() == Material.SIGN_POST) {
	return (Sign) location.getWorld().getBlockAt(controlBlockLocation).getState();
                }
            }
            controlBlockLocation = oldControlBlockLocation.clone();
            controlBlockLocation.setY(controlBlockLocation.getY() + 2.0D);
            if (controlBlockLocation.getBlock() != null && controlBlockLocation.getBlock().getType() == Material.SIGN_POST) {
                return (Sign) location.getWorld().getBlockAt(controlBlockLocation).getState();
            }
            controlBlockLocation = oldControlBlockLocation.clone();
            controlBlockLocation.setY(controlBlockLocation.getY() + 3.0D);
            if (controlBlockLocation.getBlock() != null && controlBlockLocation.getBlock().getType() == Material.SIGN_POST) {
                return (Sign) location.getWorld().getBlockAt(controlBlockLocation).getState();
            }
            controlBlockLocation = oldControlBlockLocation.clone();
            controlBlockLocation.setX(controlBlockLocation.getX() - 1.0D);
            if (controlBlockLocation.getBlock() != null && controlBlockLocation.getBlock().getType() == Material.SIGN_POST) {
                return (Sign) location.getWorld().getBlockAt(controlBlockLocation).getState();
            }
            controlBlockLocation = oldControlBlockLocation.clone();
            controlBlockLocation.setX(controlBlockLocation.getX() + 1.0D);
            if (controlBlockLocation.getBlock() != null && controlBlockLocation.getBlock().getType() == Material.SIGN_POST) {
                return (Sign) location.getWorld().getBlockAt(controlBlockLocation).getState();
            }
            controlBlockLocation = oldControlBlockLocation.clone();
            controlBlockLocation.setZ(controlBlockLocation.getZ() - 1.0D);
            if (controlBlockLocation.getBlock() != null && controlBlockLocation.getBlock().getType() == Material.SIGN_POST) {
                return (Sign) location.getWorld().getBlockAt(controlBlockLocation).getState();
            }
            controlBlockLocation = oldControlBlockLocation.clone();
            controlBlockLocation.setZ(controlBlockLocation.getZ() + 1.0D);
            if (controlBlockLocation.getBlock() != null && controlBlockLocation.getBlock().getType() == Material.SIGN_POST) {
                return (Sign) location.getWorld().getBlockAt(controlBlockLocation).getState();
            }
        }
        return null;
    }

    public Sign getSignBlockSignMoreRadius(Minecart minecart) {

        Location oldControlBlockLocation = minecart.getLocation().getBlock().getLocation();
        if (oldControlBlockLocation.getBlock().getTypeId() == 66 || oldControlBlockLocation.getBlock().getTypeId() == 27 || oldControlBlockLocation.getBlock().getTypeId() == 28) {
            Location location = minecart.getLocation();
            if (getSignBlockSign(location) != null) {
                return getSignBlockSign(location);
            }
            location.setX(location.getX() + 1.0D);
            if (getSignBlockSign(location) != null) {
                return getSignBlockSign(location);
            }
            location.setX(location.getX() - 2.0D);
            if (getSignBlockSign(location) != null) {
                return getSignBlockSign(location);
            }
            location.setX(location.getX() + 1.0D);
            location.setZ(location.getZ() + 1.0D);
            if (getSignBlockSign(location) != null) {
                return getSignBlockSign(location);
            }
            location.setZ(location.getZ() - 2.0D);
            if (getSignBlockSign(location) != null) {
                return getSignBlockSign(location);
            }
        }
        return null;
    }

    public void setNormalSpeedInSignDirection(Minecart minecart, Sign sign) {

        Vector speed = minecart.getVelocity();
        double boost = 0.3913788423600029D;

        if (sign.getRawData() == 0) {
            Location newLocation = minecart.getLocation();
            newLocation.setZ(minecart.getLocation().getZ() - 1.0D);
            minecart.teleport(newLocation);
            speed.setZ(-boost);
        } else if (sign.getRawData() == 4) {
            Location newLocation = minecart.getLocation();
            newLocation.setX(minecart.getLocation().getX() + 1.0D);
            minecart.teleport(newLocation);
            speed.setX(boost);
        } else if (sign.getRawData() == 8) {
            Location newLocation = minecart.getLocation();
            newLocation.setZ(minecart.getLocation().getZ() + 1.0D);
            minecart.teleport(newLocation);
            speed.setZ(boost);
        } else if (sign.getRawData() == 12) {
            Location newLocation = minecart.getLocation();
            newLocation.setX(minecart.getLocation().getX() - 1.0D);
            minecart.teleport(newLocation);
            speed.setX(-boost);
        }
        minecart.setVelocity(speed);
    }

    public Block powerNearbyLeaver(Block block, boolean powered) {

        if (block == null) {
            return null;
        }

        Block leverBlock = null;

        if (block.getWorld().getBlockAt(block.getX() + 1, block.getY() - 1, block.getZ()).getType() == Material.LEVER) {
            leverBlock = block.getWorld().getBlockAt(block.getX() + 1, block.getY() - 1, block.getZ());
        } else if (block.getWorld().getBlockAt(block.getX() - 1, block.getY() - 1, block.getZ()).getType() == Material.LEVER) {
            leverBlock = block.getWorld().getBlockAt(block.getX() - 1, block.getY() - 1, block.getZ());
        } else if (block.getWorld().getBlockAt(block.getX(), block.getY() - 1, block.getZ() + 1).getType() == Material.LEVER) {
            leverBlock = block.getWorld().getBlockAt(block.getX(), block.getY() - 1, block.getZ() + 1);
        } else if (block.getWorld().getBlockAt(block.getX(), block.getY() - 1, block.getZ() - 1).getType() == Material.LEVER) {
            leverBlock = block.getWorld().getBlockAt(block.getX(), block.getY() - 1, block.getZ() - 1);
        }

        if (leverBlock == null || leverBlock.getType() != Material.LEVER) {
            return null;
        }
        BlockState blockState = leverBlock.getState();
        Lever lever = (Lever) blockState.getData();
        lever.setPowered(powered);
        blockState.setData(lever);
        blockState.update();

        return leverBlock;
    }

    public int[] splitBlockData(String blockData) {

        try {
            String[] blockDataStrings = blockData.split(":");
            if (blockDataStrings.length == 2) {
                int[] blockDataArray = { Integer.parseInt(blockDataStrings[0]), Integer.parseInt(blockDataStrings[1]) };
                return blockDataArray;
            } else if (blockDataStrings.length == 1) {
                int[] blockDataArray = { Integer.parseInt(blockDataStrings[0]), -1 };
                return blockDataArray;
            } else {
                return null;
            }
        }
        catch (NumberFormatException ex) {
            return null;
        }
    }
}
