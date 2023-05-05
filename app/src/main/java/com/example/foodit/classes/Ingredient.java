package com.example.foodit.classes;

public class Ingredient {
    public String id;
    private String name;
    public Amount amount;

    public Ingredient(String name, String unit, float amount) {
        id = Helper.id();

        this.name = name;
        this.amount = new Amount(unit, amount);
    }

    public Ingredient(String name, String unit, float amount, int portions) {
        id = Helper.id();

        this.name = name;
        this.amount = new Amount(unit, amount, portions);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAmount(String unit, float amount) {
        this.amount = new Amount(unit, amount);
    }

    public void setAmount(String unit, float amount, int portion) {
        this.amount = new Amount(unit, amount, portion);
    }

    public Amount getAmount() {
        return amount;
    }
}
