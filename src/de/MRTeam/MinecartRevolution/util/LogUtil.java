//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class LogUtil implements Filter {

    public LogUtil(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    public boolean isLoggable(LogRecord record) {

        try {
            File logFile = new File(plugin.getDataFolder(), "minecart-revolution.log");
            String oldLog = "";

            if (logFile.exists()) {
                FileInputStream inputStream = new FileInputStream(logFile);
                oldLog = new Scanner(inputStream).useDelimiter("\\Z").next();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(oldLog + record.getMessage().trim() + "\n\r");
            writer.close();
        }
        catch (Exception ex) {
        }

        return true;
    }

    MinecartRevolution plugin;

}