//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import java.util.HashMap;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class SignAnnounce {

    public void execute(Minecart minecart) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign == null) {
            return;
        }

        if (minecart.getPassenger() instanceof Player) {
            Player player = (Player) minecart.getPassenger();
            try {
                if (announcePlayerMap.get(player) == null) {
                    announcePlayerMap.put(player, false);
                }
            }
            catch (NullPointerException ex) {
            }

            if (!announcePlayerMap.get(player)) {
                player.sendMessage( (sign.getLine(1) + " " + sign.getLine(2) + " " + sign.getLine(3)).replaceAll("(?i)&([a-f0-9])", "\u00A7$1"));
                announcePlayerMap.remove(player);
                announcePlayerMap.put(player, true);
            }
        }
    }

    public void reset(Minecart minecart) {

        if (minecart.getPassenger() instanceof Player) {
            Player player = (Player) minecart.getPassenger();
            try {
                if (announcePlayerMap.get(player) == null) {
                    announcePlayerMap.put(player, false);
                }
            }
            catch (NullPointerException ex) {
            }
            announcePlayerMap.remove(player);
            announcePlayerMap.put(player, false);
        }
    }

    HashMap<Player, Boolean> announcePlayerMap = new HashMap<Player, Boolean>();

}
