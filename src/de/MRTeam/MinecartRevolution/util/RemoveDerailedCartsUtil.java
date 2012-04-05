
package de.MRTeam.MinecartRevolution.util;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.entity.Minecart;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class RemoveDerailedCartsUtil {

    public void execute(Minecart minecart) {

        if (time <= 0) {
            return;
        }

        if (!alreadyExecuted.containsKey(minecart)) {
            startTimer(minecart);
            alreadyExecuted.put(minecart, minecart);
        }

    }

    private void removeMinecart(Minecart minecart) {

        if (!MinecartRevolution.minecartListener.isMinecartOnRail(minecart) && !minecart.isDead()) {
            minecart.remove();
            alreadyExecuted.remove(minecart);
        }
    }

    private void startTimer(final Minecart minecart) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {

                removeMinecart(minecart);
            }

        }, 1000 * time);
    }

    public boolean checkForDerailedCart(Minecart minecart) {

        if (MinecartRevolution.minecartListener.isMinecartOnRail(minecart)) {
            return true;
        }
        return false;
    }

    int	time            = MinecartRevolution.configUtil.removeMinecartWhenDerailedTime;
    public HashMap<Minecart, Minecart> alreadyExecuted = new HashMap<Minecart, Minecart>();

}
