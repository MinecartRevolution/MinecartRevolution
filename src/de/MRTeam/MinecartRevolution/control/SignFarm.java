//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import java.util.HashMap;
import de.MRTeam.MinecartRevolution.MinecartRevolution;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.inventory.ItemStack;

public class SignFarm {

    public void execute(Minecart minecart) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign == null) {
            return;
        }

        if (minecart instanceof StorageMinecart) {
            StorageMinecart storageMinecart = (StorageMinecart) minecart;

            if (sign.getLine(3).equalsIgnoreCase("Add")) {
                if (!farmTypeMap.containsKey(minecart)) {
                    farmTypeMap.put(minecart, sign.getLine(1));
                    farmRadiusMap.put(minecart, sign.getLine(2));
                }
            } else if (sign.getLine(1).equalsIgnoreCase("Remove") || sign.getLine(2).equalsIgnoreCase("Remove") || sign.getLine(3).equalsIgnoreCase("Remove")) {
                if (farmTypeMap.containsKey(minecart)) {
                    farmTypeMap.remove(minecart);
                    farmRadiusMap.remove(minecart);
                }
            } else {
                try {
                    farm(storageMinecart, sign.getLine(1), Integer.parseInt(sign.getLine(2)));
                }
                catch (NumberFormatException ex) {
                }
            }
        }
    }

    public void farm(StorageMinecart storageMinecart, String type, int radius) {

        if (type.equalsIgnoreCase("Wood")) {
            int[] destroyIds = { 17 };
            int[] collectIds = { 17, 6 };
            int plantId = 6;
            for (int counter = 0; counter < destroyIds.length; counter++) {
                int destroyId = destroyIds[counter];
                World world = storageMinecart.getWorld();
                Location location = storageMinecart.getLocation().getBlock().getLocation();

                for (int yCounter = (int) (location.getY() - radius); yCounter <= radius * 2 + location.getY(); yCounter++) {
                    for (int xCounter = (int) (location.getX() - radius); xCounter <= radius * 2 + location.getX(); xCounter++) {
                        for (int zCounter = (int) (location.getZ() - radius); zCounter <= radius * 2 + location.getZ(); zCounter++) {
                            if (world.getBlockTypeIdAt(xCounter, yCounter, zCounter) == destroyId) {
	world.getBlockAt(xCounter, yCounter, zCounter).breakNaturally();
	if (world.getBlockTypeIdAt(xCounter, yCounter - 1, zCounter) != 0) {
	    for (int storageCounter = 0; storageCounter < storageMinecart.getInventory().getSize(); storageCounter++) {
	        if ( (storageMinecart.getInventory().getItem(storageCounter) != null) && (storageMinecart.getInventory().getItem(storageCounter).getTypeId() == plantId)) {
	            ItemStack newItemStack = new ItemStack(storageMinecart.getInventory().getItem(storageCounter));
	            newItemStack.setAmount(storageMinecart.getInventory().getItem(storageCounter).getAmount() - 1);
	            storageMinecart.getInventory().setItem(storageCounter, newItemStack);
	            if (storageMinecart.getInventory().getItem(storageCounter).getAmount() <= 0) {
	                storageMinecart.getInventory().setItem(storageCounter, null);
	            }
	            world.getBlockAt(xCounter, yCounter, zCounter).setTypeId(plantId);
	        }
	    }
	}
                            }
                        }
                    }
                }
            }
            for (int counter = 0; counter < collectIds.length; counter++) {
                collectItems(storageMinecart, radius, collectIds[counter]);
            }
        } else if (type.equalsIgnoreCase("Wheat")) {
            int[] destroyIds = { 59 };
            int[] collectIds = { 295, 296 };
            int plantItemId = 295;
            int plantBlockId = 59;
            for (int counter = 0; counter < destroyIds.length; counter++) {
                int destroyId = destroyIds[counter];
                World world = storageMinecart.getWorld();
                Location location = storageMinecart.getLocation().getBlock().getLocation();

                for (int yCounter = (int) (location.getY() - radius); yCounter <= radius * 2 + location.getY(); yCounter++) {
                    for (int xCounter = (int) (location.getX() - radius); xCounter <= radius * 2 + location.getX(); xCounter++) {
                        for (int zCounter = (int) (location.getZ() - radius); zCounter <= radius * 2 + location.getZ(); zCounter++) {
                            if (world.getBlockTypeIdAt(xCounter, yCounter, zCounter) == destroyId && world.getBlockAt(xCounter, yCounter, zCounter).getData() == 7) {
	world.getBlockAt(xCounter, yCounter, zCounter).breakNaturally();
	for (int storageCounter = 0; storageCounter < storageMinecart.getInventory().getSize(); storageCounter++) {
	    if (storageMinecart.getInventory().getItem(storageCounter) != null && storageMinecart.getInventory().getItem(storageCounter).getTypeId() == plantItemId) {
	        ItemStack newItemStack = new ItemStack(storageMinecart.getInventory().getItem(storageCounter));
	        newItemStack.setAmount(storageMinecart.getInventory().getItem(storageCounter).getAmount() - 1);
	        storageMinecart.getInventory().setItem(storageCounter, newItemStack);
	        if (storageMinecart.getInventory().getItem(storageCounter).getAmount() <= 0) {
	            storageMinecart.getInventory().setItem(storageCounter, null);
	        }
	        world.getBlockAt(xCounter, yCounter, zCounter).setTypeId(plantBlockId);
	    }
	}
                            }
                        }
                    }
                }
            }
            for (int counter = 0; counter < collectIds.length; counter++) {
                collectItems(storageMinecart, radius, collectIds[counter]);
            }
        } else if (type.equalsIgnoreCase("Pumpkin")) {
            int destroyId = 86;
            int collectId = 86;
            World world = storageMinecart.getWorld();
            Location location = storageMinecart.getLocation().getBlock().getLocation();

            for (int yCounter = (int) (location.getY() - radius); yCounter <= radius * 2 + location.getY(); yCounter++) {
                for (int xCounter = (int) (location.getX() - radius); xCounter <= radius * 2 + location.getX(); xCounter++) {
                    for (int zCounter = (int) (location.getZ() - radius); zCounter <= radius * 2 + location.getZ(); zCounter++) {
                        if (world.getBlockTypeIdAt(xCounter, yCounter, zCounter) == destroyId) {
                            world.getBlockAt(xCounter, yCounter, zCounter).breakNaturally();
                        }
                    }
                }
            }
            collectItems(storageMinecart, radius, collectId);
        } else if (type.equalsIgnoreCase("Melon")) {
            int destroyId = 103;
            int collectId = 103;
            World world = storageMinecart.getWorld();
            Location location = storageMinecart.getLocation().getBlock().getLocation();

            for (int yCounter = (int) (location.getY() - radius); yCounter <= radius * 2 + location.getY(); yCounter++) {
                for (int xCounter = (int) (location.getX() - radius); xCounter <= radius * 2 + location.getX(); xCounter++) {
                    for (int zCounter = (int) (location.getZ() - radius); zCounter <= radius * 2 + location.getZ(); zCounter++) {
                        if (world.getBlockTypeIdAt(xCounter, yCounter, zCounter) == destroyId) {
                            world.getBlockAt(xCounter, yCounter, zCounter).breakNaturally();
                        }
                    }
                }
            }
            collectItems(storageMinecart, radius, collectId);
        } else if (type.equalsIgnoreCase("SugarCane")) {
            int[] destroyIds = { 83 };
            int[] collectIds = { 338 };
            for (int counter = 0; counter < destroyIds.length; counter++) {
                int destroyId = destroyIds[counter];
                World world = storageMinecart.getWorld();
                Location location = storageMinecart.getLocation().getBlock().getLocation();

                for (int yCounter = (int) (location.getY() - radius); yCounter <= radius * 2 + location.getY(); yCounter++) {
                    for (int xCounter = (int) (location.getX() - radius); xCounter <= radius * 2 + location.getX(); xCounter++) {
                        for (int zCounter = (int) (location.getZ() - radius); zCounter <= radius * 2 + location.getZ(); zCounter++) {
                            if (world.getBlockTypeIdAt(xCounter, yCounter, zCounter) == destroyId && (world.getBlockTypeIdAt(xCounter, yCounter - 1, zCounter) == destroyId)) {
	world.getBlockAt(xCounter, yCounter, zCounter).breakNaturally();
                            }
                        }
                    }
                }
            }
            for (int counter = 0; counter < collectIds.length; counter++) {
                collectItems(storageMinecart, radius, collectIds[counter]);
            }
        }
    }

    private void collectItems(StorageMinecart storageMinecart, int radius, int collectId) {

        if (storageMinecart.getInventory().firstEmpty() < 0) {
            return;
        }

        for (Entity entity : storageMinecart.getNearbyEntities(radius, radius, radius)) {
            if (entity instanceof Item) {
                Item item = (Item) entity;
                if (item.isDead()) {
                    continue;
                }
                if (item.getItemStack().getTypeId() != collectId) {
                    continue;
                }
                storageMinecart.getInventory().addItem(new ItemStack[] { item.getItemStack() });
                item.remove();
            }
        }
    }

    public HashMap<Minecart, String> farmTypeMap   = new HashMap<Minecart, String>();
    public HashMap<Minecart, String> farmRadiusMap = new HashMap<Minecart, String>();
}
