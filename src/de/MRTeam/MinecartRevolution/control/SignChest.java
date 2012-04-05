//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import java.util.ArrayList;
import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.inventory.ItemStack;

public class SignChest {

    public void execute(Minecart minecart) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign == null) {
            return;
        }

        Chest chest = null;

        Location minecartLocation = minecart.getLocation();
        minecartLocation.setX(minecart.getLocation().getX() + 1.0D);
        if (minecartLocation.getBlock().getType() == Material.CHEST) {
            chest = (Chest) minecartLocation.getBlock().getState();
        }
        minecartLocation = minecart.getLocation();
        minecartLocation.setX(minecart.getLocation().getX() - 1.0D);
        if (minecartLocation.getBlock().getType() == Material.CHEST) {
            chest = (Chest) minecartLocation.getBlock().getState();
        }
        minecartLocation = minecart.getLocation();
        minecartLocation.setZ(minecart.getLocation().getZ() + 1.0D);
        if (minecartLocation.getBlock().getType() == Material.CHEST) {
            chest = (Chest) minecartLocation.getBlock().getState();
        }
        minecartLocation = minecart.getLocation();
        minecartLocation.setZ(minecart.getLocation().getZ() - 1.0D);
        if (minecartLocation.getBlock().getType() == Material.CHEST) {
            chest = (Chest) minecartLocation.getBlock().getState();
        }

        if (chest == null) {
            return;
        }

        if (minecart instanceof StorageMinecart) {
            StorageMinecart storageMinecart = (StorageMinecart) minecart;

            if (sign.getLine(1).equalsIgnoreCase("allitems")) {
                transferAllItems(chest, storageMinecart, "STORAGE_MINECART>CHEST");
            }
            if (sign.getLine(2).equalsIgnoreCase("allitems")) {
                transferAllItems(chest, storageMinecart, "CHEST>STORAGE_MINECART");
            }
            try {
                ArrayList<String> collectIds = new ArrayList<String>();
                ArrayList<String> depositIds = new ArrayList<String>();

                if (sign.getLine(1).startsWith("-")) {
                    if (sign.getLine(1).contains("allitems")) {
                        transferAllItems(chest, storageMinecart, "STORAGE_MINECART>CHEST");
                    }
                    collectIds = addStringArrayToArrayList(collectIds, sign.getLine(1).replaceAll("\\-", "").trim().split(","));
                } else if (sign.getLine(1).startsWith("+")) {
                    if (sign.getLine(1).contains("allitems")) {
                        transferAllItems(chest, storageMinecart, "CHEST>STORAGE_MINECART");
                    }
                    depositIds = addStringArrayToArrayList(depositIds, sign.getLine(1).replaceAll("\\+", "").trim().split(","));
                }
                if (sign.getLine(2).startsWith("-")) {
                    if (sign.getLine(2).contains("allitems")) {
                        transferAllItems(chest, storageMinecart, "STORAGE_MINECART>CHEST");
                    }
                    collectIds = addStringArrayToArrayList(collectIds, sign.getLine(2).replaceAll("\\-", "").trim().split(","));
                } else if (sign.getLine(2).startsWith("+")) {
                    if (sign.getLine(2).contains("allitems")) {
                        transferAllItems(chest, storageMinecart, "CHEST>STORAGE_MINECART");
                    }
                    depositIds = addStringArrayToArrayList(depositIds, sign.getLine(2).replaceAll("\\+", "").trim().split(","));
                }
                if (sign.getLine(3).startsWith("-")) {
                    if (sign.getLine(3).contains("allitems")) {
                        transferAllItems(chest, storageMinecart, "STORAGE_MINECART>CHEST");
                    }
                    collectIds = addStringArrayToArrayList(collectIds, sign.getLine(3).replaceAll("\\-", "").trim().split(","));
                } else if (sign.getLine(3).startsWith("+")) {
                    if (sign.getLine(3).contains("allitems")) {
                        transferAllItems(chest, storageMinecart, "CHEST>STORAGE_MINECART");
                    }
                    depositIds = addStringArrayToArrayList(depositIds, sign.getLine(3).replaceAll("\\+", "").trim().split(","));
                }

                if (collectIds != null) {
                    for (int counter = 0; counter < collectIds.size(); counter++) {
                        transferItems(chest, storageMinecart, MinecartRevolution.itemAliasUtil.getItemId(collectIds.get(counter)), "STORAGE_MINECART>CHEST");
                    }
                }

                if (depositIds != null) {
                    for (int counter = 0; counter < depositIds.size(); counter++) {
                        transferItems(chest, storageMinecart, MinecartRevolution.itemAliasUtil.getItemId(depositIds.get(counter)), "CHEST>STORAGE_MINECART");
                    }
                }
            }
            catch (NumberFormatException ex) {
            }
        }
    }

    private ArrayList<String> addStringArrayToArrayList(ArrayList<String> arrayList, String[] array) {

        for (int counter = 0; counter < array.length; counter++) {
            arrayList.add(array[counter]);
        }

        return arrayList;
    }

    private void transferItems(Chest chest, StorageMinecart storageMinecart, int itemId, String direction) {

        if (chest.getInventory().firstEmpty() < 0 || storageMinecart.getInventory().firstEmpty() < 0) {
            return;
        }

        if (direction.equalsIgnoreCase("STORAGE_MINECART>CHEST")) {
            for (int counter = 0; counter < chest.getInventory().getSize(); counter++) {
                if (chest.getInventory().getItem(counter) != null && chest.getInventory().getItem(counter).getTypeId() == itemId) {
                    storageMinecart.getInventory().addItem(new ItemStack[] { chest.getInventory().getItem(counter) });
                    chest.getInventory().setItem(counter, null);
                }
            }
        } else if (direction.equalsIgnoreCase("CHEST>STORAGE_MINECART")) {
            for (int counter = 0; counter < storageMinecart.getInventory().getSize(); counter++) {
                if (storageMinecart.getInventory().getItem(counter) != null && storageMinecart.getInventory().getItem(counter).getTypeId() == itemId) {
                    chest.getInventory().addItem(new ItemStack[] { storageMinecart.getInventory().getItem(counter) });
                    storageMinecart.getInventory().setItem(counter, null);
                }
            }
        }
    }

    private void transferAllItems(Chest chest, StorageMinecart storageMinecart, String direction) {

        if (chest.getInventory().firstEmpty() < 0 || storageMinecart.getInventory().firstEmpty() < 0) {
            return;
        }

        if (direction.equalsIgnoreCase("STORAGE_MINECART>CHEST")) {
            for (int counter = 0; counter < chest.getInventory().getSize(); counter++) {
                if (chest.getInventory().getItem(counter) != null) {
                    storageMinecart.getInventory().addItem(new ItemStack[] { chest.getInventory().getItem(counter) });
                    chest.getInventory().setItem(counter, null);
                }
            }
        } else if (direction.equalsIgnoreCase("CHEST>STORAGE_MINECART")) {
            for (int counter = 0; counter < storageMinecart.getInventory().getSize(); counter++) {
                if (storageMinecart.getInventory().getItem(counter) != null) {
                    chest.getInventory().addItem(new ItemStack[] { storageMinecart.getInventory().getItem(counter) });
                    storageMinecart.getInventory().setItem(counter, null);
                }
            }
        }
    }
}
