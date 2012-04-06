//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.listener;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class LogListener implements Filter {

    public LogListener(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    @Override
    public boolean isLoggable(LogRecord record) {

        int recordLevel = 0;
        int maxLevel = MinecartRevolution.configUtil.timesOverloadedDisable;

        if (record.getLevel() == Level.INFO) {
            return true;
        }
        if (record.getLevel() == Level.WARNING && record.getMessage().equals("Can't keep up! Did the system time change, or is the server overloaded?")) {
            if ( (maxLevel >= 0)) {
                return true;
            } else {
                if (recordLevel <= maxLevel) {
                    MinecartRevolution.messagesUtil.sendLogMessage(null, "Disabling MinecartRevolution because of lag!", false, "warning");
                    plugin.getPluginLoader().disablePlugin(plugin);

                    return true;
                }
            }
        }
        recordLevel++;
        return true;
    }

    MinecartRevolution plugin;

}