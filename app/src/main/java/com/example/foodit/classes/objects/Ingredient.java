package com.example.foodit.classes.objects;

import com.example.foodit.classes.Helper;

import org.json.JSONException;
import org.json.JSONObject;

public class Ingredient {
    public static class Save {
        public final String id;
        public final String name;
        public final Amount.Save amount;

        public Save(Ingredient ingredient) {
            id = ingredient.id;
            name = ingredient.name;
            this.amount = new Amount.Save(ingredient.amount);
        }
    }

    private String id;
    private String name;
    private Amount amount;

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

    public Ingredient(Save save) {
        id = save.id;
        name = save.name;
        amount = new Amount(save.amount);
    }

    public String getId() {
        return id;
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
