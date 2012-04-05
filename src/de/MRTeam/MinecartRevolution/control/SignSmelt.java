//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.inventory.ItemStack;

public class SignSmelt {

    public void execute(Minecart minecart) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign == null) {
            return;
        }

        if (minecart instanceof StorageMinecart) {
            StorageMinecart storageMinecart = (StorageMinecart) minecart;
            Furnace furnace = null;

            Location minecartLocation = minecart.getLocation();
            minecartLocation.setX(minecart.getLocation().getX() + 1.0D);
            if (minecartLocation.getBlock().getType() == Material.FURNACE || minecartLocation.getBlock().getType() == Material.BURNING_FURNACE) {
                furnace = (Furnace) minecartLocation.getBlock().getState();
            }
            minecartLocation = minecart.getLocation();
            minecartLocation.setX(minecart.getLocation().getX() - 1.0D);
            if (minecartLocation.getBlock().getType() == Material.FURNACE || minecartLocation.getBlock().getType() == Material.BURNING_FURNACE) {
                furnace = (Furnace) minecartLocation.getBlock().getState();
            }
            minecartLocation = minecart.getLocation();
            minecartLocation.setZ(minecart.getLocation().getZ() + 1.0D);
            if (minecartLocation.getBlock().getType() == Material.FURNACE || minecartLocation.getBlock().getType() == Material.BURNING_FURNACE) {
                furnace = (Furnace) minecartLocation.getBlock().getState();
            }
            minecartLocation = minecart.getLocation();
            minecartLocation.setZ(minecart.getLocation().getZ() - 1.0D);
            if (minecartLocation.getBlock().getType() == Material.FURNACE || minecartLocation.getBlock().getType() == Material.BURNING_FURNACE) {
                furnace = (Furnace) minecartLocation.getBlock().getState();
            }

            if (furnace == null) {
                return;
            }
            transferItems(furnace, storageMinecart, MinecartRevolution.itemAliasUtil.getItemId(sign.getLine(2)));
        }
    }

    private void transferItems(Furnace furnace, StorageMinecart storageMinecart, int itemId) {

        for (int counter = 0; counter < storageMinecart.getInventory().getSize(); counter++) {
            if (storageMinecart.getInventory().getItem(counter) != null && storageMinecart.getInventory().getItem(counter).getType() == Material.COAL) {
                if (furnace.getInventory().getItem(1) != null && furnace.getInventory().getItem(1).getTypeId() != storageMinecart.getInventory().getItem(counter).getTypeId()) {
                    return;
                }
                int furnaceItemAmount;
                if (furnace.getInventory().getItem(1) != null) {
                    furnaceItemAmount = furnace.getInventory().getItem(1).getAmount();
                } else {
                    furnaceItemAmount = 0;
                }
                int storageMinecartItemAmount;
                if (storageMinecart.getInventory().getItem(counter) != null) {
                    storageMinecartItemAmount = storageMinecart.getInventory().getItem(counter).getAmount();
                } else {
                    storageMinecartItemAmount = 0;
                }
                int moreItems = 0;
                if (furnaceItemAmount > storageMinecartItemAmount) {
                    moreItems = storageMinecartItemAmount;
                } else if (furnaceItemAmount < storageMinecartItemAmount) {
                    moreItems = furnaceItemAmount;
                } else if (furnaceItemAmount == storageMinecartItemAmount) {
                    moreItems = furnaceItemAmount;
                }
                if (furnaceItemAmount == 0) {
                    moreItems = storageMinecartItemAmount;
                }
                if (moreItems + furnaceItemAmount > storageMinecart.getInventory().getItem(counter).getMaxStackSize()) {
                    break;
                }
                furnace.getInventory().setItem(1, new ItemStack(storageMinecart.getInventory().getItem(counter).getTypeId(), furnaceItemAmount + moreItems));
                storageMinecart.getInventory().setItem(counter, new ItemStack(storageMinecart.getInventory().getItem(counter).getTypeId(), storageMinecart.getInventory().getItem(counter).getAmount() - moreItems));
                if (storageMinecart.getInventory().getItem(counter).getAmount() <= 0) {
                    storageMinecart.getInventory().setItem(counter, null);
                }
            }
        }

        for (int counter = 0; counter < storageMinecart.getInventory().getSize(); counter++) {
            if (storageMinecart.getInventory().getItem(counter) == null || storageMinecart.getInventory().getItem(counter).getTypeId() != itemId) {
                continue;
            }
            int furnaceItemAmount;
            if (furnace.getInventory().getItem(0) != null) {
                furnaceItemAmount = furnace.getInventory().getItem(0).getAmount();
            } else {
                furnaceItemAmount = 0;
            }
            int storageMinecartItemAmount;
            if (storageMinecart.getInventory().getItem(counter) != null) {
                storageMinecartItemAmount = storageMinecart.getInventory().getItem(counter).getAmount();
            } else {
                storageMinecartItemAmount = 0;
            }
            int moreItems = 0;
            if (furnaceItemAmount > storageMinecartItemAmount) {
                moreItems = storageMinecartItemAmount;
            } else if (furnaceItemAmount < storageMinecartItemAmount) {
                moreItems = furnaceItemAmount;
            } else if (furnaceItemAmount == storageMinecartItemAmount) {
                moreItems = furnaceItemAmount;
            }
            if (furnaceItemAmount == 0) {
                moreItems = storageMinecartItemAmount;
            }
            if (moreItems + furnaceItemAmount > storageMinecart.getInventory().getItem(counter).getMaxStackSize()) {
                break;
            }
            furnace.getInventory().setItem(0, new ItemStack(storageMinecart.getInventory().getItem(counter).getTypeId(), furnaceItemAmount + moreItems));
            storageMinecart.getInventory().setItem(counter, new ItemStack(storageMinecart.getInventory().getItem(counter).getTypeId(), storageMinecart.getInventory().getItem(counter).getAmount() - moreItems));
            if (storageMinecart.getInventory().getItem(counter).getAmount() <= 0) {
                storageMinecart.getInventory().setItem(counter, null);
            }

        }

        if (storageMinecart.getInventory().firstEmpty() >= 0 && furnace.getInventory().getItem(2) != null) {
            storageMinecart.getInventory().addItem(new ItemStack[] { furnace.getInventory().getItem(2) });
            furnace.getInventory().setItem(2, null);
        }
    }
}
