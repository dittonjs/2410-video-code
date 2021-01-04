package com.usu;

public class Person {
    private int money = 100;
    private int id;
    public Person(int id) {
        this.id = id;
    }

    public boolean charge(int price) {
        if (price > money) return false;
        money -= price;
        return true;
    }

    public int getId() {
        return id;
    }
}
