
package de.MRTeam.MinecartRevolution.util;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class PatchRegionUtil {

    public PatchRegionUtil(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    public void execute(Player player) {

        MMScanThread mmScanThread = new MMScanThread();
        mmScanThread.setValues(player);
        mmScanThread.setPriority(Thread.MAX_PRIORITY);
        mmScanThread.start();
    }

    MinecartRevolution plugin;
}

class MMScanThread extends Thread implements Runnable {

    public void setValues(Player player) {

        this.player = player;
    }

    public void run() {

        int signsFound = 0;

        for (int counter = 0; counter < Bukkit.getWorlds().size(); counter++) {
            for (Chunk chunk : Bukkit.getWorlds().get(counter).getLoadedChunks()) {
                for (BlockState blockState : chunk.getTileEntities()) {
                    if (blockState instanceof Sign) {
                        Sign sign = (Sign) blockState;

                        signsFound++;
                        if (sign.getLine(0).toLowerCase().contains("hold for")) {
                            String[] splitted = sign.getLine(0).split(" ");
                            replaceSign(sign, "", "[Hold]", splitted[2], "");
                        } else if (sign.getLine(0).equalsIgnoreCase("Lock Cart")) {
                            replaceSign(sign, "", "[Lock]", "Add", "");
                        } else if (sign.getLine(0).equalsIgnoreCase("unlock Cart")) {
                            replaceSign(sign, "", "[Lock]", "Remove", "");
                        } else if (sign.getLine(0).equalsIgnoreCase("minecart type")) {
                            replaceSign(sign, "", "[Spawn]", sign.getLine(1), "");
                        } else if (sign.getLine(0).equalsIgnoreCase("eject here")) {
                            replaceSign(sign, "", "[Eject]", "", "");
                        } else if (sign.getLine(0).equalsIgnoreCase("announce")) {
                            replaceSign(sign, "[Announce]", sign.getLine(1), sign.getLine(2), sign.getLine(3));
                        } else if (sign.getLine(0).toLowerCase().contains("max speed:")) {
                            String[] splitted = sign.getLine(0).split(" ");
                            replaceSign(sign, "", "[MaxSpeed]", splitted[2], "");
                        } else if (sign.getLine(0).equalsIgnoreCase("elevator")) {
                            sign.getBlock().setTypeId(0);
                            Location newLocation = sign.getLocation();
                            newLocation.setY(newLocation.getY() - 1);
                            newLocation.getBlock().setTypeId(MinecartRevolution.configUtil.elevatorBlockId[0]);
                            newLocation.getBlock().setData((byte) MinecartRevolution.configUtil.elevatorBlockId[1]);
                            newLocation.setY(newLocation.getY() + 1);
                            newLocation.getBlock().setType(Material.RAILS);
                        } else if (sign.getLine(0).toLowerCase().contains("[station:")) {
                            String[] splitted = sign.getLine(0).split(":");
                            replaceSign(sign, "", "[Destination]", splitted[1].replaceAll("]", ""), "");
                        } else if (sign.getLine(0).toLowerCase().contains("range:") || sign.getLine(0).toLowerCase().contains("item range:")) {
                            String[] splitted = sign.getLine(0).split(" ");
                            if (sign.getLine(0).toLowerCase().contains("item range")) {
	replaceSign(sign, "[Collect]", "", splitted[2], "");
                            } else if (sign.getLine(0).toLowerCase().contains("range")) {
	replaceSign(sign, "[Collect]", "", splitted[1], "");
                            }
                        } else if (sign.getLine(0).toLowerCase().contains("sensor:")) {
                            String[] splitted = sign.getLine(0).split(":");
                            if (splitted[1].equalsIgnoreCase("0002")) {
	replaceSign(sign, "[Sensor]", "Empty", "", "");
                            } else if (splitted[1].equalsIgnoreCase("0003") || splitted[1].equalsIgnoreCase("0004")) {
	replaceSign(sign, "[Sensor]", "Mob", "", "");
                            } else if (splitted[1].equalsIgnoreCase("0005")) {
	replaceSign(sign, "[Sensor]", "Player", "", "");
                            } else if (splitted[1].equalsIgnoreCase("0006")) {
	replaceSign(sign, "[Sensor]", "Minecart", "Storage", "");
                            } else if (splitted[1].equalsIgnoreCase("0007")) {
	replaceSign(sign, "[Sensor]", "Minecart", "Powered", "");
                            } else if (splitted[1].equalsIgnoreCase("0008") || splitted[1].equalsIgnoreCase("0017")) {
	replaceSign(sign, "[Sensor]", "Item", "Inventory", sign.getLine(2));
                            } else if (splitted[1].equalsIgnoreCase("0009")) {
	replaceSign(sign, "[Sensor]", "Player", sign.getLine(2), "");
                            } else if (splitted[1].equalsIgnoreCase("0019")) {
	replaceSign(sign, "[Sensor]", "St", sign.getLine(2), "");
                            } else if (splitted[1].equalsIgnoreCase("0020")) {
	replaceSign(sign, "[Sensor]", "Item", "Hand", sign.getLine(2));
                            }
                        } else if (sign.getLine(0).equalsIgnoreCase("Eject At")) {
                            replaceSign(sign, "", "[Eject]", sign.getLine(2).replaceAll(":", ","), "");
                        } else if ( (sign.getLine(0) + sign.getLine(1) + sign.getLine(2) + sign.getLine(3)).toLowerCase().contains("fuel:") && (sign.getLine(0) + sign.getLine(1) + sign.getLine(2) + sign.getLine(3)).toLowerCase().contains("smelt:")) {
                            for (int lineCounter = 0; lineCounter < 4; lineCounter++) {
	if (sign.getLine(lineCounter).toLowerCase().contains("smelt:")) {
	    for (String string : sign.getLine(lineCounter).split(":")) {
	        if (string.replaceAll("@.+", "").trim().matches("[0-9]+")) {
	            replaceSign(sign, "", "[Smelt]", string.replaceAll("@.+", "").trim(), "");
	            break;
	        }
	    }
	}
                            }
                        } else if (sign.getLine(0).equalsIgnoreCase("Collect Items")) {
                            ArrayList<String> collectItems = new ArrayList<String>();
                            for (int lineCounter = 1; lineCounter < 4; lineCounter++) {
	for (String string : sign.getLine(lineCounter).split(":")) {
	    if (string.replaceAll("@.+", "").trim().matches("[0-9]+")) {
	        collectItems.add(string.replaceAll("@.+", "").trim());
	    }
	}
                            }
                            replaceSign(sign, "[Chest]", "+ " + formatItems(collectItems), "", "");
                        } else if (sign.getLine(0).equalsIgnoreCase("Deposit Items")) {
                            ArrayList<String> depositItems = new ArrayList<String>();
                            for (int lineCounter = 1; lineCounter < 4; lineCounter++) {
	for (String string : sign.getLine(lineCounter).split(":")) {
	    if (string.replaceAll("@.+", "").trim().matches("[0-9]+")) {
	        depositItems.add(string.replaceAll("@.+", "").trim());
	    }
	}
                            }
                            replaceSign(sign, "[Chest]", "- " + formatItems(depositItems), "", "");
                        } else if (sign.getLine(0).equalsIgnoreCase("Trash Items")) {
                            ArrayList<String> trashItems = new ArrayList<String>();
                            for (int lineCounter = 1; lineCounter < 4; lineCounter++) {
	for (String string : sign.getLine(lineCounter).split(":")) {
	    if (string.replaceAll("@.+", "").trim().matches("[0-9]+")) {
	        trashItems.add(string.replaceAll("@.+", "").trim());
	    }
	}
                            }
                            replaceSign(sign, "", "[InvClear]", formatItems(trashItems), "");
                        } else if (sign.getLine(0).equalsIgnoreCase("[train]")) {
                            if (sign.getLine(1).equalsIgnoreCase("destination")) {
	replaceSign(sign, "", "[Destination]", sign.getLine(2), "");
                            } else if (sign.getLine(1).toLowerCase().contains("spawn")) {
	String minecartType = "";
	if (sign.getLine(2).charAt(0) == 'm') {
	    minecartType = "Standard";
	} else if (sign.getLine(2).charAt(0) == 'p') {
	    minecartType = "Powered";
	} else if (sign.getLine(2).charAt(0) == 's') {
	    minecartType = "Storage";
	}
	replaceSign(sign, "", "[Spawn]", minecartType, "");
                            } else if (sign.getLine(1).equalsIgnoreCase("tag")) {
	sign.getBlock().setType(Material.STONE);
                            } else if (sign.getLine(1).equalsIgnoreCase("station")) {
	if (sign.getLine(2).isEmpty()) {
	    replaceSign(sign, "", "[TrainStation]", "", "");
	} else {
	    replaceSign(sign, "", "[Hold]", sign.getLine(2), "");
	}
	Location newLocation = sign.getLocation();
	newLocation.setY(newLocation.getY() + 1);
	newLocation.getBlock().setTypeId(MinecartRevolution.configUtil.stationBlockId[0]);
	newLocation.getBlock().setData((byte) MinecartRevolution.configUtil.stationBlockId[1]);
                            } else if (sign.getLine(1).equalsIgnoreCase("property")) {
	if (sign.getLine(2).equalsIgnoreCase("destination")) {
	    replaceSign(sign, "", "[Descent]", sign.getLine(3), "");
	} else if (sign.getLine(2).equalsIgnoreCase("maxspeed") && !sign.getLine(3).isEmpty()) {
	    try {
	        double value = Double.valueOf(sign.getLine(3));
	        value = value * 250;
	        replaceSign(sign, "", "[MaxSpeed]", String.valueOf(value), "");
	    }
	    catch (Exception ex) {
	    }
	}
                            }
                        } else if (sign.getLine(0).equalsIgnoreCase("[Chest]")) {
                            boolean replaced = false;
                            if (sign.getLine(1).equalsIgnoreCase("0")) {
	replaceSign(sign, "[Chest]", "", sign.getLine(2), "");
	replaced = true;
                            } else if (!sign.getLine(1).isEmpty() && !sign.getLine(1).contains("+") && !sign.getLine(1).contains("-")) {
	String line1 = sign.getLine(1);
	if (!sign.getLine(1).isEmpty()) {
	    line1 = "+ " + line1;
	}
	replaceSign(sign, "[Chest]", line1, sign.getLine(2), "");
	replaced = true;
                            }
                            if (sign.getLine(2).equalsIgnoreCase("0")) {
	replaceSign(sign, "[Chest]", sign.getLine(1), "", "");
	replaced = true;
                            } else if (!sign.getLine(2).isEmpty() && !sign.getLine(2).contains("+") && !sign.getLine(2).contains("-")) {
	String line2 = sign.getLine(2);
	if (!sign.getLine(2).isEmpty()) {
	    line2 = "- " + line2;
	}
	replaceSign(sign, "[Chest]", sign.getLine(1), line2, "");
	replaced = true;
                            }
                            if (!replaced) {
	signsFound--;
                            }
                        } else if (sign.getLine(0).equalsIgnoreCase("[Farm]") && sign.getLine(1).equalsIgnoreCase("Weed")) {
                            replaceSign(sign, "[Farm]", "Wheat", sign.getLine(2), sign.getLine(3));
                        } else {
                            signsFound--;
                        }

                        if (sign.getLine(1).equalsIgnoreCase("[Destination]") && sign.getLine(2) != null) {
                            if (MinecartRevolution.databaseDestinationLocationUtil.readDestinationLocation(sign.getLine(2)) == null) {
	MinecartRevolution.databaseDestinationLocationUtil.saveDestinationLocation(sign.getLine(2), sign.getLocation());
                            }
                        }
                    }
                }
            }
        }

        MinecartRevolution.messagesUtil.sendMessage(player, (MinecartRevolution.messagesUtil.getMessage("patchregion.replaced", "").replaceAll("%amount%", String.valueOf(signsFound))), false);
    }

    private String formatItems(ArrayList<String> items) {

        String itemString = "";
        for (int listCounter = 0; listCounter < items.size(); listCounter++) {
            itemString += items.get(listCounter);
            if (listCounter != items.size() - 1) {
                itemString += ",";
            }
        }

        return itemString;
    }

    private void replaceSign(Sign sign, String line1, String line2, String line3, String line4) {

        Sign newSign = (Sign) sign.getBlock().getState();

        newSign.setLine(0, line1);
        newSign.setLine(1, line2);
        newSign.setLine(2, line3);
        newSign.setLine(3, line4);

        newSign.update();
    }

    Player player = null;

}