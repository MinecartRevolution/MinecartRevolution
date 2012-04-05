
package de.MRTeam.MinecartRevolution.control;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class SignCommand {

    public void execute(Minecart minecart) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign == null) {
            return;
        }

        try {
            if (commandMinecartMap.get(minecart) == null) {
                commandMinecartMap.put(minecart, false);
            }
        }
        catch (NullPointerException ex) {
        }

        if (!this.commandMinecartMap.get(minecart)) {
            commandMinecartMap.remove(minecart);
            commandMinecartMap.put(minecart, true);

            String command = (sign.getLine(1) + sign.getLine(2));

            if (minecart.getPassenger() instanceof Player) {
                Player player = (Player) minecart.getPassenger();
                command.replaceAll("%player%", player.getName());
            }

            if (sign.getLine(3).equalsIgnoreCase("Player")) {
                if (minecart.getPassenger() != null) {
	if (minecart.getPassenger() instanceof Player) {
	    Player player = (Player) minecart.getPassenger();
	    player.performCommand(command);
	}

                }
            } else if (sign.getLine(3).equalsIgnoreCase("Console")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }
        }
    }

    public void reset(Minecart minecart) {

        try {
            if (commandMinecartMap.get(minecart) == null) {
                commandMinecartMap.put(minecart, false);
            }
        }
        catch (NullPointerException ex) {
        }
        commandMinecartMap.remove(minecart);
        commandMinecartMap.put(minecart, false);
    }

    HashMap<Minecart, Boolean> commandMinecartMap = new HashMap<Minecart, Boolean>();
}
