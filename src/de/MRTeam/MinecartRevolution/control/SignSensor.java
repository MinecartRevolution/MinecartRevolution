//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.block.Sign;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.StorageMinecart;

public class SignSensor {

    public void execute(Minecart minecart) {

        if (MinecartRevolution.blockUtil.getSignBlockSign(minecart) != null) {
            Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);

            if (sign.getLine(1).equalsIgnoreCase("Direction")) {
                if (sign.getLine(2).equalsIgnoreCase("N") && minecart.getVelocity().getZ() > 0.0D) {
                    MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                } else if (sign.getLine(2).equalsIgnoreCase("E") && minecart.getVelocity().getX() < 0.0D) {
                    MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                } else if (sign.getLine(2).equalsIgnoreCase("S") && minecart.getVelocity().getZ() < 0.0D) {
                    MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                } else if (sign.getLine(2).equalsIgnoreCase("W") && minecart.getVelocity().getX() > 0.0D) {
                    MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                }
            } else if (sign.getLine(1).equalsIgnoreCase("Minecart")) {
                if (sign.getLine(2).equalsIgnoreCase("Storage") && minecart instanceof StorageMinecart) {
                    MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                } else if (sign.getLine(2).equalsIgnoreCase("Powered") && minecart instanceof PoweredMinecart) {
                    MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                } else if (sign.getLine(2).equalsIgnoreCase("Standard") && minecart instanceof Minecart && ! (minecart instanceof StorageMinecart) && ! (minecart instanceof PoweredMinecart)) {
                    MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                }
            } else if (sign.getLine(1).equalsIgnoreCase("Player")) {
                if (minecart.getPassenger() instanceof Player) {
                    if (sign.getLine(2).equalsIgnoreCase("")) {
                        MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                    } else if (sign.getLine(2).equalsIgnoreCase( ((Player) minecart.getPassenger()).getName())) {
                        MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                    }
                }
            } else if (sign.getLine(1).equalsIgnoreCase("Mob")) {
                if (minecart.getPassenger() instanceof Monster || minecart.getPassenger() instanceof Animals) {
                    MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                }
            } else if (sign.getLine(1).equalsIgnoreCase("Empty")) {
                if (minecart.isEmpty()) {
                    MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                }
            } else if (sign.getLine(1).equalsIgnoreCase("Nocargo")) {
                if (minecart.getPassenger() instanceof Player) {
                    Player player = (Player) minecart.getPassenger();
                    for (int counter = 0; counter < player.getInventory().getSize(); counter++) {
                        if (player.getInventory().getItem(counter) != null) {
                            return;
                        }
                    }
                    MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                } else if (minecart instanceof StorageMinecart) {
                    StorageMinecart storageMinecart = (StorageMinecart) minecart;
                    for (int counter = 0; counter < storageMinecart.getInventory().getSize(); counter++) {
                        if (storageMinecart.getInventory().getItem(counter) != null) {
                            return;
                        }
                    }
                    MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                } else {
                    MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                }
            } else if (sign.getLine(1).equalsIgnoreCase("Item")) {
                if (sign.getLine(2).equalsIgnoreCase("Inventory")) {
                    if (minecart instanceof StorageMinecart) {
                        StorageMinecart storageMinecart = (StorageMinecart) minecart;
                        try {
                            String[] itemIds = sign.getLine(3).split(",");
                            for (int counter = 0; counter < itemIds.length; counter++) {
	if (!storageMinecart.getInventory().contains(Integer.parseInt(itemIds[counter]))) {
	    return;
	}
                            }
                            MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                        }
                        catch (NumberFormatException ex) {
                        }
                    } else if (minecart.getPassenger() instanceof Player) {
                        Player player = (Player) minecart.getPassenger();
                        try {
                            String[] itemIds = sign.getLine(3).split(",");
                            for (int counter = 0; counter < itemIds.length; counter++) {
	if (!player.getInventory().contains(Integer.parseInt(itemIds[counter]))) {
	    return;
	}
                            }
                            MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                        }
                        catch (NumberFormatException ex) {
                        }
                    }
                } else if (sign.getLine(2).equalsIgnoreCase("Hand") && minecart.getPassenger() instanceof Player) {
                    Player player = (Player) minecart.getPassenger();
                    try {
                        if (player.getInventory().getItemInHand().getTypeId() == Integer.parseInt(sign.getLine(3))) {
                            MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                        }
                    }
                    catch (NumberFormatException ex) {
                    }
                }
            } else if (sign.getLine(1).equalsIgnoreCase("St") && minecart.getPassenger() instanceof Player) {
                if (MinecartRevolution.minecartListener.stationWordMap.containsKey( ((Player) minecart.getPassenger()).getName()) && MinecartRevolution.minecartListener.stationWordMap.get( ((Player) minecart.getPassenger()).getName()).equalsIgnoreCase(sign.getLine(2))) {
                    MinecartRevolution.blockUtil.powerNearbyLeaver(sign.getBlock(), true);
                }
            }
        }
    }
}
