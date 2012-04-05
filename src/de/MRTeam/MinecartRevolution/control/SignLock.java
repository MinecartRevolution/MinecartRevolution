//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;

public class SignLock {

    public void execute(Minecart minecart) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign == null) {
            return;
        }

        if (sign.getLine(2).equalsIgnoreCase("Add")) {
            if (!lockList.contains(minecart.getEntityId())) {
                lockList.add(minecart.getEntityId());
                if (minecart.getPassenger() instanceof Player) {
                    MinecartRevolution.messagesUtil.sendMessage((Player) minecart.getPassenger(), MinecartRevolution.messagesUtil.getMessage("lockExit", ""), false);
                }
                if (MinecartRevolution.configUtil.playEffects.equalsIgnoreCase("true")) {
                    MinecartRevolution.playEffectUtil.playEffect(minecart, "Door");
                }
            }
        } else if (sign.getLine(2).equalsIgnoreCase("Remove")) {
            if (!lockList.contains(minecart.getEntityId())) {
                return;
            }
            lockList.remove((Object) minecart.getEntityId());
            if (minecart.getPassenger() instanceof Player) {
                MinecartRevolution.messagesUtil.sendMessage((Player) minecart.getPassenger(), MinecartRevolution.messagesUtil.getMessage("unlockExit", ""), false);
            }
            if (MinecartRevolution.configUtil.playEffects.equalsIgnoreCase("true")) {
                MinecartRevolution.playEffectUtil.playEffect(minecart, "Door");
            }
        }
    }

    public void onVehicleExit(Minecart minecart, Player player) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign != null && sign.getLine(1).equalsIgnoreCase("[Lock]")) {
            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("cantExitHere", ""), false);
            waitOnLock(minecart, player);
        }

        if (lockList.contains(minecart.getEntityId())) {
            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("cantExitHere", ""), false);
            waitOnLock(minecart, player);
        }
    }

    public void waitOnLock(final Minecart minecart, final Player player) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {

                minecart.setPassenger(player);
            }
        }, 5L);
    }

    public ArrayList<Integer> lockList = new ArrayList<Integer>();
}
