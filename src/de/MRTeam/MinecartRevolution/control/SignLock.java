//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import de.MRTeam.MinecartRevolution.util.dataStructure.FileArrayList;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class SignLock {

    public void execute(Minecart minecart) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign == null) {
            return;
        }

        if (sign.getLine(2).equalsIgnoreCase("Add") && minecart.getPassenger() != null && minecart.getPassenger() instanceof Player) {
            if (!lockList.contains( ((Player) minecart.getPassenger()).getName())) {
                lockList.add( ((Player) minecart.getPassenger()).getName());
                if (minecart.getPassenger() instanceof Player) {
                    MinecartRevolution.messagesUtil.sendMessage((Player) minecart.getPassenger(), MinecartRevolution.messagesUtil.getMessage("lockExit", ""), false);
                }
                if (MinecartRevolution.configUtil.playEffects.equalsIgnoreCase("true")) {
                    MinecartRevolution.playEffectUtil.playEffect(minecart, "Door");
                }
            }
        } else if (sign.getLine(2).equalsIgnoreCase("Remove") && minecart.getPassenger() != null && minecart.getPassenger() instanceof Player) {
            if (!lockList.contains( ((Player) minecart.getPassenger()).getName())) {
                return;
            }
            lockList.remove( ((Player) minecart.getPassenger()).getName());
            if (minecart.getPassenger() instanceof Player) {
                MinecartRevolution.messagesUtil.sendMessage((Player) minecart.getPassenger(), MinecartRevolution.messagesUtil.getMessage("unlockExit", ""), false);
            }
            if (MinecartRevolution.configUtil.playEffects.equalsIgnoreCase("true")) {
                MinecartRevolution.playEffectUtil.playEffect(minecart, "Door");
            }
        }
    }

    public void onVehicleExit(Minecart minecart, Player player, VehicleExitEvent event) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign != null && sign.getLine(1).equalsIgnoreCase("[Lock]")) {
            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("cantExitHere", ""), false);
            waitOnLock(minecart, player);
        }

        if (minecart.getPassenger() != null && minecart.getPassenger() instanceof Player && lockList.contains( ((Player) minecart.getPassenger()).getName()) && !waitingPlayersList.contains( ((Player) minecart.getPassenger()).getName())) {
            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("cantExitHere", ""), false);
            waitingPlayersList.add( ((Player) minecart.getPassenger()).getName());
            waitOnLock(minecart, player);
        }
    }

    public void waitOnLock(final Minecart minecart, final Player player) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                minecart.setPassenger(player);
                waitingPlayersList.remove( ((Player) minecart.getPassenger()).getName());
            }
        }, 5L);
    }

    public FileArrayList<String> lockList           = new FileArrayList<String>("lockList");
    private ArrayList<String>    waitingPlayersList = new ArrayList<String>();
}
