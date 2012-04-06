//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.util;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class UpdateUtil {

    MinecartRevolution plugin;

    public UpdateUtil(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    public String checkVersion(boolean dev, boolean returnAll) {

        String versionString = "";
        if (dev) {
            versionString = MinecartRevolution.downloadUtil.getFileContent("http://www.minecartrevolution.com/BUILD/DEV/ver.txt");
        } else {
            versionString = MinecartRevolution.downloadUtil.getFileContent("http://www.minecartrevolution.com/BUILD/RECOMMEND/ver.txt");
        }

        if (versionString.contains("ERROR")) {
            return MinecartRevolution.downloadUtil.checkErrorCodes(versionString, returnAll);
        } else {
            if (compareVersions(versionString, this.plugin.getDescription().getVersion())) {
                if (dev) {
                    return MinecartRevolution.messagesUtil.getMessage("check.newDevVersion", "update").replaceAll("%version%", versionString);
                } else {
                    return MinecartRevolution.messagesUtil.getMessage("check.newVersion", "update").replaceAll("%version%", versionString);
                }
            } else {
                if (returnAll) {
                    return MinecartRevolution.messagesUtil.getMessage("check.noNewVersion", "update");
                } else {
                    return null;
                }
            }
        }
    }

    public boolean compareVersions(String version1, String version2) {

        String[] version1Array1 = version1.split(" ");
        String[] version2Array1 = version2.split(" ");

        int version1Index = 0;
        int version2Index = 0;
        if (version1.contains("Beta ")) {
            version1Index = 1;
        }
        if (version2.contains("Beta ")) {
            version2Index = 1;
        }

        if (version1.contains("Beta ") && !version2.contains("Beta ")) {
            return false;
        } else if (!version1.contains("Beta ") && version2.contains("Beta ")) {
            return true;
        }

        String[] version1Array2 = version1Array1[version1Index].split("\\.");
        String[] version2Array2 = version2Array1[version2Index].split("\\.");

        try {
            for (int counter = 0; counter < version1Array2.length && counter < version2Array2.length; counter++) {
                if (Integer.parseInt(version1Array2[counter]) > Integer.parseInt(version2Array2[counter])) {
                    return true;
                }
            }
        }
        catch (NumberFormatException ex) {
        }

        return false;
    }

    public void update(Player player, boolean dev) {

        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("updater.panel", "update"), true);

        String urlString = "";
        if (dev) {
            urlString = "http://www.minecartrevolution.com/BUILD/DEV/MinecartRevolution.jar";
        } else {
            urlString = "http://www.minecartrevolution.com/BUILD/RECOMMEND/MinecartRevolution.jar";
        }

        MinecartRevolution.downloadUtil.touchURL("http://www.minecartrevolution.com/BUILD/RECOMMEND/counter.php");
        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("updater.startDownload", "update"), true);
        try {
            String downloadInfo = MinecartRevolution.downloadUtil.downloadFile(urlString, new FileOutputStream("plugins" + File.separator + "MinecartRevolution.jar"));

            if (downloadInfo != null && downloadInfo.contains("ERROR")) {
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("updater.error", "update"), true);
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.downloadUtil.checkErrorCodes(downloadInfo, true), true);
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("updater.panel", "update"), true);
            } else {
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("updater.endDownload", "update").replaceAll("%plugin%", plugin.getDescription().getName()), true);
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("updater.startInstallation", "update"), true);
                plugin.getServer().reload();
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("updater.endInstallation", "update"), true);
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("updater.endUpdate", "update").replaceAll("%plugin%", plugin.getDescription().getName()), true);
                if (dev) {
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("devBuildPart1", "warning"), true);
                    MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("devBuildPart2", "warning"), true);
                }
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("updater.panel", "update"), true);
            }
        }

        catch (FileNotFoundException ex) {
        }
    }

    public String[] getAddons(Player player) {

        try {
            String addonsString = MinecartRevolution.downloadUtil.getFileContent("http://www.minecartrevolution.com/ADDONS/addons.txt");

            if (addonsString.contains("ERROR")) {
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.downloadUtil.checkErrorCodes(addonsString, true), false);
                return null;
            } else {
                return addonsString.split("\\* ");
            }
        }
        catch (Exception ex) {
        }
        return null;
    }

    public void installAddon(Player player, String addon) {

        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("installer.panel", "addons"), true);

        String urlString = "http://www.minecartrevolution.com/ADDONS/BUILD/" + addon + ".jar";

        MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("installer.startDownload", "addons"), true);
        try {
            String downloadInfo = MinecartRevolution.downloadUtil.downloadFile(urlString, new FileOutputStream("plugins" + File.separator + addon + ".jar"));

            if (downloadInfo != null && downloadInfo.contains("ERROR")) {
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("installer.error", "addons"), true);
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.downloadUtil.checkErrorCodes(downloadInfo, true), true);
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("installer.panel", "addons"), true);
            } else {
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("installer.endDownload", "addons").replaceAll("%addon%", addon), true);
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("installer.startInstallation", "addons"), true);
                plugin.getServer().reload();
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("installer.endInstallation", "addons"), true);
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("installer.endInstaller", "addons").replaceAll("%addon%", addon), true);
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.messagesUtil.getMessage("installer.panel", "addons"), true);
            }
        }
        catch (FileNotFoundException ex) {
        }
    }

    public String checkAddonVersion(Player player, String addon, boolean returnAll) {

        String latestAddonVersion = MinecartRevolution.downloadUtil.getFileContent("http://www.minecartrevolution.com/ADDONS/BUILD/" + addon + "ver.txt");
        if (latestAddonVersion == null) {
            return null;
        }

        if (compareVersions(latestAddonVersion, MinecartRevolution.addons.get(addon).getDescription().getVersion())) {
            return MinecartRevolution.messagesUtil.getMessage("newVersion", "addons").replaceAll("%version%", latestAddonVersion).replaceAll("%addon%", addon);
        } else {
            if (returnAll) {
                return MinecartRevolution.messagesUtil.getMessage("noNewVersion", "addons").replaceAll("%addon%", addon);
            } else {
                return null;
            }
        }
    }

    public String[] getTranslations(Player player) {

        try {
            String translationsString = MinecartRevolution.downloadUtil.getFileContent("http://www.minecartrevolution.com/TRANSLATIONS/translations.txt");
            if (translationsString.contains("ERROR")) {
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.downloadUtil.checkErrorCodes(translationsString, true), false);
                return null;
            } else {
                return translationsString.split("\\* ");
            }
        }
        catch (Exception ex) {
        }
        return null;
    }

    public void installTranslation(Player player, String translation) {

        MinecartRevolution.messagesUtil.sendMessage(player, ChatColor.YELLOW + "--------" + ChatColor.WHITE + "[" + ChatColor.GREEN + plugin.getDescription().getName() + " - Translation Installer" + ChatColor.WHITE + "]" + ChatColor.YELLOW + "--------", true);

        String urlString = "http://www.minecartrevolution.com/TRANSLATIONS/BUILD/" + translation + "/messages.yml";

        MinecartRevolution.messagesUtil.sendMessage(player, "The download has started ...", true);
        try {
            String downloadInfo = MinecartRevolution.downloadUtil.downloadFile(urlString, new FileOutputStream(plugin.getDataFolder() + File.separator + "messages.yml"));

            if (downloadInfo != null && downloadInfo.contains("ERROR")) {
                MinecartRevolution.messagesUtil.sendMessage(player, ChatColor.RED + "Installation cancelled! See info below:", true);
                MinecartRevolution.messagesUtil.sendMessage(player, MinecartRevolution.downloadUtil.checkErrorCodes(downloadInfo, true), true);
                MinecartRevolution.messagesUtil.sendMessage(player, ChatColor.YELLOW + "--------" + ChatColor.WHITE + "[" + ChatColor.GREEN + plugin.getDescription().getName() + " - Translation Installer" + ChatColor.WHITE + "]" + ChatColor.YELLOW + "--------", true);
            } else {
                MinecartRevolution.messagesUtil.sendMessage(player, "Successfully downloaded: messages.yml!", true);
                MinecartRevolution.messagesUtil.sendMessage(player, "Please wait! The installation starts now...", true);
                plugin.getServer().reload();
                MinecartRevolution.messagesUtil.sendMessage(player, "... done!", true);
                MinecartRevolution.messagesUtil.sendMessage(player, translation + " translation was installed successfully!", true);
                MinecartRevolution.messagesUtil.sendMessage(player, ChatColor.YELLOW + "--------" + ChatColor.WHITE + "[" + ChatColor.GREEN + plugin.getDescription().getName() + " - Translation Installer" + ChatColor.WHITE + "]" + ChatColor.YELLOW + "--------", true);
            }
        }
        catch (FileNotFoundException ex) {
        }
    }
}
