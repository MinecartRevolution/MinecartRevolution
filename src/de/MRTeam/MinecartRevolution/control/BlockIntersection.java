//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import java.util.HashMap;
import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.util.Vector;

public class BlockIntersection {

    public void execute(Minecart minecart) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign == null) {
            if (minecart.getPassenger() instanceof Player) {
                Player player = (Player) minecart.getPassenger();
                try {
                    if (punchMessagePlayerMap.get(player) == null) {
                        punchMessagePlayerMap.put(player, false);
                    }
                }
                catch (NullPointerException ex) {
                }

                if (!punchMessagePlayerMap.get(player)) {
                    minecart.setVelocity(new Vector(0, 0, 0));
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("punch", ""), true);
                    punchMessagePlayerMap.remove(player);
                    punchMessagePlayerMap.put(player, true);
                }
            }
        } else {
            if (checkLine(minecart, sign, 1)) {
                return;
            }
            if (checkLine(minecart, sign, 2)) {
                return;
            }
            if (checkLine(minecart, sign, 3)) {
                return;
            }

            driveMinecart(minecart, "M");
        }
    }

    private boolean checkLine(Minecart minecart, Sign sign, int line) {

        String newDriveDirection = "";
        String[] lineContent = sign.getLine(line).split(":");
        try {
            if (lineContent[0].equalsIgnoreCase("N") || lineContent[0].equalsIgnoreCase("E") || lineContent[0].equalsIgnoreCase("S") || lineContent[0].equalsIgnoreCase("W")) {
                if (lineContent[0].equalsIgnoreCase("N") && minecart.getVelocity().getZ() > 0.0D) {
                    newDriveDirection = lineContent[1];
                } else if (lineContent[0].equalsIgnoreCase("E") && minecart.getVelocity().getX() < 0.0D) {
                    newDriveDirection = lineContent[1];
                } else if (lineContent[0].equalsIgnoreCase("S") && minecart.getVelocity().getZ() < 0.0D) {
                    newDriveDirection = lineContent[1];
                } else if (lineContent[0].equalsIgnoreCase("W") && minecart.getVelocity().getX() > 0.0D) {
                    newDriveDirection = lineContent[1];
                }
            } else if (lineContent[0].equalsIgnoreCase("Storage") && minecart instanceof StorageMinecart) {
                newDriveDirection = lineContent[1];
            } else if (lineContent[0].equalsIgnoreCase("Powered") && minecart instanceof PoweredMinecart) {
                newDriveDirection = lineContent[1];
            } else if (lineContent[0].equalsIgnoreCase("Standard") && minecart instanceof Minecart && ! (minecart instanceof StorageMinecart) && ! (minecart instanceof PoweredMinecart)) {
                newDriveDirection = lineContent[1];
            } else if (lineContent[0].equalsIgnoreCase("Player") && minecart.getPassenger() instanceof Player) {
                newDriveDirection = lineContent[1];
            } else if (lineContent[0].equalsIgnoreCase("Mob") && (minecart.getPassenger() instanceof Monster || minecart.getPassenger() instanceof Animals)) {
                newDriveDirection = lineContent[1];
            } else if (lineContent[0].equalsIgnoreCase("Empty") && minecart.isEmpty()) {
                newDriveDirection = lineContent[1];
            } else if (lineContent[0].equalsIgnoreCase("Nocargo")) {
                if (minecart.getPassenger() instanceof Player) {
                    Player player = (Player) minecart.getPassenger();
                    for (int counter = 0; counter < player.getInventory().getSize(); counter++) {
                        if (player.getInventory().getItem(counter) != null) {
                            return false;
                        }
                    }
                    newDriveDirection = lineContent[1];
                } else if (minecart instanceof StorageMinecart) {
                    StorageMinecart storageMinecart = (StorageMinecart) minecart;
                    for (int counter = 0; counter < storageMinecart.getInventory().getSize(); counter++) {
                        if (storageMinecart.getInventory().getItem(counter) != null) {
                            return false;
                        }
                    }
                    newDriveDirection = lineContent[1];
                } else {
                    newDriveDirection = lineContent[1];
                }
            } else if (lineContent[0].equalsIgnoreCase("Redstone") && MinecartRevolution.blockUtil.getControlBlock(minecart).isBlockIndirectlyPowered()) {
                newDriveDirection = lineContent[1];
            } else if (lineContent[0].contains("p-") && minecart.getPassenger() instanceof Player) {
                Player player = (Player) minecart.getPassenger();
                String[] variables = lineContent[0].split("-");
                if (player.getName().equalsIgnoreCase(variables[1])) {
                    newDriveDirection = lineContent[1];
                }
            } else if (lineContent[0].contains("invc-")) {
                if (minecart.getPassenger() instanceof Player) {
                    Player player = (Player) minecart.getPassenger();
                    String[] variables = lineContent[0].split("-");
                    if (player.getInventory().contains(MinecartRevolution.itemAliasUtil.getItemId(variables[1]))) {
                        newDriveDirection = lineContent[1];
                    }
                } else if (minecart instanceof StorageMinecart) {
                    StorageMinecart storageMinecart = (StorageMinecart) minecart;
                    String[] variables = lineContent[0].split("-");
                    if (storageMinecart.getInventory().contains(MinecartRevolution.itemAliasUtil.getItemId(variables[1]))) {
                        newDriveDirection = lineContent[1];
                    }
                }
            } else if (lineContent[0].contains("ihold-") && minecart.getPassenger() instanceof Player) {
                Player player = (Player) minecart.getPassenger();
                String[] variables = lineContent[0].split("-");
                if (player.getInventory().getItemInHand().getTypeId() == MinecartRevolution.itemAliasUtil.getItemId(variables[1])) {
                    newDriveDirection = lineContent[1];
                }
            } else if (lineContent[0].contains("st-") && minecart.getPassenger() instanceof Player) {
                Player player = (Player) minecart.getPassenger();
                String[] variables = lineContent[0].split("-");
                if (MinecartRevolution.minecartListener.stationWordMap.containsKey(player) && MinecartRevolution.minecartListener.stationWordMap.get(player).equalsIgnoreCase(variables[1])) {
                    newDriveDirection = lineContent[1];
                }
            }
        }
        catch (Exception ex) {
        }

        if (newDriveDirection != "") {
            driveMinecart(minecart, newDriveDirection);
            return true;
        }
        return false;
    }

    public void playerPunch(Minecart minecart, Vector punchVelocity) {

        if (MinecartRevolution.blockUtil.getSignBlockSign(minecart) == null) {
            if (punchVelocity.getX() >= 0 && punchVelocity.getZ() >= 0) {
                driveMinecart(minecart, "W");
            } else if (punchVelocity.getX() <= 0 && punchVelocity.getZ() <= 0) {
                driveMinecart(minecart, "E");
            } else if (punchVelocity.getZ() >= 0 && punchVelocity.getX() <= 0) {
                driveMinecart(minecart, "N");
            } else if (punchVelocity.getZ() <= 0 && punchVelocity.getX() >= 0) {
                driveMinecart(minecart, "S");
            }
        }
    }

    public void resetPunchMessage(Minecart minecart) {

        if (minecart.getPassenger() instanceof Player) {
            Player player = (Player) minecart.getPassenger();
            try {
                if (punchMessagePlayerMap.get(player) == null) {
                    punchMessagePlayerMap.put(player, false);
                }
            }
            catch (NullPointerException ex) {
            }
            punchMessagePlayerMap.remove(player);
            punchMessagePlayerMap.put(player, false);
        }
    }

    private void driveMinecart(Minecart minecart, String newDriveDirection) {

        Vector speed = new Vector();
        double speedNumber = 0.3913788423600029D;
        Location newLocation = minecart.getLocation();
        if (newDriveDirection.equalsIgnoreCase("R") || newDriveDirection.equalsIgnoreCase("M") || newDriveDirection.equalsIgnoreCase("L")) {
            if (minecart.getVelocity().getX() > 0.0D) {
                if (newDriveDirection.equalsIgnoreCase("R")) {
                    speed.setZ(speedNumber);
                    newLocation.setZ(newLocation.getZ() + 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("L")) {
                    speed.setZ(-speedNumber);
                    newLocation.setZ(newLocation.getZ() - 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("M")) {
                    speed.setX(speedNumber);
                }
            } else if (minecart.getVelocity().getX() < 0.0D) {
                if (newDriveDirection.equalsIgnoreCase("R")) {
                    speed.setZ(-speedNumber);
                    newLocation.setZ(newLocation.getZ() - 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("L")) {
                    speed.setZ(speedNumber);
                    newLocation.setZ(newLocation.getZ() + 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("M")) {
                    speed.setX(-speedNumber);
                }
            } else if (minecart.getVelocity().getZ() > 0.0D) {
                if (newDriveDirection.equalsIgnoreCase("R")) {
                    speed.setX(-speedNumber);
                    newLocation.setX(newLocation.getX() - 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("L")) {
                    speed.setX(speedNumber);
                    newLocation.setX(newLocation.getX() + 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("M")) {
                    speed.setZ(speedNumber);
                }
            } else if (minecart.getVelocity().getZ() < 0.0D) {
                if (newDriveDirection.equalsIgnoreCase("R")) {
                    speed.setX(speedNumber);
                    newLocation.setX(newLocation.getX() + 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("L")) {
                    speed.setX(-speedNumber);
                    newLocation.setX(newLocation.getX() - 1.0D);
                } else if (newDriveDirection.equalsIgnoreCase("M")) {
                    speed.setZ(-speedNumber);
                }
            }
        } else if (newDriveDirection.equalsIgnoreCase("N")) {
            speed.setZ(speedNumber);
            newLocation.setZ(newLocation.getZ() + 1.0D);
        } else if (newDriveDirection.equalsIgnoreCase("E")) {
            speed.setX(-speedNumber);
            newLocation.setX(newLocation.getX() - 1.0D);
        } else if (newDriveDirection.equalsIgnoreCase("S")) {
            speed.setZ(-speedNumber);
            newLocation.setZ(newLocation.getZ() - 1.0D);
        } else if (newDriveDirection.equalsIgnoreCase("W")) {
            speed.setX(speedNumber);
            newLocation.setX(newLocation.getX() + 1.0D);
        } else if (newDriveDirection.equalsIgnoreCase("D")) {
            minecart.remove();
        } else if (newDriveDirection.equalsIgnoreCase("EJ")) {
            MinecartRevolution.blockAction.blockEject.execute(minecart);
        }

        minecart.setVelocity(speed);
        minecart.teleport(newLocation);
    }

    HashMap<Player, Boolean> punchMessagePlayerMap = new HashMap<Player, Boolean>();
}
