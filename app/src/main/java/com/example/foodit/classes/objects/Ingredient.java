package com.example.foodit.classes.objects;

import com.example.foodit.classes.Helper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Ingredient {
    public static class Save {
        public final String id;
        public final String name;
        public final String description;
        public final ArrayList<Amount.Save> amount;

        public Save(Ingredient ingredient) {
            id = ingredient.id;
            name = ingredient.name;
            description = ingredient.description;

            this.amount = new ArrayList<>();
            for (Amount a : ingredient.amount) {
                amount.add(new Amount.Save(a));
            }
        }
    }

    private String id;
    private String name;
    private String description;
    private ArrayList<Amount> amount;

    public Ingredient(String name) {
        id = Helper.id();

        this.name = name;
        this.description = "";
        this.amount = new ArrayList<>();
    }

    public Ingredient(Save save) {
        id = save.id;
        name = save.name;
        description = save.description;
        amount = new ArrayList<>();
        for (Amount.Save s : save.amount) {
            amount.add(new Amount(s));
        }
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

    public void setDesc(String desc) {
        description = desc;
    }

    public String getDesc() {
        return description;
    }
}
