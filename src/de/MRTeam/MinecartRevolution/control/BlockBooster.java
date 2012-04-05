//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.entity.Minecart;

public class BlockBooster {

    public void execute(Minecart minecart) {

        MinecartRevolution.minecartListener.boostMinecart(minecart, true);
    }
}
