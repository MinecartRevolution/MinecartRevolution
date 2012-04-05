//MinecartRevolution by Hoppelmann, Nolig and TutorialMakerHD

package de.MRTeam.MinecartRevolution.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class DatabaseConnectionUtil {

    public DatabaseConnectionUtil(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    public ResultSet queryConfigDatabase(String query) {

        if (MinecartRevolution.configUtil.databaseType.equalsIgnoreCase("MySQL")) {
            return queryConfigDatabase(query);
        } else {
            return null;
        }
    }

    public ResultSet queryMySQL(String query) {

        String host = MinecartRevolution.configUtil.databaseHost;
        String database = MinecartRevolution.configUtil.databaseName;
        String user = MinecartRevolution.configUtil.databaseUser;
        String password = MinecartRevolution.configUtil.databasePassword;
        int port = MinecartRevolution.configUtil.databasePort;

        String connectionURL = "jdbc:mysql://" + host + ":" + port + "/" + database;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connections = (Connection) DriverManager.getConnection(connectionURL, user, password);
            Statement statement = (Statement) connections.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        }
        catch (Exception ex) {
            return null;
        }
    }

    MinecartRevolution plugin;
}