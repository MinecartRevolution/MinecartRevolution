//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;

public class SignWeather {

    public void execute(Minecart minecart) {

        if (MinecartRevolution.blockUtil.getSignBlockSign(minecart) != null) {
            Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);

            String weather = sign.getLine(2);
            if (weather.equalsIgnoreCase("Sun")) {
                minecart.getWorld().setStorm(false);
                minecart.getWorld().setThundering(false);
            } else if (weather.equalsIgnoreCase("Rain")) {
                minecart.getWorld().setStorm(true);
                minecart.getWorld().setThundering(false);
            } else if (weather.equalsIgnoreCase("Thunder")) {
                minecart.getWorld().setStorm(false);
                minecart.getWorld().setThundering(true);
            }
        }
    }
}