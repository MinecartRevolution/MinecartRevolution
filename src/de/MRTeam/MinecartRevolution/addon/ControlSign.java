//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.addon;

import org.bukkit.ChatColor;
import org.bukkit.entity.Minecart;
import org.bukkit.plugin.java.JavaPlugin;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public abstract class ControlSign implements Control {

    public ControlSign(JavaPlugin plugin, String blockName, String line1, String line2, String line3, String line4) {

        lines[0] = line1;
        lines[1] = line2;
        lines[2] = line3;
        lines[3] = line4;
        this.plugin = plugin;
        this.blockName = blockName;

        placeMessage = ChatColor.GRAY + "You have created a " + blockName + " sign!";
        destroyMessage = ChatColor.GRAY + "You have destroyed a " + blockName + " sign!";

        MinecartRevolution.signAction.addControlSign(this);
    }

    public ControlSign(JavaPlugin plugin, String blockName, String line1, String line2, String line3, String line4, String placeMessage, String destroyMessage) {

        lines[0] = line1;
        lines[1] = line2;
        lines[2] = line3;
        lines[3] = line4;
        this.plugin = plugin;
        this.blockName = blockName;

        this.placeMessage = placeMessage;
        this.destroyMessage = destroyMessage;

        MinecartRevolution.signAction.addControlSign(this);
    }

    @Override
    public void reset(Minecart minecart) {

    }

    @Override
    public void onRedstonePower(Minecart minecart) {

    }

    @Override
    public String getBlockName() {

        return blockName;
    }

    public String getLine(int line) {

        if (line >= 0 && line <= 3) {
            return lines[line];
        } else {
            return "";
        }
    }

    @Override
    public void setBlockName(String blockName) {

        this.blockName = blockName;
    }

    public void setLine(int line, String text) {

        if (line >= 0 && line <= 3) {
            lines[line] = text;
        }
    }

    public JavaPlugin plugin;
    private String    blockName      = "";
    private String[]  lines          = new String[4];

    public String     placeMessage   = "";
    public String     destroyMessage = "";

}
