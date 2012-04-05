//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;

public class BlockReverse {

    public void execute(Minecart minecart) {

        Vector nowSpeed = minecart.getVelocity();

        nowSpeed.setX(-nowSpeed.getX());
        nowSpeed.setZ(-nowSpeed.getZ());

        minecart.setVelocity(nowSpeed.clone());
    }
}
