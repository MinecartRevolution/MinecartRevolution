//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.util;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MessagesUtil {

    public MessagesUtil(MinecartRevolution plugin) {

        this.plugin = plugin;

        loadMessages();
    }

    public void loadMessages() {

        File messageFile = new File(plugin.getDataFolder(), "messages.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(messageFile);

        LinkedHashMap<String, String> messageMap = new LinkedHashMap<String, String>();

        messageMap.put("messages.prefix", "[" + ChatColor.GREEN + "MinecartRevolution" + ChatColor.WHITE + "]");

        messageMap.put("messages.placeCtrlBlock.createdBooster", getChatColorToAndCode(ChatColor.GRAY + "You have created a Booster!"));
        messageMap.put("messages.placeCtrlBlock.createdBrake", getChatColorToAndCode(ChatColor.GRAY + "You have created a Brake!"));
        messageMap.put("messages.placeCtrlBlock.createdReverse", getChatColorToAndCode(ChatColor.GRAY + "You have created a Reverse!"));
        messageMap.put("messages.placeCtrlBlock.createdElevator", getChatColorToAndCode(ChatColor.GRAY + "You have created an Elevator part!"));
        messageMap.put("messages.placeCtrlBlock.createdEject", getChatColorToAndCode(ChatColor.GRAY + "You have created an Eject!"));
        messageMap.put("messages.placeCtrlBlock.createdStation", getChatColorToAndCode(ChatColor.GRAY + "You have created a Station block!"));
        messageMap.put("messages.placeCtrlBlock.createdMinecartDestroyer", getChatColorToAndCode(ChatColor.GRAY + "You have created a Minecart Destroyer block!"));
        messageMap.put("messages.placeCtrlBlock.createdInvClear", getChatColorToAndCode(ChatColor.GRAY + "You have created an Inv Clear block!"));
        messageMap.put("messages.placeCtrlBlock.createdFly", getChatColorToAndCode(ChatColor.GRAY + "You have created a Fly block!"));
        messageMap.put("messages.placeCtrlBlock.createdHeal", getChatColorToAndCode(ChatColor.GRAY + "You have created a Heal block!"));
        messageMap.put("messages.placeCtrlBlock.createdGrab", getChatColorToAndCode(ChatColor.GRAY + "You have created a Grab block!"));
        messageMap.put("messages.placeCtrlBlock.createdIntersection", getChatColorToAndCode(ChatColor.GRAY + "You have created an Intersection block!"));
        messageMap.put("messages.placeCtrlSign.createdStationSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Station sign!"));
        messageMap.put("messages.placeCtrlSign.createdHoldSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Station hold sign!"));
        messageMap.put("messages.placeCtrlSign.createdTrainStationSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Train Station sign!"));
        messageMap.put("messages.placeCtrlSign.createdAnnounceSign", getChatColorToAndCode(ChatColor.GRAY + "You have created an Announce sign!"));
        messageMap.put("messages.placeCtrlSign.createdSpawnSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Spawn sign!"));
        messageMap.put("messages.placeCtrlSign.createdFlyBoostSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Fly Boost sign!"));
        messageMap.put("messages.placeCtrlSign.createdChestSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Chest sign!"));
        messageMap.put("messages.placeCtrlSign.createdCollectSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Collect sign!"));
        messageMap.put("messages.placeCtrlSign.createdCraftSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Craft sign!"));
        messageMap.put("messages.placeCtrlSign.createdSmeltSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Smelt sign!"));
        messageMap.put("messages.placeCtrlSign.createdFarmSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Farm sign!"));
        messageMap.put("messages.placeCtrlSign.createdGrabSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Grab sign!"));
        messageMap.put("messages.placeCtrlSign.createdLockSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Lock sign!"));
        messageMap.put("messages.placeCtrlSign.createdMaxspeedSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Maxspeed sign!"));
        messageMap.put("messages.placeCtrlSign.createdKillSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Kill sign!"));
        messageMap.put("messages.placeCtrlSign.createdTimeSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Time sign!"));
        messageMap.put("messages.placeCtrlSign.createdWeatherSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Weather sign!"));
        messageMap.put("messages.placeCtrlSign.createdSensorSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Sensor sign!"));
        messageMap.put("messages.placeCtrlSign.createdIntersectionSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Intersection sign!"));
        messageMap.put("messages.placeCtrlSign.createdEjectSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Eject sign!"));
        messageMap.put("messages.placeCtrlSign.createdCommandSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Command sign!"));
        messageMap.put("messages.placeCtrlSign.createdDescentSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Descent sign!"));
        messageMap.put("messages.placeCtrlSign.createdDestinationSign", getChatColorToAndCode(ChatColor.GRAY + "You have created a Destination sign!"));
        messageMap.put("messages.placeCtrlSign.createdEffectSign", getChatColorToAndCode(ChatColor.GRAY + "You have created an Effect sign!"));
        messageMap.put("messages.placeCtrlSign.createdInvClearSign", getChatColorToAndCode(ChatColor.GRAY + "You have created an InvClear sign!"));

        messageMap.put("messages.destroyedCtrlBlock.destroyedBooster", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Booster!"));
        messageMap.put("messages.destroyedCtrlBlock.destroyedBrake", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Brake!"));
        messageMap.put("messages.destroyedCtrlBlock.destroyedReverse", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Reverse!"));
        messageMap.put("messages.destroyedCtrlBlock.destroyedElevator", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed an Elevator part!"));
        messageMap.put("messages.destroyedCtrlBlock.destroyedEject", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed an Eject!"));
        messageMap.put("messages.destroyedCtrlBlock.destroyedStation", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Station block!"));
        messageMap.put("messages.destroyedCtrlBlock.destroyedMinecartDestroyer", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Minecart Destroyer block!"));
        messageMap.put("messages.destroyedCtrlBlock.destroyedInvClear", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed an Inv Clear block!"));
        messageMap.put("messages.destroyedCtrlBlock.destroyedFly", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Fly block!"));
        messageMap.put("messages.destroyedCtrlBlock.destroyedHeal", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Heal block!"));
        messageMap.put("messages.destroyedCtrlBlock.destroyedGrab", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Grab block!"));
        messageMap.put("messages.destroyedCtrlBlock.destroyedIntersection", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed an Intersection block!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedStationSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Station sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedHoldSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Station hold sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedTrainStationSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Train Station sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedAnnounceSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed an Announce sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedSpawnSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Spawn sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedFlyBoostSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Fly Boost sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedChestSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Chest sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedCollectSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Collect sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedCraftSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Craft sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedSmeltSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Smelt sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedFarmSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Farm sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedGrabSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Grab sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedLockSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Lock sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedMaxspeedSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Maxspeed sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedKillSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Kill sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedTimeSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Time sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedWeatherSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Weather sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedSensorSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Sensor sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedIntersectionSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Intersection sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedEjectSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Eject sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedCommandSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Command sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedDescentSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Descent sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedDestinationSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed a Destination sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedEffectSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed an Effect sign!"));
        messageMap.put("messages.destroyedCtrlSign.destroyedInvClearSign", getChatColorToAndCode(ChatColor.GRAY + "You have destroyed an InvClear sign!"));

        messageMap.put("messages.enable.addon", getChatColorToAndCode("Successfully enabled addon %addon% for %plugin%!"));
        messageMap.put("messages.webError.404", getChatColorToAndCode(ChatColor.DARK_RED + "Error: Code %code%" + ChatColor.WHITE + " - " + ChatColor.RED + "The required file doesn't exists!"));
        messageMap.put("messages.webError.500", getChatColorToAndCode(ChatColor.DARK_RED + "Error: Code %code%" + ChatColor.WHITE + " - " + ChatColor.RED + "The MinecartRevolution Servers are currently not available, try again later!"));
        messageMap.put("messages.warning.devBuildPart1", getChatColorToAndCode(ChatColor.RED + "WARNING: DEV-BUILDS AREN'T RECOMMEND. It may cause that the world will break."));
        messageMap.put("messages.warning.devBuildPart2", getChatColorToAndCode(ChatColor.RED + "To come back to a RC-Build, type /mr update"));
        messageMap.put("messages.update.check.newVersion", getChatColorToAndCode(ChatColor.GOLD + "A new version, " + ChatColor.DARK_AQUA + "%version%" + ChatColor.GOLD + ", is available! Download now with /mr update"));
        messageMap.put("messages.update.check.newDevVersion", getChatColorToAndCode(ChatColor.GOLD + "A new INSTABLE DEV version, " + ChatColor.DARK_AQUA + "%version%" + ChatColor.GOLD + ", is available! Download now with /mr update dev"));
        messageMap.put("messages.update.check.noNewVersion", getChatColorToAndCode(ChatColor.GREEN + "All is fine, you're using the newest version of MinecartRevolution, thank you!"));
        messageMap.put("messages.update.updater.panel", getChatColorToAndCode(ChatColor.YELLOW + "-----------" + ChatColor.WHITE + "[" + ChatColor.GREEN + plugin.getDescription().getName() + " - Updater" + ChatColor.WHITE + "]" + ChatColor.YELLOW + "-----------"));
        messageMap.put("messages.update.updater.startDownload", getChatColorToAndCode("The download has started ..."));
        messageMap.put("messages.update.updater.endDownload", getChatColorToAndCode("Successfully downloaded: %plugin%.jar!"));
        messageMap.put("messages.update.updater.startInstallation", getChatColorToAndCode("Please wait! The installation starts now..."));
        messageMap.put("messages.update.updater.endInstallation", getChatColorToAndCode("... done!"));
        messageMap.put("messages.update.updater.endUpdate", getChatColorToAndCode("%plugin% was updated successfully!"));
        messageMap.put("messages.update.updater.error", getChatColorToAndCode(ChatColor.RED + "Update cancelled! See info below:"));
        messageMap.put("messages.addons.get.list", getChatColorToAndCode("Installed and enabled MinecartRevolution Addons:"));
        messageMap.put("messages.addons.get.noAddons", getChatColorToAndCode(ChatColor.RED + "There aren't any addons installed yet! Search for addons with /mr addons check!"));
        messageMap.put("messages.addons.check.list", getChatColorToAndCode("Installable MinecartRevolution Addons:"));
        messageMap.put("messages.addons.newVersion", getChatColorToAndCode(ChatColor.GOLD + "A new version, " + ChatColor.DARK_AQUA + "%version%" + ChatColor.GOLD + ", is available! Download now with /mr addons install %addon%"));
        messageMap.put("messages.addons.noNewVersion", getChatColorToAndCode(ChatColor.GREEN + "All is fine, you're using the newest version of %addon%, thank you!"));
        messageMap.put("messages.addons.installer.panel", getChatColorToAndCode(ChatColor.YELLOW + "----------" + ChatColor.WHITE + "[" + ChatColor.GREEN + plugin.getDescription().getName() + " - Addon Installer" + ChatColor.WHITE + "]" + ChatColor.YELLOW + "----------"));
        messageMap.put("messages.addons.installer.startDownload", getChatColorToAndCode("The download has started ..."));
        messageMap.put("messages.addons.installer.endDownload", getChatColorToAndCode("Successfully downloaded: %addon%.jar!"));
        messageMap.put("messages.addons.installer.startInstallation", getChatColorToAndCode("Please wait! The installation starts now..."));
        messageMap.put("messages.addons.installer.endInstallation", getChatColorToAndCode("... done!"));
        messageMap.put("messages.addons.installer.endInstaller", getChatColorToAndCode("%addon% was installed successfully!"));
        messageMap.put("messages.addons.installer.error", getChatColorToAndCode(ChatColor.RED + "Installation cancelled! See info below:"));
        messageMap.put("messages.addons.wrongSyntax", getChatColorToAndCode(ChatColor.RED + "You have to type an action: /mr addons <get; check; install> [ADDON]"));
        messageMap.put("messages.translations.check.list", getChatColorToAndCode("Installable MinecartRevolution Translations:"));
        messageMap.put("messages.translations.wrongSyntax", getChatColorToAndCode(ChatColor.RED + "You have to type an action: /mr translations <check; install> [TRANSLATION]"));
        messageMap.put("messages.changelog.panel", getChatColorToAndCode("---------- " + ChatColor.LIGHT_PURPLE + "MinecartRevolution Changelog" + ChatColor.WHITE + " ----------"));
        messageMap.put("messages.changelog.wrongSyntax.noVersion", getChatColorToAndCode(ChatColor.RED + "You have to type a version: /mr changelog <VERSION>"));
        messageMap.put("messages.changelog.wrongSyntax.noPage", getChatColorToAndCode(ChatColor.RED + "You have to type a page: /mr changelog <VERSION> <PAGE>"));
        messageMap.put("messages.changelog.wrongSyntax.wrongPage", getChatColorToAndCode(ChatColor.RED + "You have to type a number as page ;)"));
        messageMap.put("messages.changelog.wrongSyntax.pageDoesntExist", getChatColorToAndCode(ChatColor.RED + "This page doesn't exists!"));
        messageMap.put("messages.patchregion.attention", getChatColorToAndCode(ChatColor.RED + "Attention! The Patcher can cause the server to crash. All worlds will saved now!"));
        messageMap.put("messages.patchregion.replaced", getChatColorToAndCode(ChatColor.GREEN + "You replaced " + ChatColor.GOLD + "%amount%" + ChatColor.GREEN + " MinecartMania- and TrainCarts-signs with MinecartRevolution-signs."));
        messageMap.put("messages.compass.direction.north", getChatColorToAndCode("You look to the north."));
        messageMap.put("messages.compass.direction.east", getChatColorToAndCode("You look to the east."));
        messageMap.put("messages.compass.direction.south", getChatColorToAndCode("You look to the south."));
        messageMap.put("messages.compass.direction.west", getChatColorToAndCode("You look to the west."));
        messageMap.put("messages.helpMenu.panel", getChatColorToAndCode(ChatColor.GRAY + "===== " + ChatColor.GOLD + "%plugin%" + ChatColor.AQUA + " HELP" + ChatColor.GRAY + " ====="));
        messageMap.put("messages.helpMenu.websiteLink", getChatColorToAndCode(ChatColor.GRAY + "Visit the Website/Wiki: " + ChatColor.GOLD + "http://www.minecartrevolution.com"));
        messageMap.put("messages.helpMenu.noHelpPage", getChatColorToAndCode(ChatColor.RED + "This help page doesn't exists!"));
        messageMap.put("messages.helpMenu.nextHelpPage", getChatColorToAndCode(ChatColor.GRAY + "Next help page: /mr %page%"));
        messageMap.put("messages.helpMenu.mr", getChatColorToAndCode("Shows you this help menu."));
        messageMap.put("messages.helpMenu.info", getChatColorToAndCode("Shows you information about the plugin."));
        messageMap.put("messages.helpMenu.delcarts", getChatColorToAndCode("Removes all minecarts in your current world."));
        messageMap.put("messages.helpMenu.stopcarts", getChatColorToAndCode("Stops all minecarts in your current world."));
        messageMap.put("messages.helpMenu.flyer", getChatColorToAndCode("Turns you into the minecart FlyCart mode."));
        messageMap.put("messages.helpMenu.vender", getChatColorToAndCode("Sets you into a minecart, when you're over a track."));
        messageMap.put("messages.helpMenu.st", getChatColorToAndCode("Sets your station word for intersections and sensors."));
        messageMap.put("messages.helpMenu.getv", getChatColorToAndCode("Shows you your current version of MinecartRevolution."));
        messageMap.put("messages.helpMenu.reload", getChatColorToAndCode("Reloads the MinecartRevolution plugin (configs etc.)."));
        messageMap.put("messages.helpMenu.patchregion", getChatColorToAndCode("Replaces all MinecartMania- and TrainCarts-signs with MinecartRevolution-signs."));
        messageMap.put("messages.helpMenu.compass", getChatColorToAndCode("Shows you the direction (N, E, S or W) you're looking."));
        messageMap.put("messages.helpMenu.changelog", getChatColorToAndCode("Shows you the changelog of the version <VERSION> (for beta: Beta-<VERSION>)."));
        messageMap.put("messages.helpMenu.checkv", getChatColorToAndCode("Checks whether you have the latest version (Recommend Build) of MinecartRevolution."));
        messageMap.put("messages.helpMenu.update", getChatColorToAndCode("Updates the plugin to the newest version."));
        messageMap.put("messages.helpMenu.addons", getChatColorToAndCode("Gets your addons, checks all available addons; install an addon."));
        messageMap.put("messages.helpMenu.translations", getChatColorToAndCode("Checks all available translations; install a translation."));
        messageMap.put("messages.removedMinecarts", getChatColorToAndCode(ChatColor.GREEN + "Successfully removed all minecarts!"));
        messageMap.put("messages.removedMinecartsBroadcast", getChatColorToAndCode(ChatColor.DARK_GREEN + "%player% removed all minecarts!"));
        messageMap.put("messages.stoppedMinecarts", getChatColorToAndCode(ChatColor.GREEN + "Successfully stopped all minecarts!"));
        messageMap.put("messages.stoppedMinecartsBroadcast", getChatColorToAndCode(ChatColor.DARK_GREEN + "%player% stopped all minecarts!"));
        messageMap.put("messages.getVersion", getChatColorToAndCode("Your MinecartRevolution version: %version%"));
        messageMap.put("messages.st", getChatColorToAndCode(ChatColor.AQUA + "Station successfully set!"));
        messageMap.put("messages.reloaded", getChatColorToAndCode("All configs successfully reloaded!"));
        messageMap.put("messages.cantExitHere", getChatColorToAndCode(ChatColor.GRAY + "You can't exit here!"));
        messageMap.put("messages.lockExit", getChatColorToAndCode(ChatColor.GRAY + "You can't exit now!"));
        messageMap.put("messages.unlockExit", getChatColorToAndCode(ChatColor.GRAY + "You can exit now!"));
        messageMap.put("messages.punch", getChatColorToAndCode(ChatColor.GRAY + "Tap one side of the minecart to move!"));
        messageMap.put("messages.haveToBeOnRails", getChatColorToAndCode(ChatColor.GRAY + "You have to be on rails!"));
        messageMap.put("messages.cantBeInVehicle", getChatColorToAndCode(ChatColor.GRAY + "You can not be in vehicle, when you want to perfom this command!"));
        messageMap.put("messages.haveToBeInMinecart", getChatColorToAndCode(ChatColor.GRAY + "You have to be in a minecart to do this!"));
        messageMap.put("messages.flyCartEnable", getChatColorToAndCode(ChatColor.GRAY + "You're now in Fly Cart mode!"));
        messageMap.put("messages.flyCartDisable", getChatColorToAndCode(ChatColor.GRAY + "No longer in Fly Cart mode!"));
        messageMap.put("messages.noPermission", getChatColorToAndCode(ChatColor.RED + "You haven't the permissions to do this!"));
        messageMap.put("messages.cantPerformOnConsole", getChatColorToAndCode("You can't perform this command in console!"));

        config.options().header("[" + plugin.getDescription().getName() + "] Plugin by " + "Hoppelmann, Nolig and TutorialMakerHD / Color-Codes: http://tinyurl.com/MR-Color-Codes");
        for (Entry<String, String> entry : messageMap.entrySet()) {
            String path = entry.getKey();
            String message = entry.getValue();
            if (!config.contains(path)) {
                config.set(path, message);
            }
        }

        try {
            config.save(messageFile);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addAddonMessage(JavaPlugin plugin, String path, String message) {

        PluginDescriptionFile descFile = plugin.getDescription();
        File configFile = new File(this.plugin.getDataFolder() + File.separator + "addons" + File.separator + descFile.getName(), "messages.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        path = "messages." + path;

        if (!config.contains(path)) {
            config.set(path, getChatColorToAndCode(message));
        }

        try {
            config.save(configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAddonControlBlockMessages(JavaPlugin plugin, String name, String placeMessage, String destroyMessage) {

        PluginDescriptionFile descFile = plugin.getDescription();
        File configFile = new File(this.plugin.getDataFolder() + File.separator + "addons" + File.separator + descFile.getName(), "messages.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        String path = "messages.placeCtrlBlock.created" + name;
        if (!config.contains(path)) {
            config.set(path, getChatColorToAndCode(placeMessage));
        }
        path = "messages.destroyedCtrlBlock.destroyed" + name;
        if (!config.contains(path)) {
            config.set(path, getChatColorToAndCode(destroyMessage));
        }

        try {
            config.save(configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAddonControlSignMessages(JavaPlugin plugin, String name, String placeMessage, String destroyMessage) {

        PluginDescriptionFile descFile = plugin.getDescription();
        File configFile = new File(this.plugin.getDataFolder() + File.separator + "addons" + File.separator + descFile.getName(), "messages.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        String path = "messages.placeCtrlSign.created" + name;
        if (!config.contains(path)) {
            config.set(path, getChatColorToAndCode(placeMessage));
        }
        path = "messages.destroyedCtrlSign.destroyed" + name;
        if (!config.contains(path)) {
            config.set(path, getChatColorToAndCode(destroyMessage));
        }

        try {
            config.save(configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getChatColorToAndCode(String message) {

        return message.replaceAll("§", "&");
    }

    public String formatMessage(String message) {

        File messageFile = new File(plugin.getDataFolder(), "messages.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(messageFile);

        return config.getString("messages.prefix") + " " + message;
    }

    public String getMessage(String message, String type) {

        File messageFile = new File(plugin.getDataFolder(), "messages.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(messageFile);

        String messagePath = "messages.";
        if (type != "") {
            messagePath = messagePath + type + ".";
        }
        messagePath = messagePath + message;
        return config.getString(messagePath);
    }

    public String getAddonMessage(JavaPlugin plugin, String path) {

        File configFile = new File(this.plugin.getDataFolder() + File.separator + "addons" + File.separator + plugin.getDescription().getName() + File.separator + "messages.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        return config.getString("messages." + path);
    }

    public void sendMessage(Player player, String message, boolean noPrefix) {

        if (!noPrefix) {
            message = MinecartRevolution.messagesUtil.formatMessage(message);
        }
        if (player != null) {
            player.sendMessage(message.replaceAll("(?i)&([a-n0-9])", "\u00A7$1"));
        } else {
            System.out.println(message.replaceAll("(?i)&([a-n0-9])", "").replaceAll("§[a-n0-9]", ""));
        }
    }

    public void sendLogMessage(Player player, String message, boolean noPrefix, String level) {

        if (level.equalsIgnoreCase("info")) {
            if (player == null) {
                MinecartRevolution.mrLogger.info(formatMessage(message.replaceAll("(?i)&([a-n0-9])", "")).replaceAll("§[a-n0-9]", ""));
            } else {
                sendMessage(player, message, noPrefix);
            }
        } else if (level.equalsIgnoreCase("warning")) {
            if (player == null) {
                MinecartRevolution.mrLogger.warning(formatMessage(message.replaceAll("(?i)&([a-n0-9])", "")).replaceAll("§[a-n0-9]", ""));
            } else {
                sendMessage(player, message, noPrefix);
            }
        }
    }

    public void broadcastMessage(String message, boolean noPrefix) {

        if (!noPrefix) {
            message = MinecartRevolution.messagesUtil.formatMessage(message);
        }

        Bukkit.broadcastMessage(message.replaceAll("(?i)&([a-n0-9])", "\u00A7$1"));
    }

    MinecartRevolution plugin;

}
