//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;

public class BlockFly {

    public void execute(Minecart minecart) {

        Location block = minecart.getLocation();
        if (MinecartRevolution.blockUtil.getSignBlockSign(minecart) != null) {
            Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
            if (sign.getLine(0).equalsIgnoreCase("[FlyBoost]") && sign.getLine(1) != "") {
                try {
                    int yBoost = Integer.parseInt(sign.getLine(1));
                    if (!sign.getLine(2).isEmpty()) {
                        int horizontalBoost = Integer.parseInt(sign.getLine(2));
                        Vector speed = minecart.getVelocity();
                        if (speed.getX() > 0.0D) {
                            block.setX(block.getX() + horizontalBoost);
                        } else if (speed.getX() < 0.0D) {
                            block.setX(block.getX() - horizontalBoost);
                        } else if (speed.getZ() > 0.0D) {
                            block.setZ(block.getZ() + horizontalBoost);
                        } else if (speed.getZ() < 0.0D) {
                            block.setZ(block.getZ() - horizontalBoost);
                        }
                    }
                    block.setY(block.getY() + yBoost);
                    minecart.teleport(block);
                    return;
                }
                catch (Exception localException) {
                }
            }
        } else {
            block.setY(block.getY() + 5.0D);
            MinecartRevolution.minecartListener.boostMinecart(minecart, true);
            minecart.teleport(block);
        }
    }
}
