//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;

public class BlockKill {

    public void execute(Minecart minecart) {

        if (MinecartRevolution.blockUtil.getSignBlockSign(minecart) != null) {
            BlockState State = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
            if (State instanceof Sign) {
                Sign sign = (Sign) State;
                if (sign.getLine(1).equalsIgnoreCase("[Kill]") && sign.getLine(2) != null && sign.getLine(2).equalsIgnoreCase("EmptyCarts") && minecart.getPassenger() == null) {
                    minecart.remove();
                    if (MinecartRevolution.configUtil.playEffects.equalsIgnoreCase("true")) {
                        MinecartRevolution.playEffectUtil.playEffect(minecart, "Smoke");
                    }
                }
            }
        } else {
            minecart.eject();
            minecart.remove();
            if (MinecartRevolution.configUtil.playEffects.equalsIgnoreCase("true")) {
                MinecartRevolution.playEffectUtil.playEffect(minecart, "Smoke");
            }
        }
    }
}
