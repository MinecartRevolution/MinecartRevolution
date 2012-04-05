//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.util;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class PathFindUtil {

    public PathFindUtil(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    public void insertDestination(Minecart minecart, String destination) {

        if (destinationMap.containsKey(minecart) || pathFindingList.contains(minecart)) {
            return;
        }

        pathFindingList.add(minecart);

        Location location = MinecartRevolution.databaseDestinationLocationUtil.readDestinationLocation(destination);

        if (location != null) {
            MinecartRevolution.pathFindUtil.destinationMap.put(minecart, location);
        }
    }

    public void removeDestination(Minecart minecart, String destination) {

        destinationMap.remove(minecart);
    }

    public void findPathUpdate(Minecart minecart) {

        if (!destinationMap.containsKey(minecart)) {
            return;
        }

        Location newLocation = minecart.getLocation().getBlock().getLocation();
        int rails = 0;
        newLocation.setX(newLocation.getX() + 1.0D);
        if (newLocation.getBlock().getType() == Material.RAILS) {
            rails++;
        }
        newLocation.setX(newLocation.getX() - 2.0D);
        if (newLocation.getBlock().getType() == Material.RAILS) {
            rails++;
        }
        newLocation.setX(newLocation.getX() + 1.0D);
        newLocation.setZ(newLocation.getZ() + 1.0D);
        if (newLocation.getBlock().getType() == Material.RAILS) {
            rails++;
        }
        newLocation.setZ(newLocation.getZ() - 2.0D);
        if (newLocation.getBlock().getType() == Material.RAILS) {
            rails++;
        }

        if (rails <= 2) {
            return;
        }

        Location destination = destinationMap.get(minecart);

        String preferedDirection = "";

        double xDifference = destination.getX() - minecart.getLocation().getX();
        double zDifference = destination.getZ() - minecart.getLocation().getZ();

        double newXDifference = xDifference;
        double newZDifference = zDifference;
        if (newXDifference < 0) {
            newXDifference *= -1;
        }
        if (newZDifference < 0) {
            newZDifference *= -1;
        }

        if (newXDifference < newZDifference) {
            if (zDifference > 0) {
                preferedDirection = "z+";
            } else {
                preferedDirection = "z-";
            }
        } else {
            if (xDifference > 0) {
                preferedDirection = "x+";
            } else {
                preferedDirection = "x-";
            }
        }

        driveNextRails(minecart, preferedDirection);
    }

    private void driveNextRails(Minecart minecart, String preferedDirection) {

        Vector speed = new Vector();
        double speedNumber = 0.3913788423600029;

        Location newLocation = minecart.getLocation();
        if (preferedDirection.equalsIgnoreCase("x+")) {
            speed.setX(speedNumber);
            newLocation.setX(newLocation.getX() + 1.0D);
        } else if (preferedDirection.equalsIgnoreCase("x-")) {
            speed.setX(-speedNumber);
            newLocation.setX(newLocation.getX() - 1.0D);
        } else if (preferedDirection.equalsIgnoreCase("z+")) {
            speed.setZ(speedNumber);
            newLocation.setZ(newLocation.getZ() + 1.0D);
        } else if (preferedDirection.equalsIgnoreCase("z-")) {
            speed.setZ(-speedNumber);
            newLocation.setZ(newLocation.getZ() - 1.0D);
        } else {
            speed = null;
        }

        if (speed != null && newLocation.getBlock().getType() == Material.RAILS) {
            minecart.setVelocity(speed);
            minecart.teleport(newLocation);
        }
    }

    MinecartRevolution                 plugin;

    public HashMap<Minecart, Location> destinationMap  = new HashMap<Minecart, Location>();
    public ArrayList<Minecart>         pathFindingList = new ArrayList<Minecart>();

}
