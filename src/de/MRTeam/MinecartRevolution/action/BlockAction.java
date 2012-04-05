//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.action;

import java.util.ArrayList;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import de.MRTeam.MinecartRevolution.MinecartRevolution;
import de.MRTeam.MinecartRevolution.addon.ControlBlock;
import de.MRTeam.MinecartRevolution.control.BlockBooster;
import de.MRTeam.MinecartRevolution.control.BlockBrake;
import de.MRTeam.MinecartRevolution.control.BlockClear;
import de.MRTeam.MinecartRevolution.control.BlockEject;
import de.MRTeam.MinecartRevolution.control.BlockElevator;
import de.MRTeam.MinecartRevolution.control.BlockFly;
import de.MRTeam.MinecartRevolution.control.BlockGrab;
import de.MRTeam.MinecartRevolution.control.BlockHeal;
import de.MRTeam.MinecartRevolution.control.BlockIntersection;
import de.MRTeam.MinecartRevolution.control.BlockKill;
import de.MRTeam.MinecartRevolution.control.BlockReverse;
import de.MRTeam.MinecartRevolution.control.BlockStation;

public class BlockAction {

    public BlockAction(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    public void doBlockEvent(Block block, Minecart minecart) {

        if (MinecartRevolution.blockUtil.getControlBlock(minecart) != null && !MinecartRevolution.blockUtil.getControlBlock(minecart).isBlockIndirectlyPowered()) {
            if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == MinecartRevolution.configUtil.boosterBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == MinecartRevolution.configUtil.boosterBlockId[1] || MinecartRevolution.configUtil.boosterBlockId[1] == -1)) {
                blockBooster.execute(minecart);
            } else if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == MinecartRevolution.configUtil.brakeBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == MinecartRevolution.configUtil.brakeBlockId[1] || MinecartRevolution.configUtil.brakeBlockId[1] == -1)) {
                blockBrake.execute(minecart);
            } else if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == MinecartRevolution.configUtil.reverseBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == MinecartRevolution.configUtil.reverseBlockId[1] || MinecartRevolution.configUtil.reverseBlockId[1] == -1)) {
                blockReverse.execute(minecart);
            } else if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == MinecartRevolution.configUtil.ejectBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == MinecartRevolution.configUtil.ejectBlockId[1] || MinecartRevolution.configUtil.ejectBlockId[1] == -1)) {
                blockEject.execute(minecart);
            } else if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == MinecartRevolution.configUtil.elevatorBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == MinecartRevolution.configUtil.elevatorBlockId[1] || MinecartRevolution.configUtil.elevatorBlockId[1] == -1)) {
                blockElevator.execute(minecart);
            } else if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == MinecartRevolution.configUtil.stationBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == MinecartRevolution.configUtil.stationBlockId[1] || MinecartRevolution.configUtil.stationBlockId[1] == -1)) {
                blockStation.execute(minecart);
            } else if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == MinecartRevolution.configUtil.killBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == MinecartRevolution.configUtil.killBlockId[1] || MinecartRevolution.configUtil.killBlockId[1] == -1)) {
                blockKill.execute(minecart);
            } else if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == MinecartRevolution.configUtil.clearInvBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == MinecartRevolution.configUtil.clearInvBlockId[1] || MinecartRevolution.configUtil.clearInvBlockId[1] == -1)) {
                blockClear.execute(minecart);
            } else if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == MinecartRevolution.configUtil.flyBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == MinecartRevolution.configUtil.flyBlockId[1] || MinecartRevolution.configUtil.flyBlockId[1] == -1)) {
                blockFly.execute(minecart);
            } else if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == MinecartRevolution.configUtil.healBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == MinecartRevolution.configUtil.healBlockId[1] || MinecartRevolution.configUtil.healBlockId[1] == -1)) {
                blockHeal.execute(minecart);
            } else if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == MinecartRevolution.configUtil.grabBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == MinecartRevolution.configUtil.grabBlockId[1] || MinecartRevolution.configUtil.grabBlockId[1] == -1)) {
                blockGrab.execute(minecart);
            } else if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == MinecartRevolution.configUtil.intersectionBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == MinecartRevolution.configUtil.intersectionBlockId[1] || MinecartRevolution.configUtil.intersectionBlockId[1] == -1)) {
                blockIntersection.execute(minecart);
            } else {
                for (int counter = 0; counter < blockList.size(); counter++) {
                    ControlBlock controlBlock = blockList.get(counter);
                    int[] controlBlockId = MinecartRevolution.blockUtil.splitBlockData((String) MinecartRevolution.configUtil.getAddonSetting(controlBlock.plugin, "blockId." + controlBlock.getBlockName()));
                    if (MinecartRevolution.blockUtil.getControlBlock(minecart).getTypeId() == controlBlockId[0] && (MinecartRevolution.blockUtil.getControlBlock(minecart).getData() == controlBlockId[1] || controlBlockId[1] == -1)) {
                        controlBlock.execute(minecart);
                        return;
                    }
                }
                if (MinecartRevolution.removeDerailedCartsUtil.checkForDerailedCart(minecart)) {
                    MinecartRevolution.removeDerailedCartsUtil.execute(minecart);
                }

                blockIntersection.resetPunchMessage(minecart);

                for (int counter = 0; counter < blockList.size(); counter++) {
                    ControlBlock controlBlock = blockList.get(counter);
                    controlBlock.reset(minecart);
                }
            }
        }
    }

    public void addControlBlock(ControlBlock controlBlock) {

        blockList.add(controlBlock);
        MinecartRevolution.messagesUtil.addAddonControlBlockMessages(controlBlock.plugin, controlBlock.getBlockName(), controlBlock.placeMessage, controlBlock.destroyMessage);
    }

    MinecartRevolution             plugin;

    public BlockBooster            blockBooster        = new BlockBooster();
    public BlockBrake              blockBrake          = new BlockBrake();
    public BlockReverse            blockReverse        = new BlockReverse();
    public BlockEject              blockEject          = new BlockEject();
    public BlockElevator           blockElevator       = new BlockElevator();
    public BlockStation            blockStation        = new BlockStation();
    public BlockKill               blockKill           = new BlockKill();
    public BlockClear              blockClear          = new BlockClear();
    public BlockFly                blockFly            = new BlockFly();
    public BlockHeal               blockHeal           = new BlockHeal();
    public BlockIntersection       blockMinecartSelect = new BlockIntersection();
    public BlockGrab               blockGrab           = new BlockGrab();
    public BlockIntersection       blockIntersection   = new BlockIntersection();

    public ArrayList<ControlBlock> blockList           = new ArrayList<ControlBlock>();

}
