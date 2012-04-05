//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.util;

import de.MRTeam.MinecartRevolution.MinecartRevolution;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.bukkit.ChatColor;

public class DownloadUtil {

    MinecartRevolution plugin;

    public DownloadUtil(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    public String downloadFile(String urlString, OutputStream outputStream) {

        return downloadFile(urlString, "GET", outputStream);
    }

    public String downloadFile(String urlString, String method, OutputStream outputStream) {

        try {
            outputStream.flush();

            URL url = new URL(urlString.replace(" ", "%20"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                byte[] tempBuffer = new byte[4096];

                InputStream inputStream = connection.getInputStream();
                int counter;
                while ( (counter = inputStream.read(tempBuffer)) > 0) {
                    outputStream.write(tempBuffer, 0, counter);
                    outputStream.flush();
                }
            } else {
                return "ERROR:" + responseCode;
            }

            outputStream.close();
        }
        catch (MalformedURLException ex) {
            return "ERROR:500";
        }
        catch (ProtocolException ex) {
            return "ERROR:500";
        }
        catch (IOException ex) {
            return "ERROR:500";
        }

        return null;
    }

    public String getFileContent(String urlString) {

        String fileContent = null;
        URL adress;
        try {
            adress = new URL(urlString);
            InputStream inputStream = adress.openStream();
            fileContent = new Scanner(inputStream).useDelimiter("\\Z").next();
        }
        catch (MalformedURLException ex) {
            return "ERROR:500";
        }
        catch (IOException ex) {
            return "ERROR:404";
        }
        catch (NoSuchElementException ex) {
        }

        return fileContent;
    }

    public void touchURL(String url) {

        try {
            new URL(url).openStream().close();
        }
        catch (MalformedURLException ex) {
        }
        catch (IOException ex) {
        }
    }

    public String checkErrorCodes(String errorCode, boolean returnAll) {

        if (errorCode.contains("ERROR")) {
            if (!returnAll) {
                return null;
            }
            String[] errorParts = errorCode.split(":");

            if (errorParts[1].equalsIgnoreCase("404")) {
                return ChatColor.DARK_RED + "Error: Code " + errorParts[1] + ChatColor.WHITE + " - " + ChatColor.RED + "The required file don't exists!";
            } else if (errorParts[1].equalsIgnoreCase("500")) {
                return ChatColor.DARK_RED + "Error: Code " + errorParts[1] + ChatColor.WHITE + " - " + ChatColor.RED + "The MinecartRevolution Servers are currently not available, try again later!";
            } else {
                return ChatColor.RED + "Unknown connection error: " + errorParts[1] + "!";
            }
        } else {
            return null;
        }
    }
}
