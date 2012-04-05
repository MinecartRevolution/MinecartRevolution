//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.control;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class SignEffect {

    public void execute(Minecart minecart) {

        Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
        if (sign == null) {
            return;
        }

        if (sign.getLine(3).equalsIgnoreCase("Add")) {
            ArrayList<String> effectList = new ArrayList<String>();
            if (effectMinecartMap.containsKey(minecart)) {
                effectList = effectMinecartMap.get(minecart);
            }
            effectList.add(sign.getLine(2));
            effectMinecartMap.put(minecart, effectList);
        } else if (sign.getLine(2).equalsIgnoreCase("Remove")) {
            if (effectMinecartMap.containsKey(minecart)) {
                effectMinecartMap.remove(minecart);
            }
        } else if (sign.getLine(3).equalsIgnoreCase("Remove")) {
            if (effectMinecartMap.containsKey(minecart)) {
                ArrayList<String> effectList = effectMinecartMap.get(minecart);
                if (effectList.contains(sign.getLine(2))) {
                    effectList.remove(sign.getLine(2));
                    effectMinecartMap.remove(minecart);
                    effectMinecartMap.put(minecart, effectList);
                }
            }
        } else {
            MinecartRevolution.playEffectUtil.playEffect(minecart, sign.getLine(2));
        }
    }

    public HashMap<Minecart, ArrayList<String>> effectMinecartMap = new HashMap<Minecart, ArrayList<String>>();

}
