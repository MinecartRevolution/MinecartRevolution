//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;

public class BlockHeal {

    public void execute(Minecart minecart) {

        if (minecart.getPassenger() instanceof Player) {
            Player player = (Player) minecart.getPassenger();
            player.setHealth(20);
            player.setFoodLevel(20);
        }
    }
}
