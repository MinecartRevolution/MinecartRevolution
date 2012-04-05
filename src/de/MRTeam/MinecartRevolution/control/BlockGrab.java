//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;

public class BlockGrab {

    public void execute(Minecart minecart) {

        int radius = MinecartRevolution.configUtil.grabBlockRadius;
        if (MinecartRevolution.blockUtil.getSignBlockSign(minecart) != null) {
            Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
            if (sign.getLine(2) != null) {
                try {
	radius = Integer.parseInt(sign.getLine(2));
                }
                catch (Exception localException) {
                }
            }
        }

        if (minecart.getPassenger() == null) {
            for (Entity entity : minecart.getNearbyEntities(radius, radius, radius)) {
                if (entity instanceof Player && minecart.getPassenger() == null && entity.getVehicle() == null) {
	minecart.setPassenger(entity);
	break;
                }
            }
            if (MinecartRevolution.configUtil.playEffects.equalsIgnoreCase("true")) {
                MinecartRevolution.playEffectUtil.playEffect(minecart, "Grab");
            }
        }
    }
}
