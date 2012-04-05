//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.server.CraftingManager;
import net.minecraft.server.ShapedRecipes;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class SignCraft {

    public void execute(Minecart minecart) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign == null) {
            return;
        }

        if (minecart instanceof StorageMinecart) {
            ArrayList<String> signShape = new ArrayList<String>();
            signShape = addStringArrayToArrayList(signShape, sign.getLine(1).split(","));
            signShape = addStringArrayToArrayList(signShape, sign.getLine(2).split(","));
            signShape = addStringArrayToArrayList(signShape, sign.getLine(3).split(","));
            if (signShape.size() != 9) {
                return;
            }

            StorageMinecart storageMinecart = (StorageMinecart) minecart;

            @SuppressWarnings ("unchecked")
            Iterator<ShapedRecipes> iterator = CraftingManager.getInstance().recipies.iterator();

            try {
                while (iterator.hasNext()) {
                    ShapedRecipe recipe = iterator.next().toBukkitRecipe();
                    Map<Character, ItemStack> ingredientMap = recipe.getIngredientMap();
                    ArrayList<String> shapeList = new ArrayList<String>();
                    for (int counter = 0; counter < 9; counter++) {
                        shapeList.add("0");
                    }
                    for (Map.Entry<Character, ItemStack> entry : ingredientMap.entrySet()) {
                        Character key = entry.getKey();
                        ItemStack itemstack = entry.getValue();
                        if (key == 'a') {
                            shapeList = addCraftingIngredient(shapeList, 0, itemstack);
                        } else if (key == 'b') {
                            shapeList = addCraftingIngredient(shapeList, 1, itemstack);
                        } else if (key == 'c') {
                            shapeList = addCraftingIngredient(shapeList, 2, itemstack);
                        } else if (key == 'd') {
                            shapeList = addCraftingIngredient(shapeList, 3, itemstack);
                        } else if (key == 'e') {
                            shapeList = addCraftingIngredient(shapeList, 4, itemstack);
                        } else if (key == 'f') {
                            shapeList = addCraftingIngredient(shapeList, 5, itemstack);
                        } else if (key == 'g') {
                            shapeList = addCraftingIngredient(shapeList, 6, itemstack);
                        } else if (key == 'h') {
                            shapeList = addCraftingIngredient(shapeList, 7, itemstack);
                        } else if (key == 'i') {
                            shapeList = addCraftingIngredient(shapeList, 8, itemstack);
                        }
                    }

                    boolean canCraft = true;
                    for (int counter = 0; counter < shapeList.size(); counter++) {
                        if (!shapeList.get(counter).equalsIgnoreCase(String.valueOf(MinecartRevolution.itemAliasUtil.getItemId(signShape.get(counter))))) {
                            canCraft = false;
                        }
                    }
                    if (canCraft) {
                        craft(storageMinecart, shapeList, recipe.getResult());
                    }
                }
            }
            catch (ClassCastException ex) {
            }
        }
    }

    private ArrayList<String> addStringArrayToArrayList(ArrayList<String> arrayList, String[] array) {

        for (int counter = 0; counter < array.length; counter++) {
            arrayList.add(array[counter]);
        }

        return arrayList;
    }

    private ArrayList<String> addCraftingIngredient(ArrayList<String> arrayList, int slot, ItemStack itemstack) {

        if (itemstack == null) {
            arrayList.set(slot, "0");
        } else {
            arrayList.set(slot, String.valueOf(itemstack.getTypeId()));
        }

        return arrayList;
    }

    private void craft(StorageMinecart storageMinecart, ArrayList<String> recipeShape, ItemStack result) {

        try {
            HashMap<String, Integer> amountMap = new HashMap<String, Integer>();
            for (int shapeCounter = 0; shapeCounter < recipeShape.size(); shapeCounter++) {
                if (!recipeShape.get(shapeCounter).equalsIgnoreCase("0")) {
                    if (amountMap.containsKey(recipeShape.get(shapeCounter))) {
                        int oldAmount = amountMap.get(recipeShape.get(shapeCounter));
                        amountMap.remove(recipeShape.get(shapeCounter));
                        amountMap.put(recipeShape.get(shapeCounter), oldAmount + 1);
                    } else {
                        amountMap.put(recipeShape.get(shapeCounter), 1);
                    }
                }
            }

            while (true) {
                for (Entry<String, Integer> entry : amountMap.entrySet()) {
                    String item = entry.getKey();
                    Integer amount = entry.getValue();
                    if (getSlot(storageMinecart, Integer.parseInt(item), amount) < 0) {
                        return;
                    }
                    if (! (storageMinecart.getInventory().getItem(getSlot(storageMinecart, Integer.parseInt(item), amount)).getAmount() >= amount)) {
                        return;
                    }
                }

                for (Entry<String, Integer> entry : amountMap.entrySet()) {
                    String item = entry.getKey();
                    Integer amount = entry.getValue();
                    ItemStack itemstack = storageMinecart.getInventory().getItem(getSlot(storageMinecart, Integer.parseInt(item), amount));
                    if (itemstack.getAmount() == amount) {
                        storageMinecart.getInventory().setItem(getSlot(storageMinecart, Integer.parseInt(item), amount), null);
                    } else {
                        itemstack.setAmount(itemstack.getAmount() - amount);
                    }
                }

                storageMinecart.getInventory().addItem(result);
            }
        }
        catch (NumberFormatException ex) {
        }
    }

    private int getSlot(StorageMinecart storageMinecart, int item, int amount) {

        int slot = -1;

        for (int counter = 0; counter < storageMinecart.getInventory().getSize(); counter++) {
            if (storageMinecart.getInventory().getItem(counter) != null && storageMinecart.getInventory().getItem(counter).getTypeId() == item && storageMinecart.getInventory().getItem(counter).getAmount() >= amount) {
                return counter;
            }
        }

        return slot;
    }
}
