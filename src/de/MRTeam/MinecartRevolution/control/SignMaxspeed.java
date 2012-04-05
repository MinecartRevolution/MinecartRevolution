//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;

public class SignMaxspeed {

    public void execute(Minecart minecart) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign == null) {
            return;
        }

        if (sign.getLine(2).equalsIgnoreCase("reset")) {
            minecart.setMaxSpeed(0.4D);
        } else {
            try {
                double newMaxSpeedProcent = Integer.parseInt(sign.getLine(2).replaceAll("%", "").replaceAll(" ", ""));
                if (newMaxSpeedProcent > 0.0D) {
                    newMaxSpeedProcent /= 100;
                    double newMaxSpeed = newMaxSpeedProcent * 0.4D;
                    minecart.setMaxSpeed(newMaxSpeed);
                    MinecartRevolution.minecartListener.boostMinecartWithSpeed(minecart, minecart.getMaxSpeed());
                }
            }
            catch (NumberFormatException localNumberFormatException) {
            }
        }
    }
}
