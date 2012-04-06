//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.util;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import de.MRTeam.MinecartRevolution.util.dataStructure.FileArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class FlyCartUtil {

    public FlyCartUtil(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    public void doFly(Minecart minecart) {

        if (minecart.getPassenger() != null && minecart.getPassenger() instanceof Player) {
            Player player = (Player) minecart.getPassenger();
            if (flyerList.contains(player.getName()) && !vectorWasSet.contains(player.getName())) {
                Vector vec = minecart.getVelocity();
                if (MinecartRevolution.blockUtil.getControlBlock(minecart) != null) {
                    vec.setY(vec.getY() + 1.0D);
                }
                vec.setY(vec.getY() + 2.0D);
                minecart.setVelocity(vec);
                vectorWasSet.add(player.getName());
                setFly(player, minecart);
            }
        }
    }

    public void setFly(final Player player, final Minecart minecart) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                minecart.setVelocity(player.getLocation().getDirection().multiply(2.0D));
                FlyCartUtil.this.vectorWasSet.remove(player.getName());
            }
        }, 2000L);
    }

    MinecartRevolution           plugin;

    public FileArrayList<String> flyerList    = new FileArrayList<String>("flyerList");
    public FileArrayList<String> vectorWasSet = new FileArrayList<String>("vectorWasSet");

}
