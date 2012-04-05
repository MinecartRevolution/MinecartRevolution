//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.addon;

import org.bukkit.entity.Minecart;

public abstract interface Control {

    public void execute(Minecart minecart);

    public void reset(Minecart minecart);

    public void onRedstonePower(Minecart minecart);

    public String getBlockName();

    public void setBlockName(String blockName);

}
