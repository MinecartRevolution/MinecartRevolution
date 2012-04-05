
package de.MRTeam.MinecartRevolution.util.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class DatabaseDestinationLocationUtil {

    public DatabaseDestinationLocationUtil(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    public void saveDestinationLocation(String destination, Location location) {

        if (MinecartRevolution.configUtil.databaseType.equalsIgnoreCase("FlatFile")) {
            try {
                File file = new File(plugin.getDataFolder() + File.separator + "saves" + File.separator + "destination_signs" + File.separator + destination + ".txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));

                String x = String.valueOf(location.getBlockX());
                String y = String.valueOf(location.getY());
                String z = String.valueOf(location.getZ());
                String yaw = String.valueOf(location.getYaw());
                String pitch = String.valueOf(location.getPitch());
                String world = String.valueOf(location.getWorld().getName());

                writer.write(x + " " + y + " " + z + " " + yaw + " " + pitch + " " + world);
                writer.close();
            }
            catch (Exception ex) {
                return;
            }
        } else {
            MinecartRevolution.databaseConnectionUtil.queryConfigDatabase("INSERT");
        }
    }

    public Location readDestinationLocation(String destination) {

        try {
            File file = new File(plugin.getDataFolder() + File.separator + "saves" + File.separator + "destination_signs" + File.separator + destination + ".txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String lineContent = reader.readLine();

            double x = Double.valueOf(lineContent.split(" ")[0]);
            double y = Double.valueOf(lineContent.split(" ")[1]);
            double z = Double.valueOf(lineContent.split(" ")[2]);
            float yaw = Float.valueOf(lineContent.split(" ")[3]);
            float pitch = Float.valueOf(lineContent.split(" ")[4]);
            World world = Bukkit.getWorld(lineContent.split(" ")[5]);

            Location location = new Location(world, x, y, z, yaw, pitch);

            reader.close();
            return location;
        }
        catch (Exception ex) {
            return null;
        }
    }

    public void deleteDestinationLocation(String destination) {

        File file = new File(plugin.getDataFolder() + File.separator + "saves" + File.separator + "destination_signs" + File.separator + destination + ".txt");
        file.delete();
    }

    MinecartRevolution plugin;

}
