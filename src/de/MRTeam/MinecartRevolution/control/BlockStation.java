//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import java.util.ArrayList;
import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;

public class BlockStation {

    public void execute(Minecart minecart) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign == null) {
            return;
        }

        if (sign.getLine(1).equalsIgnoreCase("[Station]")) {
            if (!MinecartRevolution.blockUtil.getControlBlock(minecart).isBlockIndirectlyPowered()) {
                Vector stop = new Vector(0, 0, 0);
                minecart.setVelocity(stop);
            } else {
                MinecartRevolution.blockUtil.setNormalSpeedInSignDirection(minecart, MinecartRevolution.blockUtil.getSignBlockSign(minecart));
            }
        } else if (sign.getLine(1).equalsIgnoreCase("[Hold]") && sign.getLine(2) != null) {
            if (!MinecartRevolution.blockUtil.getControlBlock(minecart).isBlockIndirectlyPowered()) {
                Vector stop = new Vector(0, 0, 0);
                minecart.setVelocity(stop);
                try {
                    int time = 0;
                    if (sign.getLine(3).isEmpty()) {
                        time = Integer.parseInt(sign.getLine(2));
                    } else {
                        time = Integer.parseInt(sign.getLine(3));
                    }

                    HoldTimerThread timerThread = new HoldTimerThread();
                    timerThread.setValues(time, minecart, sign);
                    timerThread.start();
                }
                catch (Exception ex) {
                }
            } else {
                MinecartRevolution.blockUtil.setNormalSpeedInSignDirection(minecart, MinecartRevolution.blockUtil.getSignBlockSign(minecart));
            }
        } else if (sign.getLine(1).equalsIgnoreCase("[TrainStation]")) {
            if ( (minecart.getVelocity().getX() > 0 && getNewTrainStationDirection(sign)[0].equalsIgnoreCase("x+")) || (minecart.getVelocity().getX() < 0 && getNewTrainStationDirection(sign)[1].equalsIgnoreCase("x-")) || (minecart.getVelocity().getZ() > 0 && getNewTrainStationDirection(sign)[2].equalsIgnoreCase("z+")) || (minecart.getVelocity().getZ() < 0 && getNewTrainStationDirection(sign)[3].equalsIgnoreCase("z-"))) {
            } else {
                String[] newDirections = getNewTrainStationDirection(sign);
                Vector speed = minecart.getVelocity();
                double boost = 0.3913788423600029D * 5;

                if (newDirections[0].equalsIgnoreCase("x+")) {
                    Location newLocation = minecart.getLocation();
                    newLocation.setX(minecart.getLocation().getX() + 1.0D);
                    minecart.teleport(newLocation);
                    speed.setX(boost);
                } else if (newDirections[1].equalsIgnoreCase("x-")) {
                    Location newLocation = minecart.getLocation();
                    newLocation.setX(minecart.getLocation().getX() - 1.0D);
                    minecart.teleport(newLocation);
                    speed.setX(-boost);
                } else if (newDirections[2].equalsIgnoreCase("z+")) {
                    Location newLocation = minecart.getLocation();
                    newLocation.setZ(minecart.getLocation().getZ() + 1.0D);
                    minecart.teleport(newLocation);
                    speed.setZ(boost);
                } else if (newDirections[3].equalsIgnoreCase("z-")) {
                    Location newLocation = minecart.getLocation();
                    newLocation.setZ(minecart.getLocation().getZ() - 1.0D);
                    minecart.teleport(newLocation);
                    speed.setZ(-boost);
                } else {
                    speed = new Vector(0, 0, 0);
                }

                minecart.setVelocity(speed);
            }
        }
    }

    private String[] getNewTrainStationDirection(Sign sign) {

        String[] directions = { "", "", "", "" };

        if (sign.getBlock().getLocation().add(1, 0, 0).getBlock().getType() == Material.REDSTONE_WIRE && !sign.getBlock().getLocation().add(1, 0, 0).getBlock().isBlockIndirectlyPowered()) {
            directions[0] = "x+";
        }
        if (sign.getBlock().getLocation().subtract(1, 0, 0).getBlock().getType() == Material.REDSTONE_WIRE && !sign.getBlock().getLocation().subtract(1, 0, 0).getBlock().isBlockIndirectlyPowered()) {
            directions[1] = "x-";
        }
        if (sign.getBlock().getLocation().add(0, 0, 1).getBlock().getType() == Material.REDSTONE_WIRE && !sign.getBlock().getLocation().add(0, 0, 1).getBlock().isBlockIndirectlyPowered()) {
            directions[2] = "z+";
        }
        if (sign.getBlock().getLocation().subtract(0, 0, 1).getBlock().getType() == Material.REDSTONE_WIRE && !sign.getBlock().getLocation().subtract(0, 0, 1).getBlock().isBlockIndirectlyPowered()) {
            directions[3] = "z-";
        }

        return directions;
    }

    public void checkPlayerEnter(Minecart minecart) {

        if (minecart == null || !MinecartRevolution.minecartListener.isMinecartOnRail(minecart)) {
            return;
        }
        if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == MinecartRevolution.configUtil.stationBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == MinecartRevolution.configUtil.stationBlockId[1] || MinecartRevolution.configUtil.stationBlockId[1] == -1)) {
            if (MinecartRevolution.blockUtil.getSignBlockSign(minecart) != null && MinecartRevolution.blockUtil.getSignBlockSign(minecart).getLine(2).equalsIgnoreCase("Enter")) {
                MinecartRevolution.blockUtil.setNormalSpeedInSignDirection(minecart, MinecartRevolution.blockUtil.getSignBlockSign(minecart));
            }
        }
    }

    static ArrayList<Minecart> waitingList = new ArrayList<Minecart>();

}

class HoldTimerThread extends Thread implements Runnable {

    public void setValues(int time, Minecart minecart, Sign sign) {

        this.secondCounter = time;
        this.minecart = minecart;
        this.sign = sign;
    }

    @Override
    public void run() {

        if (BlockStation.waitingList.contains(minecart)) {
            return;
        }
        BlockStation.waitingList.add(minecart);

        while (!isInterrupted()) {
            try {
                if (sign.getLine(3).isEmpty()) {
                    sign.setLine(3, sign.getLine(2));
                }
                sign.setLine(2, String.valueOf(secondCounter) + " more sec");
                sign.update();

                if (secondCounter < 1) {
                    sign.setLine(2, sign.getLine(3));
                    sign.setLine(3, "");
                    sign.update();
                    MinecartRevolution.blockUtil.setNormalSpeedInSignDirection(minecart, sign);
                    BlockStation.waitingList.remove(minecart);
                    return;
                }

                secondCounter--;

                Thread.sleep(1000);
            }
            catch (Exception ex) {
            }
        }
    }

    int      secondCounter = 0;
    Minecart minecart;
    Sign     sign;

}