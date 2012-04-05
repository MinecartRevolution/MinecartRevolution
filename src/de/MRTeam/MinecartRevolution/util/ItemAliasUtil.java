
package de.MRTeam.MinecartRevolution.util;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class ItemAliasUtil {

    public ItemAliasUtil(MinecartRevolution plugin) {

        this.plugin = plugin;

        loadAliases();
    }

    public int getItemId(String alias) {

        if (alias.matches("[0-9]+")) {
            return Integer.parseInt(alias);
        }
        File aliasFile = new File(this.plugin.getDataFolder(), "aliases.yml");
        FileConfiguration aliases = YamlConfiguration.loadConfiguration(aliasFile);

        try {
            return aliases.getInt("aliases." + alias);
        }
        catch (Exception ex) {
            return 0;
        }
    }

    public void loadAliases() {

        File aliasFile = new File(this.plugin.getDataFolder(), "aliases.yml");
        FileConfiguration aliases = YamlConfiguration.loadConfiguration(aliasFile);

        LinkedHashMap<String, Integer> aliasMap = new LinkedHashMap<String, Integer>();

        aliasMap.put("stone", 1);
        aliasMap.put("sstone", 1);
        aliasMap.put("smoothstone", 1);
        aliasMap.put("rock", 1);
        aliasMap.put("grass", 2);
        aliasMap.put("dirt", 3);
        aliasMap.put("cobb", 4);
        aliasMap.put("cobble", 4);
        aliasMap.put("cobblestone", 4);
        aliasMap.put("cstone", 4);
        aliasMap.put("wood", 5);
        aliasMap.put("plank", 5);
        aliasMap.put("planks", 5);
        aliasMap.put("sapling", 6);
        aliasMap.put("bedrock", 7);
        aliasMap.put("sand", 12);
        aliasMap.put("gravel", 13);
        aliasMap.put("goldore", 14);
        aliasMap.put("gold_ore", 14);
        aliasMap.put("ironore", 15);
        aliasMap.put("iron_ore", 15);
        aliasMap.put("log", 17);
        aliasMap.put("tree", 17);
        aliasMap.put("leave", 18);
        aliasMap.put("leaves", 18);
        aliasMap.put("sponge", 19);
        aliasMap.put("glass", 20);
        aliasMap.put("lapisblock", 22);
        aliasMap.put("lapis_block", 22);
        aliasMap.put("dispenser", 23);
        aliasMap.put("sandstone", 24);
        aliasMap.put("sand_stone", 24);
        aliasMap.put("noteblock", 25);
        aliasMap.put("note_block", 25);
        aliasMap.put("poweredrail", 27);
        aliasMap.put("powered_rail", 27);
        aliasMap.put("detectorrail", 28);
        aliasMap.put("detector_rail", 28);
        aliasMap.put("stickypiston", 29);
        aliasMap.put("sticky_piston", 29);
        aliasMap.put("deadbush", 32);
        aliasMap.put("dead_bush", 32);
        aliasMap.put("piston", 33);
        aliasMap.put("wool", 35);
        aliasMap.put("cloth", 35);
        aliasMap.put("flower", 37);
        aliasMap.put("yellow_flower", 37);
        aliasMap.put("redflower", 38);
        aliasMap.put("red_flower", 38);
        aliasMap.put("rose", 38);
        aliasMap.put("brownmushroom", 39);
        aliasMap.put("brown_mushroom", 39);
        aliasMap.put("redmushroom", 40);
        aliasMap.put("red_mushroom", 40);
        aliasMap.put("goldblock", 41);
        aliasMap.put("gold_block", 41);
        aliasMap.put("ironblock", 42);
        aliasMap.put("iron_block", 42);
        aliasMap.put("step", 44);
        aliasMap.put("slab", 44);
        aliasMap.put("half", 44);
        aliasMap.put("halfstep", 44);
        aliasMap.put("half_setp", 44);
        aliasMap.put("halfslab", 44);
        aliasMap.put("half_slab", 44);
        aliasMap.put("brick", 45);
        aliasMap.put("bricks", 45);
        aliasMap.put("tnt", 46);
        aliasMap.put("bookshelf", 47);
        aliasMap.put("book_shelf", 47);
        aliasMap.put("mossy", 48);
        aliasMap.put("mossystone", 48);
        aliasMap.put("mossy_stone", 48);
        aliasMap.put("mossycobb", 48);
        aliasMap.put("mossy_cobb", 48);
        aliasMap.put("mossycobble", 48);
        aliasMap.put("mossy_cobble", 48);
        aliasMap.put("mossycobblestone", 48);
        aliasMap.put("mossy_cobblestone", 48);
        aliasMap.put("obsidian", 49);
        aliasMap.put("torch", 50);
        aliasMap.put("woodstair", 53);
        aliasMap.put("wood_stair", 53);
        aliasMap.put("woodenstair", 53);
        aliasMap.put("wooden_stair", 53);
        aliasMap.put("chest", 54);
        aliasMap.put("diamondblock", 57);
        aliasMap.put("diamond_block", 57);
        aliasMap.put("workbench", 58);
        aliasMap.put("furnace", 61);
        aliasMap.put("ladder", 65);
        aliasMap.put("rail", 66);
        aliasMap.put("stonestair", 67);
        aliasMap.put("stone_stair", 67);
        aliasMap.put("lever", 69);
        aliasMap.put("stonepressure", 70);
        aliasMap.put("stone_pressure", 70);
        aliasMap.put("woodpressure", 72);
        aliasMap.put("wood_pressure", 72);
        aliasMap.put("woodenpressure", 72);
        aliasMap.put("wooden_pressure", 72);
        aliasMap.put("redtorch", 76);
        aliasMap.put("red_torch", 76);
        aliasMap.put("redstonetorch", 76);
        aliasMap.put("redstone_torch", 76);
        aliasMap.put("button", 77);
        aliasMap.put("ice", 79);
        aliasMap.put("snow", 80);
        aliasMap.put("cactus", 81);
        aliasMap.put("clay", 82);
        aliasMap.put("jukebox", 84);
        aliasMap.put("fence", 85);
        aliasMap.put("pumpkin", 86);
        aliasMap.put("netherrack", 87);
        aliasMap.put("nether_rack", 87);
        aliasMap.put("soulsand", 88);
        aliasMap.put("soul_sand", 88);
        aliasMap.put("glowstone", 89);
        aliasMap.put("glow_stone", 89);
        aliasMap.put("lantern", 91);
        aliasMap.put("pumpkinlantern", 91);
        aliasMap.put("pumpkin_lantern", 91);
        aliasMap.put("jack_o_lantern", 91);
        aliasMap.put("trapdoor", 96);
        aliasMap.put("trap_door", 96);
        aliasMap.put("brickstone", 98);
        aliasMap.put("brick_stone", 98);
        aliasMap.put("ironbars", 101);
        aliasMap.put("iron_bars", 101);
        aliasMap.put("glasspane", 102);
        aliasMap.put("glass_pane", 102);
        aliasMap.put("melon", 103);
        aliasMap.put("vines", 106);
        aliasMap.put("fencegate", 107);
        aliasMap.put("fence_gate", 107);
        aliasMap.put("brickstair", 108);
        aliasMap.put("brick_stair", 108);
        aliasMap.put("stonebrickstair", 109);
        aliasMap.put("stonebrick_stair", 109);
        aliasMap.put("mycelium", 110);
        aliasMap.put("lillypad", 111);
        aliasMap.put("lilly_pad", 111);
        aliasMap.put("netherbrick", 112);
        aliasMap.put("nether_brick", 112);
        aliasMap.put("netherfence", 113);
        aliasMap.put("nether_fence", 113);
        aliasMap.put("netherstair", 114);
        aliasMap.put("nether_stair", 114);
        aliasMap.put("enchant", 116);
        aliasMap.put("enchanting", 116);
        aliasMap.put("enchanttable", 116);
        aliasMap.put("enchant_table", 116);
        aliasMap.put("enchantingtable", 116);
        aliasMap.put("enchanting_table", 116);
        aliasMap.put("endstone", 121);
        aliasMap.put("end_stone", 121);
        aliasMap.put("dragonegg", 122);
        aliasMap.put("dragon_egg", 122);
        aliasMap.put("redlantern", 123);
        aliasMap.put("red_lantern", 123);
        aliasMap.put("redstonelantern", 123);
        aliasMap.put("redstone_lantern", 123);
        // aliasMap.put("", 100);

        aliases.options().header("[" + plugin.getDescription().getName() + "] Plugin by " + "Hoppelmann, Nolig and TutorialMakerHD");
        for (Entry<String, Integer> entry : aliasMap.entrySet()) {
            String alias = "aliases." + entry.getKey();
            Integer id = entry.getValue();
            if (!aliases.contains(alias)) {
                aliases.set(alias, id);
            }
        }

        try {
            aliases.save(aliasFile);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    MinecartRevolution plugin;
}
