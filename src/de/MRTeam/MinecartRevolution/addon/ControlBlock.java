//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.addon;

import org.bukkit.ChatColor;
import org.bukkit.entity.Minecart;
import org.bukkit.plugin.java.JavaPlugin;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public abstract class ControlBlock implements Control {

    public ControlBlock(JavaPlugin plugin, int blockId, int blockMetadata, String blockName) {

        this.plugin = plugin;
        this.blockId = blockId;
        this.blockMetadata = blockMetadata;
        this.blockName = blockName;

        placeMessage = ChatColor.GRAY + "You have created a " + blockName + " block!";
        destroyMessage = ChatColor.GRAY + "You have destroyed a " + blockName + " block!";

        MinecartRevolution.blockAction.addControlBlock(this);
        MinecartRevolution.configUtil.addAddonBlockId(plugin, blockName, blockId, blockMetadata);
    }

    public ControlBlock(JavaPlugin plugin, int blockId, int blockMetadata, String blockName, String placeMessage, String destroyMessage) {

        this.plugin = plugin;
        this.blockId = blockId;
        this.blockMetadata = blockMetadata;
        this.blockName = blockName;

        this.placeMessage = placeMessage;
        this.destroyMessage = destroyMessage;

        MinecartRevolution.blockAction.addControlBlock(this);
        MinecartRevolution.configUtil.addAddonBlockId(plugin, blockName, blockId, blockMetadata);
    }

    public void reset(Minecart minecart) {

    }

    public void onRedstonePower(Minecart minecart) {

    }

    public int getBlockId() {

        return blockId;
    }

    public int getBlockMetadata() {

        return blockMetadata;
    }

    public String getBlockName() {

        return blockName;
    }

    public void setBlockId(int blockId) {

        this.blockId = blockId;
    }

    public void setBlockMetadata(int blockMetadata) {

        this.blockMetadata = blockMetadata;
    }

    public void setBlockName(String blockName) {

        this.blockName = blockName;
    }

    public JavaPlugin plugin;
    private int       blockId        = 0;
    private int       blockMetadata  = -1;
    private String    blockName      = "";

    public String     placeMessage   = "";
    public String     destroyMessage = "";

}
