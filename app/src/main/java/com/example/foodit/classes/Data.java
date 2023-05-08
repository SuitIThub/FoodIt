package com.example.foodit.classes;

import android.content.Context;

import com.example.foodit.classes.objects.Recipe;

import java.util.HashMap;

public class Data {
    private static boolean isInitialized = false;

    private static HashMap<String, Recipe> recipes;

    private static SaveManager saveManager;

    public static void initialize(Context context) {
        if (isInitialized)
            return;

        saveManager = new SaveManager(context);

        recipes = new HashMap<>();

        Recipe[] loadedRecipes = saveManager.loadRecipes();
        for (Recipe r : loadedRecipes) {
            recipes.put(r.getId(), r);
        }

        isInitialized = true;
    }

    public static Recipe[] getRecipes() {
        return recipes.values().toArray(new Recipe[0]);
    }

    public static String[] getRecipeIds() {
        return recipes.keySet().toArray(new String[0]);
    }

    public static Recipe getRecipe(String id) {
        if (!recipes.containsKey(id))
            return null;
        return recipes.get(id);
    }

    public static void addRecipe(Recipe recipe) {
        if (recipes.containsKey(recipe.getId()))
            return;
        recipes.put(recipe.getId(), recipe);

        saveManager
            .saveRecipe(recipe)
            .apply();

    }

    public static void removeRecipe(String id) {
        if (!recipes.containsKey(id))
            return;

        saveManager
            .removeRecipe(recipes.get(id))
            .apply();

        recipes.remove(id);
    }

    public static void removeRecipe(Recipe recipe) {
        removeRecipe(recipe.getId());
    }
}
