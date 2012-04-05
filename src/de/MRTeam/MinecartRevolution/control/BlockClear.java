//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import java.util.ArrayList;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.StorageMinecart;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class BlockClear {

    public void execute(Minecart minecart) {

        Sign controlSign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (controlSign == null) {
            if (minecart.getPassenger() != null && minecart.getPassenger() instanceof Player) {
                Player player = (Player) minecart.getPassenger();
                player.getInventory().clear();
            }
            if (minecart instanceof StorageMinecart) {
                StorageMinecart storageMinecart = (StorageMinecart) minecart;
                storageMinecart.getInventory().clear();
            }
        } else if (controlSign.getLine(1).equalsIgnoreCase("[InvClear]")) {
            ArrayList<String> itemList = new ArrayList<String>();
            for (String string : controlSign.getLine(2).split(",")) {
                itemList.add(string);
            }

            if (minecart.getPassenger() != null && minecart.getPassenger() instanceof Player) {
                Player player = (Player) minecart.getPassenger();
                for (int counter = 0; counter < itemList.size(); counter++) {
                    player.getInventory().remove(MinecartRevolution.itemAliasUtil.getItemId(itemList.get(counter)));
                }
            }
            if (minecart instanceof StorageMinecart) {
                StorageMinecart storageMinecart = (StorageMinecart) minecart;
                for (int counter = 0; counter < itemList.size(); counter++) {
                    storageMinecart.getInventory().remove(MinecartRevolution.itemAliasUtil.getItemId(itemList.get(counter)));
                }
            }
        }
    }
}