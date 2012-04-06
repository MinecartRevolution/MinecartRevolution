//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import de.MRTeam.MinecartRevolution.action.BlockAction;
import de.MRTeam.MinecartRevolution.action.BlockPlaceAction;
import de.MRTeam.MinecartRevolution.action.SignAction;
import de.MRTeam.MinecartRevolution.action.SignPlaceAction;
import de.MRTeam.MinecartRevolution.addon.Addon;
import de.MRTeam.MinecartRevolution.listener.BlockListener;
import de.MRTeam.MinecartRevolution.listener.LogListener;
import de.MRTeam.MinecartRevolution.listener.MinecartListener;
import de.MRTeam.MinecartRevolution.listener.PlayerListener;
import de.MRTeam.MinecartRevolution.util.BlockUtil;
import de.MRTeam.MinecartRevolution.util.CommandExecuterUtil;
import de.MRTeam.MinecartRevolution.util.ConfigUtil;
import de.MRTeam.MinecartRevolution.util.DownloadUtil;
import de.MRTeam.MinecartRevolution.util.FlyCartUtil;
import de.MRTeam.MinecartRevolution.util.ItemAliasUtil;
import de.MRTeam.MinecartRevolution.util.LogUtil;
import de.MRTeam.MinecartRevolution.util.MessagesUtil;
import de.MRTeam.MinecartRevolution.util.PatchRegionUtil;
import de.MRTeam.MinecartRevolution.util.PathFindUtil;
import de.MRTeam.MinecartRevolution.util.PermissionUtil;
import de.MRTeam.MinecartRevolution.util.PlayEffectUtil;
import de.MRTeam.MinecartRevolution.util.RemoveDerailedCartsUtil;
import de.MRTeam.MinecartRevolution.util.UpdateUtil;
import de.MRTeam.MinecartRevolution.util.database.DatabaseConnectionUtil;
import de.MRTeam.MinecartRevolution.util.database.DatabaseDestinationLocationUtil;
import de.MRTeam.MinecartRevolution.util.database.DatabaseUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecartRevolution extends JavaPlugin {

    @Override
    public void onDisable() {

        messagesUtil.sendLogMessage(null, "Successfully deactivated plugin", false, "info");
    }

    @Override
    public void onEnable() {

        register();
        new File(getDataFolder(), "saves").mkdirs();
        messagesUtil.sendLogMessage(null, "Successfully activated plugin", false, "info");
    }

    private void register() {

        descFile = getDescription();

        databaseUtil = new DatabaseUtil(this);
        databaseConnectionUtil = new DatabaseConnectionUtil(this);
        databaseDestinationLocationUtil = new DatabaseDestinationLocationUtil(this);

        downloadUtil = new DownloadUtil(this);
        updateUtil = new UpdateUtil(this);
        blockUtil = new BlockUtil(this);
        configUtil = new ConfigUtil(this);
        messagesUtil = new MessagesUtil(this);
        flyCart = new FlyCartUtil(this);
        commandExecutor = new CommandExecuterUtil(this);
        permissionUtil = new PermissionUtil(this);
        pathFindUtil = new PathFindUtil(this);
        patchRegionUtil = new PatchRegionUtil(this);
        playEffectUtil = new PlayEffectUtil(this);
        removeDerailedCartsUtil = new RemoveDerailedCartsUtil();
        itemAliasUtil = new ItemAliasUtil(this);
        logUtil = new LogUtil(this);

        minecartListener = new MinecartListener(this);
        blockListener = new BlockListener(this);
        playerListener = new PlayerListener(this);
        logListener = new LogListener(this);

        blockAction = new BlockAction(this);
        signAction = new SignAction(this);
        blockPlaceAction = new BlockPlaceAction(this);
        signPlaceAction = new SignPlaceAction(this);

        addon = new Addon(this);

        Bukkit.getPluginCommand("mr").setExecutor(commandExecutor);
        Bukkit.getPluginCommand("st").setExecutor(commandExecutor);

        mrLogger = Logger.getLogger(descFile.getName());
        mrLogger.setParent(Logger.getLogger("Minecraft"));
        mrLogger.setFilter(logUtil);
        minecraftLogger = Logger.getLogger("Minecraft");
        minecraftLogger.setFilter(logListener);
    }

    public static PluginDescriptionFile           descFile;

    public static DatabaseUtil                    databaseUtil;
    public static DatabaseConnectionUtil          databaseConnectionUtil;
    public static DatabaseDestinationLocationUtil databaseDestinationLocationUtil;

    public static ConfigUtil                      configUtil;
    public static MessagesUtil                    messagesUtil;
    public static DownloadUtil                    downloadUtil;
    public static UpdateUtil                      updateUtil;
    public static CommandExecuterUtil             commandExecutor;
    public static FlyCartUtil                     flyCart;
    public static BlockUtil                       blockUtil;
    public static PermissionUtil                  permissionUtil;
    public static PathFindUtil                    pathFindUtil;
    public static PatchRegionUtil                 patchRegionUtil;
    public static PlayEffectUtil                  playEffectUtil;
    public static RemoveDerailedCartsUtil         removeDerailedCartsUtil;
    public static ItemAliasUtil                   itemAliasUtil;
    public static LogUtil                         logUtil;

    public static MinecartListener                minecartListener;
    public static PlayerListener                  playerListener;
    public static BlockListener                   blockListener;
    public static LogListener                     logListener;

    public static BlockAction                     blockAction;
    public static SignAction                      signAction;
    public static BlockPlaceAction                blockPlaceAction;
    public static SignPlaceAction                 signPlaceAction;

    public static Addon                           addon;

    public static Logger                          mrLogger;
    public static Logger                          minecraftLogger;

    public static HashMap<String, JavaPlugin>     addons          = new HashMap<String, JavaPlugin>();
    public static ArrayList<JavaPlugin>           addonsIndexList = new ArrayList<JavaPlugin>();

}
