package com.example.foodit.classes.objects;

import com.example.foodit.classes.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IngredientGroup {
    public static class Save {
        private final String id;
        private final String name;
        private final List<Ingredient.Save> ingredients;

        public Save(IngredientGroup group) {
            id = group.id;
            name = group.name;

            ingredients = new ArrayList<Ingredient.Save>();

            int i = 0;
            for (Ingredient ingredient : group.ingredients.values()) {
                ingredients.add(new Ingredient.Save(ingredient));
            }
        }
    }

    private final String id;

    private String name;

    private HashMap<String, Ingredient> ingredients;

    public IngredientGroup(String name) {
        id = Helper.id();
        this.name = name;
        ingredients = new HashMap<>();
    }

    public IngredientGroup(Save save) {
        id = save.id;
        name = save.name;

        ingredients = new HashMap<>();
        for (Ingredient.Save is : save.ingredients) {
            Ingredient ingredient = new Ingredient(is);
            ingredients.put(ingredient.getId(), ingredient);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ingredient[] getIngredients() {
        return ingredients.values().toArray(new Ingredient[0]);
    }

    public Ingredient getIngredient(String id) {
        if (!ingredients.containsKey(id))
            return null;
        return ingredients.get(id);
    }

    public boolean containsIngredient(String id) {
        return ingredients.containsKey(id);
    }

    public void addIngredient(Ingredient ingredient) {
        if (!ingredients.containsKey(ingredient.getId()))
            ingredients.put(ingredient.getId(), ingredient);
    }

    public void removeIngredient(String id) {
        ingredients.remove(id);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient.getId());
    }

    public int getIngredientAmount() {
        return ingredients.size();
    }
}
