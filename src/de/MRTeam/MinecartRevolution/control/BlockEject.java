//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class BlockEject {

    public void execute(Minecart minecart) {

        if (minecart.getPassenger() != null) {
            Block block = getSignNearLoc(minecart.getLocation());
            Entity entity = minecart.getPassenger();

            if (MinecartRevolution.blockUtil.getSignBlockSign(minecart) != null && MinecartRevolution.blockUtil.getSignBlockSign(minecart).getLine(1).equalsIgnoreCase("[Eject]")) {
                Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
                try {
                    Location location = entity.getLocation();
                    String[] stringLocation = sign.getLine(2).split(",");
                    location.setX(Double.valueOf(stringLocation[0]));
                    location.setY(Double.valueOf(stringLocation[1]));
                    location.setZ(Double.valueOf(stringLocation[2]));
                    minecart.eject();
                    entity.teleport(location);
                }
                catch (Exception ex) {
                    minecart.eject();
                }
            } else if (block != null) {
                BlockState State = block.getState();
                Sign sign = (Sign) State;
                if (sign.getLine(1).equalsIgnoreCase("[Eject]")) {
                    minecart.eject();
                    entity.teleport(block.getLocation());
                } else {
                    minecart.eject();
                }
            } else {
                minecart.eject();
            }
        }
    }

    public Block getSignNearLoc(Location location) {

        World world = location.getWorld();
        for (int yCounter = (int) (location.getY() - 5); yCounter <= 5 * 2 + location.getY(); yCounter++) {
            for (int xCounter = (int) (location.getX() - 5); xCounter <= 5 * 2 + location.getX(); xCounter++) {
                for (int zCounter = (int) (location.getZ() - 5); zCounter <= 5 * 2 + location.getZ(); zCounter++) {
                    if (world.getBlockAt(xCounter, yCounter, zCounter).getType() == Material.SIGN_POST) {
                        return world.getBlockAt(xCounter, yCounter, zCounter);
                    }
                }
            }
        }
        return null;
    }
}
