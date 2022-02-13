package com.example.weaponshop.models;

import com.example.weaponshop.models.tables.TableGuns;

public class DbManager {
    private static DbManager instance = null;

    public static DbManager getInstance(){
        if (instance == null){
            instance = new DbManager();
        }
        return instance;
    }

    public TableGuns tableGuns;

    private DbManager(){
        String url = "jdbc:postgresql://194.58.123.204:5432/rmgdb?currentSchema=public";
        String login = "rmg";
        String password = "123";

        tableGuns = new TableGuns(url, login, password);
    }
}
