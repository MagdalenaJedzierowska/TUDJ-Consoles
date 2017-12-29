package com.example.jdbcdemo.service;

import com.example.jdbcdemo.domain.Console;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleManagerJDBC implements ConsoleManager {

    private Connection connection;

    private PreparedStatement addConsoleStmt;		    //CREATE
    private PreparedStatement getAllConsolesStmt;	    //READ
    private PreparedStatement updateConsoleByNameStmt;  //UPDATE
    private PreparedStatement deleteAllConsolesStmt;	//DELETE
    private PreparedStatement deleteConsoleByNameStmt;  //DELETE
    private PreparedStatement searchConsoleByNameStmt;  //FIND

    ConnectionManagerJDBC conn = new ConnectionManagerJDBC();

    public ConsoleManagerJDBC() {
        try {
            connection = conn.getConnection();

            addConsoleStmt = connection
                    .prepareStatement("INSERT INTO Console (name, brand, premiere, price) VALUES (?, ?, ?, ?)");
            getAllConsolesStmt = connection
                    .prepareStatement("SELECT id_console, name, brand, premiere, price FROM Console");
            updateConsoleByNameStmt = connection
                    .prepareStatement("UPDATE Console SET name = ?, brand = ?, premiere = ?, price = ? WHERE name = ?");
            deleteAllConsolesStmt = connection
                    .prepareStatement("DELETE FROM Console");
            deleteConsoleByNameStmt = connection
                    .prepareStatement("DELETE FROM Console WHERE name = ?");
            searchConsoleByNameStmt = connection
                    .prepareStatement("SELECT id_console, name, brand, premiere, price FROM Console WHERE name = ?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addConsole(Console console) {
        int count = 0;

        try {
            addConsoleStmt.setString(1, console.getName());
            addConsoleStmt.setString(2, console.getBrand());
            addConsoleStmt.setInt(3, console.getPremiere());
            addConsoleStmt.setDouble(4, console.getPrice());

            count = addConsoleStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void addConsoles(List<Console> consoles){

        try {
            connection.setAutoCommit(false);
            for (Console console : consoles) {
                addConsoleStmt.setString(1, console.getName());
                addConsoleStmt.setString(2, console.getBrand());
                addConsoleStmt.setInt(3, console.getPremiere());
                addConsoleStmt.setDouble(4, console.getPrice());
                addConsoleStmt.executeUpdate();
            }
            connection.commit();

        } catch (SQLException exception) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
                //!!! ALERT !!!
            }
        }
    }

    @Override
    public List<Console> getAllConsoles() {
        List<Console> consoles = new ArrayList<>();

        try {
            ResultSet resultSet = getAllConsolesStmt.executeQuery();
            consoles = getFromResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consoles;
    }

    @Override
    public int updateConsole(Console console, Console newConsole){
        int count = 0;

        try{
            updateConsoleByNameStmt.setString(1, newConsole.getName());
            updateConsoleByNameStmt.setString(2, newConsole.getBrand());
            updateConsoleByNameStmt.setInt(3, newConsole.getPremiere());
            updateConsoleByNameStmt.setDouble(4, newConsole.getPrice());
            updateConsoleByNameStmt.setString(5, console.getName());
            count = updateConsoleByNameStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public int deleteConsoles(List<Console> consoles){
        int count = 0;

        try{

            connection.setAutoCommit(false);

            for(Console console : consoles){
                deleteConsoleByNameStmt.setString(1, console.getName());
                count += deleteConsoleByNameStmt.executeUpdate();

            }
            connection.commit();

        }catch (SQLException exception){

            try{
                connection.rollback();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return count;
    }

    void clearConsoles() {
        try {
            deleteAllConsolesStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Console> searchConsoleByName(Console console){
        List<Console> consoles = new ArrayList<>();

        try{
            searchConsoleByNameStmt.setString(1, console.getName());
            consoles = getFromResultSet(searchConsoleByNameStmt.executeQuery());

        }catch (SQLException e){
            e.printStackTrace();
        }
        return consoles;
    }

    private List<Console> getFromResultSet (ResultSet resultSet){
        List<Console> consoles = new ArrayList<>();

        try {

            while (resultSet.next()) {
                Console c = new Console();
                c.setId(resultSet.getInt("id_console"));
                c.setName(resultSet.getString("name"));
                c.setBrand(resultSet.getString("brand"));
                c.setPremiere(resultSet.getInt("premiere"));
                c.setPrice(resultSet.getDouble("price"));
                consoles.add(c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return consoles;
    }
}
