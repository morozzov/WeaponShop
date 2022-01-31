package com.example.weaponshop.models.tables;

import com.example.weaponshop.models.entities.Gun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class TableGuns {
    private String url;
    private String login;
    private String password;

    public TableGuns(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    private Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");

        Properties props = new Properties();
        props.setProperty("user", login);
        props.setProperty("password", password);
        props.setProperty("ssl", "false");

        return DriverManager.getConnection(url, props);
    }

    public ArrayList<Gun> getAll() throws Exception{
        Connection connection = null;

        try{
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format("SELECT * FROM guns");

            ResultSet resultSet = statement.executeQuery(query);

            ArrayList<Gun> guns = new ArrayList<>();

            while (resultSet.next() == true){
                Gun gun = new Gun(
                  resultSet.getInt("id"),
                  resultSet.getString("name"),
                  resultSet.getInt("price"),
                  resultSet.getInt("amount")
                );

                guns.add(gun);
            }

            return guns;
        }catch (Exception e){
            throw new Exception("Error from getting data from DB: " + e.getMessage());
        }finally {
            if (connection !=null ){
                connection.close();
            }
        }
    }
}
