package com.example.weaponshop.models.entities;

public class Gun {
    public int id;
    public String name;
    public int price;
    public int amount;

    public Gun(int id, String name, int price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}
