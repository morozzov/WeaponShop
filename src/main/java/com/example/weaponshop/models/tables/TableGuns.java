package com.example.weaponshop.models.tables;

import com.example.weaponshop.models.entities.Gun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;
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
        ArrayList<Gun> guns = null;

        try{
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format("SELECT * FROM guns");

            ResultSet resultSet = statement.executeQuery(query);

            guns = new ArrayList<>();

            while (resultSet.next() == true){
                Gun gun = new Gun(
                  resultSet.getInt("id"),
                  resultSet.getString("name"),
                  resultSet.getInt("price"),
                  resultSet.getInt("amount")
                );

                guns.add(gun);
            }
        }catch (Exception e){
            throw new Exception("Error from getting data from DB: " + e.getMessage());
        }finally {
            if (connection != null ){
                connection.close();
            }
        }

        return guns;
    }

    public Gun getById(int id) throws Exception{
        Connection connection = null;
        Gun gun = null;

        try{
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format("SELECT * FROM guns WHERE id=%d", id);

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next() == true){
                gun = new Gun(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("amount")
                );
            }
            else{
                throw new Exception("Error, element with this id not exists");
            }
        }catch (Exception e){
            throw new Exception("Error from getting data from DB: " + e.getMessage());
        }finally {
            if (connection != null ){
                connection.close();
            }
        }

        return gun;
    }

    public void insertOne(Gun gun) throws Exception{
        Connection connection = null;

        try{
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format("INSERT INTO guns (name, price, amount) values ('%s', %d, %d)", gun.name, gun.price, gun.amount);

            statement.executeUpdate(query);
        }catch (Exception e){
            throw new Exception("Error from adding data to DB: " + e.getMessage());
        }finally {
            if (connection != null ){
                connection.close();
            }
        }
    }

    public void deleteById(int id) throws Exception{
        Connection connection = null;

        try{
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format("DELETE FROM guns WHERE id=%d", id);

            statement.executeUpdate(query);
        }catch (Exception e){
            throw new Exception("Error from deletion data from DB: " + e.getMessage());
        }finally {
            if (connection != null ){
                connection.close();
            }
        }
    }

    public void updateById(int id, Gun gun) throws Exception{
        Connection connection = null;

        try{
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format("UPDATE guns SET name='%s', price=%d, amount=%d WHERE id=%d", gun.name, gun.price, gun.amount, id);

            statement.executeUpdate(query);
        }catch (Exception e){
            throw new Exception("Error from updating data on DB: " + e.getMessage());
        }finally {
            if (connection != null ){
                connection.close();
            }
        }
    }
}
