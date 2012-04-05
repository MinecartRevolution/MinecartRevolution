//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.inventory.ItemStack;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class SignCollect {

    public void execute(Minecart minecart) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign == null) {
            return;
        }

        if (minecart instanceof StorageMinecart) {
            StorageMinecart storageMinecart = (StorageMinecart) minecart;

            if (sign.getLine(1).equalsIgnoreCase("allitems")) {
                try {
                    collectAllItems(storageMinecart, Integer.parseInt(sign.getLine(2)));
                }
                catch (NumberFormatException ex) {
                }
            } else {
                String[] collectIds = sign.getLine(1).split(",");
                try {
                    if (collectIds != null) {
                        for (int counter = 0; counter < collectIds.length; counter++) {
                            collectItems(storageMinecart, collectIds[counter], MinecartRevolution.itemAliasUtil.getItemId(sign.getLine(2)));
                        }
                    }
                }
                catch (NumberFormatException ex) {
                }
            }
        }
    }

    private void collectItems(StorageMinecart storageMinecart, String itemId, int radius) {

        if (storageMinecart.getInventory().firstEmpty() < 0) {
            return;
        }

        for (Entity entity : storageMinecart.getNearbyEntities(radius, radius, radius)) {
            if (entity instanceof Item) {
                Item item = (Item) entity;
                if (item.isDead()) {
                    continue;
                }
                if (item.getItemStack().getTypeId() != Integer.parseInt(itemId)) {
                    continue;
                }
                storageMinecart.getInventory().addItem(new ItemStack[] { item.getItemStack() });
                item.remove();
            }
        }
    }

    private void collectAllItems(StorageMinecart storageMinecart, int radius) {

        if (storageMinecart.getInventory().firstEmpty() < 0) {
            return;
        }

        for (Entity entity : storageMinecart.getNearbyEntities(radius, radius, radius)) {
            if (entity instanceof Item) {
                Item item = (Item) entity;
                if (item.isDead()) {
                    continue;
                }
                storageMinecart.getInventory().addItem(new ItemStack[] { item.getItemStack() });
                item.remove();
            }
        }
    }
}
