//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.util;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigUtil {

    public ConfigUtil(MinecartRevolution plugin) {

        this.plugin = plugin;

        loadConfig();
    }

    public void loadConfig() {

        File configFile = new File(this.plugin.getDataFolder(), "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        LinkedHashMap<String, Object> configMap = new LinkedHashMap<String, Object>();

        configMap.put(checkVersionOnJoinPath, checkVersionOnJoin);
        configMap.put(constantMinecartSpeedPath, constantMinecartSpeed);
        configMap.put(prettyControlBlocksPath, prettyControlBlocks);
        configMap.put(pushNearbyEntitiesPath, pushNearbyEntities);
        configMap.put(killPlayerWhenLockedOnQuitPath, killPlayerWhenLockedOnQuit);
        configMap.put(enablePunchPath, enablePunch);
        configMap.put(playEffectsPath, playEffects);
        configMap.put(loadMinecartChunksPath, loadMinecartChunks);
        configMap.put(removeMinecartWhenDerailedTimePath, removeMinecartWhenDerailedTime);
        configMap.put(putMinecartInInvOnQuitPath, putMinecartInInvOnQuit);
        configMap.put(grabBlockDefaultRadiusPath, grabBlockRadius);
        configMap.put(farmMaxRadiusPath, farmMaxRadius);
        configMap.put(timesOverloadedDisablePath, timesOverloadedDisable);

        configMap.put(boosterBlockIdPath, getBlockIdFormat(boosterBlockId));
        configMap.put(breakBlockIdPath, getBlockIdFormat(brakeBlockId));
        configMap.put(reverseBlockIdPath, getBlockIdFormat(reverseBlockId));
        configMap.put(ejectBlockIdPath, getBlockIdFormat(ejectBlockId));
        configMap.put(elevatorBlockIdPath, getBlockIdFormat(elevatorBlockId));
        configMap.put(stationBlockIdPath, getBlockIdFormat(stationBlockId));
        configMap.put(killBlockIdPath, getBlockIdFormat(killBlockId));
        configMap.put(clearInvBlockIdPath, getBlockIdFormat(clearInvBlockId));
        configMap.put(flyBlockIdPath, getBlockIdFormat(flyBlockId));
        configMap.put(healBlockIdPath, getBlockIdFormat(healBlockId));
        configMap.put(grabBlockIdPath, getBlockIdFormat(grabBlockId));
        configMap.put(intersectionBlockIdPath, getBlockIdFormat(intersectionBlockId));

        config.options().header("[" + plugin.getDescription().getName() + "] Plugin by " + "Hoppelmann, Nolig and TutorialMakerHD");
        for (Entry<String, Object> entry : configMap.entrySet()) {
            String path = entry.getKey();
            Object value = entry.getValue();
            if (!config.contains(path)) {
                config.set(path, value);
            }
        }

        try {
            config.save(configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        checkVersionOnJoin = config.getString(checkVersionOnJoinPath);
        constantMinecartSpeed = config.getString(constantMinecartSpeedPath);
        prettyControlBlocks = config.getString(prettyControlBlocksPath);
        pushNearbyEntities = config.getString(pushNearbyEntitiesPath);
        killPlayerWhenLockedOnQuit = config.getString(killPlayerWhenLockedOnQuitPath);
        enablePunch = config.getString(enablePunchPath);
        playEffects = config.getString(playEffectsPath);
        loadMinecartChunks = config.getString(loadMinecartChunksPath);
        removeMinecartWhenDerailedTime = config.getInt(removeMinecartWhenDerailedTimePath);
        putMinecartInInvOnQuit = config.getString(putMinecartInInvOnQuitPath);
        grabBlockRadius = config.getInt(grabBlockDefaultRadiusPath);
        farmMaxRadius = config.getInt(farmMaxRadiusPath);
        timesOverloadedDisable = config.getInt(timesOverloadedDisablePath);

        boosterBlockId = MinecartRevolution.blockUtil.splitBlockData(config.getString(boosterBlockIdPath));
        brakeBlockId = MinecartRevolution.blockUtil.splitBlockData(config.getString(breakBlockIdPath));
        reverseBlockId = MinecartRevolution.blockUtil.splitBlockData(config.getString(reverseBlockIdPath));
        ejectBlockId = MinecartRevolution.blockUtil.splitBlockData(config.getString(ejectBlockIdPath));
        elevatorBlockId = MinecartRevolution.blockUtil.splitBlockData(config.getString(elevatorBlockIdPath));
        stationBlockId = MinecartRevolution.blockUtil.splitBlockData(config.getString(stationBlockIdPath));
        killBlockId = MinecartRevolution.blockUtil.splitBlockData(config.getString(killBlockIdPath));
        clearInvBlockId = MinecartRevolution.blockUtil.splitBlockData(config.getString(clearInvBlockIdPath));
        flyBlockId = MinecartRevolution.blockUtil.splitBlockData(config.getString(flyBlockIdPath));
        healBlockId = MinecartRevolution.blockUtil.splitBlockData(config.getString(healBlockIdPath));
        grabBlockId = MinecartRevolution.blockUtil.splitBlockData(config.getString(grabBlockIdPath));
        intersectionBlockId = MinecartRevolution.blockUtil.splitBlockData(config.getString(intersectionBlockIdPath));
    }

    public void addAddonSetting(JavaPlugin plugin, String path, Object value) {

        PluginDescriptionFile descFile = plugin.getDescription();
        File configFile = new File(this.plugin.getDataFolder() + File.separator + "addons" + File.separator + descFile.getName(), "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        if (!config.contains(path)) {
            config.set(path, value);
        }

        try {
            config.save(configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAddonBlockId(JavaPlugin plugin, String blockName, int blockId, int blockMetadata) {

        String path = "blockId." + blockName;
        int[] blockIds = { blockId, blockMetadata };
        String value = getBlockIdFormat(blockIds);

        addAddonSetting(plugin, path, value);
    }

    public Object getAddonSetting(JavaPlugin plugin, String path) {

        PluginDescriptionFile descFile = plugin.getDescription();
        File configFile = new File(this.plugin.getDataFolder() + File.separator + "addons" + File.separator + descFile.getName(), "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        return config.get(path);
    }

    private String getBlockIdFormat(int[] blockId) {

        String blockIdFormat = "";

        try {
            blockIdFormat += blockId[0];
            if (blockId[1] != -1) {
                blockIdFormat += ":" + blockId[1];
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
        }

        return blockIdFormat;
    }

    MinecartRevolution plugin;

    public String      checkVersionPath                   = "checkVersion";
    public String      checkVersionOnJoinPath             = checkVersionPath + ".onOpJoin";
    public String      minecartPath                       = "minecart";
    public String      constantMinecartSpeedPath          = minecartPath + ".constantMinecartSpeed";
    public String      prettyControlBlocksPath            = minecartPath + ".prettyControlBlocks";
    public String      pushNearbyEntitiesPath             = minecartPath + ".pushNearbyEntities";
    public String      killPlayerWhenLockedOnQuitPath     = minecartPath + ".killPlayerWhenLockedOnQuit";
    public String      enablePunchPath                    = minecartPath + ".enablePunch";
    public String      playEffectsPath                    = minecartPath + ".playEffects";
    public String      loadMinecartChunksPath             = minecartPath + ".loadMinecartChunks";
    public String      removeMinecartWhenDerailedTimePath = minecartPath + ".removeMinecartWhenDerailedTime";
    public String      putMinecartInInvOnQuitPath         = minecartPath + ".putMinecartInInvOnQuit";
    public String      blockSettingsPath                  = "blockSettings";
    public String      grabBlockDefaultRadiusPath         = blockSettingsPath + ".grabBlockDefaultRadius";
    public String      farmMaxRadiusPath                  = blockSettingsPath + ".farmMaxRadius";
    public String      serverPath                         = "server";
    public String      overloadedPath                     = serverPath + ".overloaded";
    public String      timesOverloadedDisablePath         = overloadedPath + ".timesForDisablePlugin";

    public String      databasePath                       = "database";
    public String      databaseTypePath                   = databasePath + ".type";
    public String      databaseHostPath                   = databasePath + ".host";
    public String      databaseNamePath                   = databasePath + ".database";
    public String      databaseUserPath                   = databasePath + ".user";
    public String      databasePasswordPath               = databasePath + ".password";
    public String      databasePortPath                   = databasePath + ".port";

    public String      blockIdPath                        = "blockId";
    public String      boosterBlockIdPath                 = blockIdPath + ".booster";
    public String      breakBlockIdPath                   = blockIdPath + ".break";
    public String      reverseBlockIdPath                 = blockIdPath + ".reverse";
    public String      ejectBlockIdPath                   = blockIdPath + ".eject";
    public String      elevatorBlockIdPath                = blockIdPath + ".elevator";
    public String      stationBlockIdPath                 = blockIdPath + ".station";
    public String      killBlockIdPath                    = blockIdPath + ".kill";
    public String      clearInvBlockIdPath                = blockIdPath + ".clear";
    public String      flyBlockIdPath                     = blockIdPath + ".fly";
    public String      healBlockIdPath                    = blockIdPath + ".heal";
    public String      grabBlockIdPath                    = blockIdPath + ".grab";
    public String      intersectionBlockIdPath            = blockIdPath + ".intersection";

    public String      checkVersionOnJoin                 = "true";
    public String      constantMinecartSpeed              = "false";
    public String      prettyControlBlocks                = "false";
    public String      pushNearbyEntities                 = "true";
    public String      killPlayerWhenLockedOnQuit         = "true";
    public String      enablePunch                        = "true";
    public String      playEffects                        = "true";
    public String      loadMinecartChunks                 = "true";
    public String      putMinecartInInvOnQuit             = "true";
    public int         removeMinecartWhenDerailedTime     = 0;
    public int         grabBlockRadius                    = 5;
    public int         farmMaxRadius                      = 20;
    public int         timesOverloadedDisable             = 3;

    public String      databaseType                       = "FlatFile";
    public String      databaseHost                       = "localhost";
    public String      databaseName                       = "minecraft";
    public String      databaseUser                       = "user";
    public String      databasePassword                   = "password";
    public int         databasePort                       = 10;

    public int[]       boosterBlockId                     = { 41, -1 };
    public int[]       brakeBlockId                       = { 88, -1 };
    public int[]       reverseBlockId                     = { 35, 0 };
    public int[]       ejectBlockId                       = { 42, -1 };
    public int[]       elevatorBlockId                    = { 57, -1 };
    public int[]       stationBlockId                     = { 49, -1 };
    public int[]       killBlockId                        = { 48, -1 };
    public int[]       clearInvBlockId                    = { 22, -1 };
    public int[]       flyBlockId                         = { 24, -1 };
    public int[]       healBlockId                        = { 121, -1 };
    public int[]       grabBlockId                        = { 15, -1 };
    public int[]       intersectionBlockId                = { 45, -1 };

}
