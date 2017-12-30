package com.example.jdbcdemo.service;

import java.sql.*;

public class ConnectionManagerJDBC {

    private Connection connection;

    private String url = "jdbc:hsqldb:hsql://localhost/workdb";

    private String createTableConsole = "CREATE TABLE IF NOT EXISTS Console (id_console int GENERATED BY DEFAULT AS IDENTITY, name varchar(20) UNIQUE NOT NULL, brand varchar(20) NOT NULL, premiere int NOT NULL, price decimal(6,2) NOT NULL, FOREIGN KEY (brand) REFERENCES Brand(brand_name) ON DELETE CASCADE);";

    private String createTableBrand = "CREATE TABLE IF NOT EXISTS Brand (id_brand int GENERATED BY DEFAULT AS IDENTITY, brand_name varchar(20) UNIQUE NOT NULL, country varchar(20) NOT NULL);";

    private Statement statement;

    public ConnectionManagerJDBC() {
        try {
            connection = DriverManager.getConnection(url);

            statement = connection.createStatement();

            ResultSet rs = connection.getMetaData().getTables(null, null, null,null);
            boolean tablesExists = false;

            while (rs.next()) {
                if ("Console".equalsIgnoreCase(rs.getString("TABLE_NAME")))
                    tablesExists = true;
                break;
            }
            if(!tablesExists){
                statement.executeUpdate(createTableConsole);
                statement.executeUpdate(createTableBrand);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {

        return connection;
    }
}
