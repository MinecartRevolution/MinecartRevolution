
package de.MRTeam.MinecartRevolution.util.database;

import java.sql.ResultSet;
import de.MRTeam.MinecartRevolution.MinecartRevolution;

public class DatabaseUtil {

    public DatabaseUtil(MinecartRevolution plugin) {

        this.plugin = plugin;
    }

    public void createTable(String table, String[] fieldNames, String[] fieldTypes) {

        if (fieldNames.length != fieldTypes.length) {
            return;
        }

        String query = "CREATE TABLE " + table + " (";
        for (int counter = 0; counter < fieldNames.length; counter++) {
            query += fieldNames[counter] + " " + fieldTypes[counter];
            if (counter < fieldNames.length - 1) {
                query += ", ";
            }
        }
        query += ")";

        MinecartRevolution.databaseConnectionUtil.queryConfigDatabase(query);
    }

    public void insert(String table, String[] fieldNames, String[] fieldData) {

        if (fieldNames.length != fieldData.length) {
            return;
        }

        String query = "INSERT INTO " + table + "(";
        for (int counter = 0; counter < fieldNames.length; counter++) {
            query += "'" + fieldNames[counter] + "'";
            if (counter < fieldNames.length - 1) {
                query += ", ";
            }
        }
        query += ") VALUES(";
        for (int counter = 0; counter < fieldData.length; counter++) {
            query += fieldData[counter];
            if (counter < fieldData.length - 1) {
                query += ", ";
            }
        }
        query += ")";

        MinecartRevolution.databaseConnectionUtil.queryConfigDatabase(query);
    }

    public String[] get(String table, String field) {

        String query = "SELECT " + field + " FROM " + table;

        @SuppressWarnings ("unused")
        ResultSet resultSet = MinecartRevolution.databaseConnectionUtil.queryConfigDatabase(query);
        return null;
    }

    public String[] get(String table, String field, String conditionField, String conditionValue) {

        String query = "SELECT " + field + " FROM " + table + " WHERE " + conditionField + " = " + "'" + conditionValue + "'";

        @SuppressWarnings ("unused")
        ResultSet resultSet = MinecartRevolution.databaseConnectionUtil.queryConfigDatabase(query);
        return null;
    }

    MinecartRevolution plugin;

}
