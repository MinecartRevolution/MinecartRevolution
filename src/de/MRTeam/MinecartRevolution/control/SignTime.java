//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;

public class SignTime {

    public void execute(Minecart minecart) {

        if (MinecartRevolution.blockUtil.getSignBlockSign(minecart) != null) {
            Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);

            if (sign.getLine(2).equalsIgnoreCase("Day")) {
                minecart.getWorld().setTime(0L);
            } else if (sign.getLine(2).equalsIgnoreCase("Night")) {
                minecart.getWorld().setTime(16000L);
            } else {
                try {
                    int time = Integer.parseInt(sign.getLine(2));
                    minecart.getWorld().setTime(time);
                }
                catch (NumberFormatException localNumberFormatException) {
                }
            }
        }
    }
}
