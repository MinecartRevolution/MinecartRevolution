//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.util;

import java.util.ArrayList;
import java.util.HashMap;
import de.MRTeam.MinecartRevolution.MinecartRevolution;
import de.MRTeam.MinecartRevolution.addon.MrCommandExecuter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class CommandExecuterUtil implements CommandExecutor {

    MinecartRevolution plugin;

    public CommandExecuterUtil(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }

        if (cmd.getName().equalsIgnoreCase("mr")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("getv") || args.length == 1 && args[0].equalsIgnoreCase("getversion")) {
                if (MinecartRevolution.permissionUtil.hasPermission(player, "getv", "")) {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("getVersion", "").replaceAll("%version%", plugin.getDescription().getVersion()), false);
                }
            } else if ( (args.length == 1 || args.length == 2) && args[0].equalsIgnoreCase("checkv") || args.length == 1 && args[0].equalsIgnoreCase("checkversion")) {
                if (MinecartRevolution.permissionUtil.hasPermission(player, "update", "versioncheck")) {
                    boolean dev = false;
                    if (args.length >= 2 && args[1].equalsIgnoreCase("dev")) {
                        dev = true;
                    }
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.updateUtil.checkVersion(dev, true), false);
                    if (dev) {
                        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("devBuildPart1", "warning"), true);
                        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("devBuildPart2", "warning"), true);
                    }
                } else {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("noPermission", ""), false);
                }
            } else if ( (args.length == 1 || args.length == 2) && args[0].equalsIgnoreCase("update")) {
                if (MinecartRevolution.permissionUtil.hasPermission(player, "update", "update")) {
                    boolean dev = false;
                    if (args.length >= 2 && args[1].equalsIgnoreCase("dev")) {
                        dev = true;
                    }
                    MinecartRevolution.updateUtil.update(player, dev);
                } else {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("noPermission", ""), false);
                }
            } else if ( (args.length == 1 || args.length == 2 || args.length == 3) && args[0].equalsIgnoreCase("addons")) {
                if (args.length == 2 && args[1].equalsIgnoreCase("get")) {
                    if (MinecartRevolution.permissionUtil.hasPermission(player, "addons", "get")) {
                        if (MinecartRevolution.addons.size() >= 1) {
                            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("get.list", "addons"), true);
                            for (int counter = 0; counter < MinecartRevolution.addons.size(); counter++) {
	MinecartRevolution.messagesUtil.sendMessage(player, "- " + ChatColor.GOLD + MinecartRevolution.addons.get(counter).getDescription().getName() + " " + MinecartRevolution.addons.get(counter).getDescription().getVersion(), true);
                            }
                        } else {
                            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("get.noAddons", "addons"), true);
                        }
                    } else {
                        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("noPermission", ""), false);
                    }
                } else if (args.length == 2 && args[1].equalsIgnoreCase("check")) {
                    try {
                        if (MinecartRevolution.permissionUtil.hasPermission(player, "addons", "check")) {
                            String[] addons = MinecartRevolution.updateUtil.getAddons(player);
                            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("check.list", "addons"), true);
                            for (String addonString : addons) {
	String[] specifiedAddonString = addonString.split(";");
	String addonInfo = "- " + ChatColor.GOLD + specifiedAddonString[0] + " " + specifiedAddonString[2] + ChatColor.WHITE + ": " + ChatColor.AQUA + specifiedAddonString[1];
	MinecartRevolution.messagesUtil.sendMessage(player, addonInfo, true);
                            }
                        } else {
                            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("noPermission", ""), false);
                        }
                    }
                    catch (NullPointerException ex) {
                    }
                } else if (args.length == 3 && args[1].equalsIgnoreCase("install")) {
                    if (MinecartRevolution.permissionUtil.hasPermission(player, "addons", "install")) {
                        MinecartRevolution.updateUtil.installAddon(player, args[2]);
                    } else {
                        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("noPermission", ""), false);
                    }
                } else {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("wrongSyntax", "addons"), false);
                }
            } else if ( (args.length == 2 || args.length == 3) && args[0].equalsIgnoreCase("translations")) {
                if (args.length == 2 && args[1].equalsIgnoreCase("check")) {
                    try {
                        if (MinecartRevolution.permissionUtil.hasPermission(player, "translations", "check")) {
                            String[] translations = MinecartRevolution.updateUtil.getTranslations(player);
                            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("check.list", "translations"), true);
                            for (String translationString : translations) {
	String[] specifiedTranslationString = translationString.split(";");
	String translationInfo = "- " + ChatColor.GOLD + specifiedTranslationString[0] + ChatColor.WHITE + ": " + ChatColor.AQUA + specifiedTranslationString[1];
	MinecartRevolution.messagesUtil.sendMessage(player, translationInfo, true);
                            }
                        } else {
                            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("noPermission", ""), false);
                        }
                    }
                    catch (NullPointerException ex) {
                    }
                } else if (args.length == 3 && args[1].equalsIgnoreCase("install")) {
                    if (MinecartRevolution.permissionUtil.hasPermission(player, "translations", "install")) {
                        MinecartRevolution.updateUtil.installTranslation(player, args[2]);
                    } else {
                        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("noPermission", ""), false);
                    }
                } else {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("wrongSyntax", "translations"), false);
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("flyer")) {
                if (player != null) {
                    if (MinecartRevolution.permissionUtil.hasPermission(player, "flycart", "")) {
                        if (MinecartRevolution.flyCart.flyerList.contains(player.getName())) {
                            MinecartRevolution.flyCart.flyerList.remove(player.getName());
                            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("flyCartDisable", ""), false);
                            return true;
                        }
                        if (!player.isInsideVehicle()) {
                            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("haveToBeInMinecart", ""), false);
                            return true;
                        }
                        MinecartRevolution.flyCart.flyerList.add(player.getName());
                        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("flyCartEnable", ""), false);
                        return true;
                    }
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("noPermission", ""), false);
                } else {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("cantPerformOnConsole", ""), false);
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("delcarts")) {
                if (MinecartRevolution.permissionUtil.hasPermission(player, "delcarts", "")) {
                    for (World world : plugin.getServer().getWorlds()) {
                        for (Entity entity : world.getEntities()) {
                            if (entity instanceof Minecart) {
	entity.remove();
                            }
                        }
                    }
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("removedMinecarts", ""), false);
                    if (player != null) {
                        String message = MinecartRevolution.messagesUtil.getMessage("removedMinecartsBroadcast", "").replaceAll("%player%", player.getName());
                        MinecartRevolution.messagesUtil.broadcastMessage(message, false);
                    } else {
                        String message = MinecartRevolution.messagesUtil.getMessage("removedMinecartsBroadcast", "").replaceAll("%player%", "Console");
                        MinecartRevolution.messagesUtil.broadcastMessage(message, false);
                    }
                } else {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("noPermission", ""), false);
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("stopcarts")) {
                if (MinecartRevolution.permissionUtil.hasPermission(player, "stopcarts", "")) {
                    for (World world : plugin.getServer().getWorlds()) {
                        for (Entity entity : world.getEntities()) {
                            if (entity instanceof Minecart) {
	Minecart minecart = (Minecart) entity;
	minecart.setVelocity(new Vector(0, 0, 0));
                            }
                        }
                    }

                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("stoppedMinecarts", ""), false);
                    if (player != null) {
                        String message = MinecartRevolution.messagesUtil.getMessage("stoppedMinecartsBroadcast", "").replaceAll("%player%", player.getName());
                        MinecartRevolution.messagesUtil.broadcastMessage(message, false);
                        MinecartRevolution.messagesUtil.sendLogMessage(null, message, false, "info");
                    } else {
                        String message = MinecartRevolution.messagesUtil.getMessage("stoppedMinecartsBroadcast", "").replaceAll("%player%", "Console");
                        MinecartRevolution.messagesUtil.broadcastMessage(message, false);
                        MinecartRevolution.messagesUtil.sendLogMessage(null, message, false, "info");
                    }
                } else {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("noPermission", ""), false);
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("info")) {
                sendInfo(player);
                return true;
            } else if ( (args.length == 1 || args.length == 2 || args.length == 3) && (args[0].equalsIgnoreCase("changelog") || args[0].equalsIgnoreCase("cl"))) {
                if (args.length == 1) {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("wrongSyntax.noVersion", "changelog"), false);
                } else {
                    String changelog = MinecartRevolution.downloadUtil.getFileContent("http://www.minecartrevolution.com/CHANGELOG/changelog_" + args[1] + ".txt");
                    if (changelog.contains("ERROR")) {
                        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.downloadUtil.checkErrorCodes(changelog, true), false);
                    } else {
                        String[] changelogParts = changelog.split("\\* ");
                        if (changelogParts.length > 9) {
                            if (args.length == 2) {
	MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("wrongSyntax.noPage", "changelog"), false);
                            } else {
	try {
	    if (Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) < changelogParts.length) {
	        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("panel", "changelog"), true);
	        for (int counter = 10 * (Integer.parseInt(args[2]) - 1); counter < 10 * (Integer.parseInt(args[2]) - 1) + 10 && counter < changelogParts.length; counter++) {
	            MinecartRevolution.messagesUtil.sendMessage(player, "- " + changelogParts[counter], true);
	        }
	    } else {
	        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("wrongSyntax.pageDoesntExist", "changelog"), false);
	    }
	}
	catch (NumberFormatException ex) {
	    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("wrongSyntax.wrongPage", "changelog"), false);
	}
                            }
                        } else {
                            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("panel", "changelog"), true);
                            for (String changelogPart : changelogParts) {
	MinecartRevolution.messagesUtil.sendMessage(player, "- " + changelogPart, true);
                            }
                        }
                    }
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("vender")) {
                if (player != null) {
                    if (MinecartRevolution.permissionUtil.hasPermission(player, "vender", "")) {
                        if (!player.isInsideVehicle()) {
                            Location playerloc = player.getLocation();
                            Block playerblock = playerloc.getBlock();

                            if (playerblock.getType() == Material.RAILS || playerblock.getType() == Material.POWERED_RAIL || playerblock.getType() == Material.DETECTOR_RAIL) {
	Vector velocity = player.getLocation().getDirection();
	Minecart minecart = player.getWorld().spawn(player.getLocation(), Minecart.class);
	minecart.setPassenger(player);
	minecart.setVelocity(velocity);
                            } else {
	MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("haveToBeOnRails", ""), false);
                            }
                        } else {
                            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("cantBeInVehicle", ""), false);
                        }
                    } else {
                        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("noPermission", ""), false);
                    }
                } else {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("cantPerformOnConsole", ""), false);
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (MinecartRevolution.permissionUtil.hasPermission(player, "reload", "")) {
                    MinecartRevolution.messagesUtil.loadMessages();
                    MinecartRevolution.configUtil.loadConfig();
                    MinecartRevolution.messagesUtil.sendLogMessage(player, MinecartRevolution.messagesUtil.getMessage("reloaded", ""), false, "info");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("patchregion")) {
                MinecartRevolution.messagesUtil.sendMessage(player, (MinecartRevolution.messagesUtil.getMessage("patchregion.attention", "")), false);
                for (World world : Bukkit.getWorlds()) {
                    world.save();
                }
                MinecartRevolution.patchRegionUtil.execute(player);
            } else if (args.length == 1 && args[0].equalsIgnoreCase("compass") && player != null) {
                Vector lookingDirection = player.getLocation().getDirection();
                if (Math.round(lookingDirection.getZ()) > 0.0D) {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("direction.north", "compass"), false);
                } else if (Math.round(lookingDirection.getX()) < 0.0D) {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("direction.east", "compass"), false);
                } else if (Math.round(lookingDirection.getZ()) < 0.0D) {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("direction.south", "compass"), false);
                } else if (Math.round(lookingDirection.getX()) > 0.0D) {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("direction.west", "compass"), false);
                }
            } else {
                if (args.length >= 1) {
                    for (int counter = 0; counter < mrCommandsMap.size(); counter++) {
                        if (mrCommandsMap.containsKey(args[0])) {
                            String[] newArgs = new String[args.length - 1];
                            for (int counter2 = 1; counter2 < args.length; counter2++) {
	newArgs[counter2 - 1] = args[counter2];
                            }
                            mrCommandsMap.get(args[0]).executeMrCommand(args[0], newArgs, player);
                            return true;
                        }
                    }
                }

                if (args.length >= 1) {
                    sendHelpMenu(player, plugin.getDescription().getName(), args[args.length - 1]);
                } else {
                    sendHelpMenu(player, plugin.getDescription().getName(), null);
                }
            }

            return true;
        } else if (cmd.getName().equalsIgnoreCase("st")) {
            if (args.length == 1) {
                if (MinecartRevolution.permissionUtil.hasPermission(player, "st", "")) {
                    if (player == null) {
                        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("cantPerformOnConsole", ""), false);
                    } else {
                        MinecartRevolution.minecartListener.stationWordMap.put(player.getName(), args[0]);
                        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("st", ""), false);
                    }
                }
            }

            return true;
        }

        return false;
    }

    private void sendHelpMenu(Player player, String pluginName, String argument) {

        ArrayList<String> helpCommands = new ArrayList<String>();

        helpCommands.add(colorHelpMessage("/mr", MinecartRevolution.messagesUtil.getMessage("mr", "helpMenu")));
        helpCommands.add(colorHelpMessage("/mr info", MinecartRevolution.messagesUtil.getMessage("info", "helpMenu")));
        helpCommands.add(colorHelpMessage("/mr delcarts", MinecartRevolution.messagesUtil.getMessage("delcarts", "helpMenu")));
        helpCommands.add(colorHelpMessage("/mr stopcarts", MinecartRevolution.messagesUtil.getMessage("stopcarts", "helpMenu")));
        helpCommands.add(colorHelpMessage("/mr flyer", MinecartRevolution.messagesUtil.getMessage("flyer", "helpMenu")));
        helpCommands.add(colorHelpMessage("/mr vender", MinecartRevolution.messagesUtil.getMessage("vender", "helpMenu")));
        helpCommands.add(colorHelpMessage("/st <STATION>", MinecartRevolution.messagesUtil.getMessage("st", "helpMenu")));
        helpCommands.add(colorHelpMessage("/mr getv", MinecartRevolution.messagesUtil.getMessage("getv", "helpMenu")));
        helpCommands.add(colorHelpMessage("/mr reload", MinecartRevolution.messagesUtil.getMessage("reload", "helpMenu")));
        helpCommands.add(colorHelpMessage("/mr patchregion ", MinecartRevolution.messagesUtil.getMessage("patchregion", "helpMenu")));
        helpCommands.add(colorHelpMessage("/mr compass ", MinecartRevolution.messagesUtil.getMessage("compass", "helpMenu")));
        helpCommands.add(colorHelpMessage("/mr changelog <VERSION> [PAGE]", MinecartRevolution.messagesUtil.getMessage("changelog", "helpMenu")));
        helpCommands.add(colorHelpMessage("/mr checkv [dev]", MinecartRevolution.messagesUtil.getMessage("checkv", "helpMenu")));
        helpCommands.add(colorHelpMessage("/mr update [dev]", MinecartRevolution.messagesUtil.getMessage("update", "helpMenu")));
        helpCommands.add(colorHelpMessage("/mr addons <get; check; install> [ADDON]", MinecartRevolution.messagesUtil.getMessage("addons", "helpMenu")));
        helpCommands.add(colorHelpMessage("/mr translations <check; install> [TRANSLATION]", MinecartRevolution.messagesUtil.getMessage("translations", "helpMenu")));

        for (int counter = 0; counter < mrCommandsShowList.size(); counter++) {
            helpCommands.add(colorHelpMessage("/mr " + mrCommandsShowList.get(counter), MinecartRevolution.messagesUtil.getAddonMessage(mrCommandPluginsList.get(counter), "helpMenu." + mrCommandsList.get(counter))));
        }

        int page = 0;
        if (argument != null) {
            try {
                page = Integer.parseInt(argument) - 1;
            }
            catch (NumberFormatException ex) {
            }
        }
        if (helpCommands.size() < page * 10 || page < 0) {
            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("noHelpPage", "helpMenu"), false);
            return;
        }

        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("panel", "helpMenu").replaceAll("%plugin%", pluginName), true);
        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("websiteLink", "helpMenu"), true);
        int counter = page * 9;
        for (; counter < helpCommands.size() && counter < page * 10 + 9; counter++) {
            MinecartRevolution.messagesUtil.sendMessage(player, helpCommands.get(counter), true);
        }
        if (! (helpCommands.size() < page * 10 + 9)) {
            MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("nextHelpPage", "helpMenu").replaceAll("%page%", String.valueOf(page + 2)), true);
        }
    }

    private void sendInfo(Player player) {

        try {
            MinecartRevolution.messagesUtil.sendMessage(player, ChatColor.YELLOW + "Total " + plugin.getName() + " Downloads: " + ChatColor.DARK_GREEN + (Integer.parseInt(MinecartRevolution.downloadUtil.getFileContent("http://www.minecartrevolution.com/BUILD/RECOMMEND/counter.txt")) + Integer.parseInt(MinecartRevolution.downloadUtil.getFileContent("http://www.minecartrevolution.com/BUILD/RECOMMEND/devbukkit_counter.txt"))), true);
            MinecartRevolution.messagesUtil.sendMessage(player, "", true);
        }
        catch (NumberFormatException ex) {
        }
        MinecartRevolution.messagesUtil.sendMessage(player, ChatColor.GOLD + "MinecartRevolution" + ChatColor.GREEN + " was born at a Tuesday, on 21th February", true);
        MinecartRevolution.messagesUtil.sendMessage(player, ChatColor.GOLD + "2012, and filled with creativity.", true);
        MinecartRevolution.messagesUtil.sendMessage(player, "The first run on Hoppelmann's computer was with only a few", true);
        MinecartRevolution.messagesUtil.sendMessage(player, "lines of code, and it was the beginning of a big thing,", true);
        MinecartRevolution.messagesUtil.sendMessage(player, "this big thing is called " + ChatColor.GOLD + "MinecartRevolution" + ChatColor.WHITE + ", and today it's one", true);
        MinecartRevolution.messagesUtil.sendMessage(player, "of the largest & simplest Minecart plugins ever made.", true);
        MinecartRevolution.messagesUtil.sendMessage(player, ChatColor.AQUA + "Your Minecart, Your way!", true);
        MinecartRevolution.messagesUtil.sendMessage(player, ChatColor.AQUA + "The new Meaning of Minecarts since the " + ChatColor.GOLD + "21th February 2012 ...", true);
    }

    private String colorHelpMessage(String command, String description) {

        return ChatColor.DARK_GREEN + command + ChatColor.BLUE + " > " + ChatColor.DARK_AQUA + description;
    }

    public void addMrCommand(JavaPlugin plugin, String command, String commandShow, String description, MrCommandExecuter commandExecuter) {

        mrCommandsMap.put(command, commandExecuter);
        mrCommandPluginsList.add(plugin);
        mrCommandsList.add(command);
        mrCommandsShowList.add(commandShow);

        MinecartRevolution.messagesUtil.addAddonMessage(plugin, "helpMenu." + command, description);
    }

    private HashMap<String, MrCommandExecuter> mrCommandsMap        = new HashMap<String, MrCommandExecuter>();
    private ArrayList<JavaPlugin>              mrCommandPluginsList = new ArrayList<JavaPlugin>();
    private ArrayList<String>                  mrCommandsList       = new ArrayList<String>();
    private ArrayList<String>                  mrCommandsShowList   = new ArrayList<String>();

}