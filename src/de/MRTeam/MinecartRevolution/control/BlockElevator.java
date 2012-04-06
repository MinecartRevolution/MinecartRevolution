//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class BlockElevator {

    public void execute(Minecart minecart) {

        int heightCounter = 0;

        while (heightCounter <= 265) {
            heightCounter++;

            Block block = minecart.getWorld().getBlockAt(minecart.getLocation().getBlockX(), minecart.getLocation().getBlockY() + heightCounter, minecart.getLocation().getBlockZ());
            if (block.getTypeId() == MinecartRevolution.configUtil.elevatorBlockId[0] && (block.getData() == MinecartRevolution.configUtil.elevatorBlockId[1] || MinecartRevolution.configUtil.elevatorBlockId[1] == -1)) {
                Vector velocity = minecart.getVelocity();
                if (velocity.getX() > 0) {
                    minecart.teleport(block.getLocation().add(1, 1, 0));
                } else if (velocity.getX() < 0) {
                    minecart.teleport(block.getLocation().add(0, 1, 0).subtract(1, 0, 0));
                } else if (velocity.getZ() > 0) {
                    minecart.teleport(block.getLocation().add(0, 1, 1));
                } else if (velocity.getZ() < 0) {
                    minecart.teleport(block.getLocation().add(0, 1, 0).subtract(0, 0, 1));
                }
                minecart.setVelocity(velocity);
                return;
            }

            block = minecart.getWorld().getBlockAt(minecart.getLocation().getBlockX(), minecart.getLocation().getBlockY() - heightCounter - 2, minecart.getLocation().getBlockZ());
            if (block.getTypeId() == MinecartRevolution.configUtil.elevatorBlockId[0] && (block.getData() == MinecartRevolution.configUtil.elevatorBlockId[1] || MinecartRevolution.configUtil.elevatorBlockId[1] == -1)) {
                Vector velocity = minecart.getVelocity();
                if (velocity.getX() > 0) {
                    minecart.teleport(block.getLocation().add(1, 1, 0));
                } else if (velocity.getX() < 0) {
                    minecart.teleport(block.getLocation().add(0, 1, 0).subtract(1, 0, 0));
                } else if (velocity.getZ() > 0) {
                    minecart.teleport(block.getLocation().add(0, 1, 1));
                } else if (velocity.getZ() < 0) {
                    minecart.teleport(block.getLocation().add(0, 1, 0).subtract(0, 0, 1));
                }
                minecart.setVelocity(velocity);
                return;
            }
        }
    }
}
