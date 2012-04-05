//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.action;

import java.util.ArrayList;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import de.MRTeam.MinecartRevolution.MinecartRevolution;
import de.MRTeam.MinecartRevolution.addon.ControlSign;
import de.MRTeam.MinecartRevolution.control.SignAnnounce;
import de.MRTeam.MinecartRevolution.control.SignChest;
import de.MRTeam.MinecartRevolution.control.SignCollect;
import de.MRTeam.MinecartRevolution.control.SignCommand;
import de.MRTeam.MinecartRevolution.control.SignCraft;
import de.MRTeam.MinecartRevolution.control.SignDescent;
import de.MRTeam.MinecartRevolution.control.SignDestination;
import de.MRTeam.MinecartRevolution.control.SignEffect;
import de.MRTeam.MinecartRevolution.control.SignFarm;
import de.MRTeam.MinecartRevolution.control.SignLock;
import de.MRTeam.MinecartRevolution.control.SignMaxspeed;
import de.MRTeam.MinecartRevolution.control.SignSensor;
import de.MRTeam.MinecartRevolution.control.SignSmelt;
import de.MRTeam.MinecartRevolution.control.SignTime;
import de.MRTeam.MinecartRevolution.control.SignWeather;

public class SignAction {

    public SignAction(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    public void doSignEvent(Block block, Minecart minecart) {

        if (MinecartRevolution.blockUtil.getSignBlockSign(minecart) != null && !MinecartRevolution.blockUtil.getSignBlockSign(minecart).getBlock().isBlockIndirectlyPowered()) {
            Sign sign = MinecartRevolution.blockUtil.getSignBlockSign(minecart);
            if (sign.getLine(0).equalsIgnoreCase("[Announce]")) {
                signAnnounce.execute(minecart);
            } else if (sign.getLine(0).equalsIgnoreCase("[Chest]")) {
                signChest.execute(minecart);
            } else if (sign.getLine(0).equalsIgnoreCase("[Collect]")) {
                signCollect.execute(minecart);
            } else if (sign.getLine(0).equalsIgnoreCase("[Craft]")) {
                signCraft.execute(minecart);
            } else if (sign.getLine(1).equalsIgnoreCase("[Smelt]")) {
                signSmelt.execute(minecart);
            } else if (sign.getLine(0).equalsIgnoreCase("[Farm]")) {
                signFarm.execute(minecart);
            } else if (sign.getLine(1).equalsIgnoreCase("[Lock]")) {
                signLock.execute(minecart);
            } else if (sign.getLine(1).equalsIgnoreCase("[MaxSpeed]")) {
                signMaxspeed.execute(minecart);
            } else if (sign.getLine(1).equalsIgnoreCase("[Time]")) {
                signTime.execute(minecart);
            } else if (sign.getLine(1).equalsIgnoreCase("[Weather]")) {
                signWeather.execute(minecart);
            } else if (sign.getLine(0).equalsIgnoreCase("[Sensor]")) {
                signSensor.execute(minecart);
            } else if (sign.getLine(0).equalsIgnoreCase("[Command]")) {
                signCommand.execute(minecart);
            } else if (sign.getLine(1).equalsIgnoreCase("[Descent]")) {
                signDescent.execute(minecart);
            } else if (sign.getLine(1).equalsIgnoreCase("[Destination]")) {
                signDestination.execute(minecart);
            } else if (sign.getLine(1).equalsIgnoreCase("[Effect]")) {
                signEffect.execute(minecart);
            } else {
                for (int counter = 0; counter < signList.size(); counter++) {
                    ControlSign controlSign = signList.get(counter);
                    if ( (sign.getLine(0).equalsIgnoreCase(controlSign.getLine(0)) || controlSign.getLine(0).equalsIgnoreCase("")) && (sign.getLine(1).equalsIgnoreCase(controlSign.getLine(1)) || controlSign.getLine(1).equalsIgnoreCase("")) && (sign.getLine(2).equalsIgnoreCase(controlSign.getLine(2)) || controlSign.getLine(2).equalsIgnoreCase("")) && (sign.getLine(3).equalsIgnoreCase(controlSign.getLine(3)) || controlSign.getLine(3).equalsIgnoreCase(""))) {
                        controlSign.execute(minecart);
                        return;
                    }
                }

                signAnnounce.reset(minecart);
                signCommand.reset(minecart);

                for (int counter = 0; counter < signList.size(); counter++) {
                    ControlSign controlSign = signList.get(counter);
                    controlSign.reset(minecart);
                }
            }
        }

        if (MinecartRevolution.blockUtil.getSignBlockSign(minecart) == null && MinecartRevolution.blockUtil.getSignBlockSignMoreRadius(minecart) != null) {
            MinecartRevolution.blockUtil.powerNearbyLeaver(MinecartRevolution.blockUtil.getSignBlockSignMoreRadius(minecart).getBlock(), false);
        }
    }

    public void addControlSign(ControlSign controlSign) {

        signList.add(controlSign);
        MinecartRevolution.messagesUtil.addAddonControlSignMessages(controlSign.plugin, controlSign.getBlockName(), controlSign.placeMessage, controlSign.destroyMessage);
    }

    MinecartRevolution            plugin;

    public SignLock               signLock        = new SignLock();
    public SignAnnounce           signAnnounce    = new SignAnnounce();
    public SignChest              signChest       = new SignChest();
    public SignCollect            signCollect     = new SignCollect();
    public SignCraft              signCraft       = new SignCraft();
    public SignSmelt              signSmelt       = new SignSmelt();
    public SignFarm               signFarm        = new SignFarm();
    public SignMaxspeed           signMaxspeed    = new SignMaxspeed();
    public SignTime               signTime        = new SignTime();
    public SignWeather            signWeather     = new SignWeather();
    public SignSensor             signSensor      = new SignSensor();
    public SignCommand            signCommand     = new SignCommand();
    public SignDescent            signDescent     = new SignDescent();
    public SignDestination        signDestination = new SignDestination();
    public SignEffect             signEffect      = new SignEffect();

    public ArrayList<ControlSign> signList        = new ArrayList<ControlSign>();

}
