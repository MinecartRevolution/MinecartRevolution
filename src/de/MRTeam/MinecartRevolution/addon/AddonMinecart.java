
package de.MRTeam.MinecartRevolution.addon;

import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;

public class AddonMinecart {

    public double getMinecartSpeed(Minecart minecart) {

        if (minecart.getVelocity().getX() > 0) {
            return minecart.getVelocity().getX();
        } else if (minecart.getVelocity().getX() < 0) {
            return -minecart.getVelocity().getX();
        } else if (minecart.getVelocity().getZ() > 0) {
            return minecart.getVelocity().getZ();
        } else if (minecart.getVelocity().getZ() < 0) {
            return -minecart.getVelocity().getZ();
        } else {
            return 0;
        }
    }

    public void setMinecartSpeed(Minecart minecart, double speed) {

        if (minecart.getVelocity().getX() > 0) {
            minecart.setVelocity(new Vector(speed, 0, 0));
        } else if (minecart.getVelocity().getX() < 0) {
            minecart.setVelocity(new Vector(-speed, 0, 0));
        } else if (minecart.getVelocity().getZ() > 0) {
            minecart.setVelocity(new Vector(0, 0, speed));
        } else if (minecart.getVelocity().getZ() < 0) {
            minecart.setVelocity(new Vector(0, 0, -speed));
        }
    }

    public String getMinecartDirection(Minecart minecart) {

        if (minecart.getVelocity().getX() > 0) {
            return "x+";
        } else if (minecart.getVelocity().getX() < 0) {
            return "x-";
        } else if (minecart.getVelocity().getZ() > 0) {
            return "z+";
        } else if (minecart.getVelocity().getZ() < 0) {
            return "z-";
        } else {
            return null;
        }
    }

}
