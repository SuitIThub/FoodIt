package com.example.foodit.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.foodit.R;
import com.example.foodit.classes.objects.Recipe;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SaveManager {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    public SaveManager(Context context) {
        sharedPref = context.getSharedPreferences("com.example.myapp.savefile", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public SaveManager updateRecipeList() {
        Set<String> list = sharedPref.getStringSet("recipeList", new HashSet<>());
        list.addAll(Arrays.asList(Data.getRecipeIds()));
        editor.putStringSet("recipeList", list);

        return this;
    }

    public SaveManager saveRecipe(Recipe recipe) {
        String json = new Gson().toJson(new Recipe.Save(recipe));
        editor.putString(recipe.getId(), json);

        return this;
    }

    public SaveManager removeRecipe(Recipe recipe) {
        Set<String> list = sharedPref.getStringSet("recipeList", new HashSet<>());
        list.remove(recipe.getId());
        editor.putStringSet("recipeList", list);

        return this;
    }

    public void apply() {
        updateRecipeList();
        editor.apply();
        editor = sharedPref.edit();
    }

    public Recipe loadRecipe(String id) {
        if (!sharedPref.contains(id))
            return null;

        String recipeJSON = sharedPref.getString(id, "");
        Recipe.Save save = new Gson().fromJson(recipeJSON, Recipe.Save.class);
        return new Recipe(save);
    }

    public String[] getRecipeList() {
        if (!sharedPref.contains("recipeList"))
            return new String[0];

        return sharedPref.getStringSet("recipeList", new HashSet<>()).toArray(new String[0]);
    }

    public Recipe[] loadRecipes() {
        String[] ids = getRecipeList();

        ArrayList<Recipe> recipes = new ArrayList<>();

        for (String id : ids) {
            Recipe r = loadRecipe(id);
            if (r != null)
                recipes.add(r);
        }

        return recipes.toArray(new Recipe[0]);
    }
}
