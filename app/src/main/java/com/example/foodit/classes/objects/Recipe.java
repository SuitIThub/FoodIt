package com.example.foodit.classes.objects;

import android.util.Log;

import com.example.foodit.classes.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    public static class Save {
        private final String id;
        private final String title;
        private final String description;
        private final IngredientGroup.Save defaultGroup;
        private final List<IngredientGroup.Save> groups;
        private final List<PrepStep.Save> steps;

        public Save(Recipe recipe) {
            id = recipe.id;
            title = recipe.title;
            description = recipe.description;
            defaultGroup = new IngredientGroup.Save(recipe.defaultGroup);

            groups = new ArrayList<>();
            for (int i = 0; i < recipe.groups.size(); i++) {
                groups.add(new IngredientGroup.Save(recipe.groups.get(i)));
            }

            steps = new ArrayList<PrepStep.Save>();
            for (int i = 0; i < recipe.steps.size(); i++) {
                steps.add(new PrepStep.Save(recipe.steps.get(i)));
            }
        }

    }

    private final String id;

    private String title;
    private String description;

    private IngredientGroup defaultGroup;
    private ArrayList<IngredientGroup> groups;
    private ArrayList<PrepStep> steps;

    public Recipe (String title) {
        id = Helper.id();

        this.title = title;
        description = "";

        defaultGroup = new IngredientGroup("~DEFAULT~");
        groups = new ArrayList<>();

        steps = new ArrayList<>();
    }

    public Recipe (Save save){
        id = save.id;
        title = save.title;
        description = save.description;
        defaultGroup = new IngredientGroup(save.defaultGroup);

        groups = new ArrayList<>();
        for (IngredientGroup.Save gs : save.groups) {
            groups.add(new IngredientGroup(gs));
        }

        steps = new ArrayList<>();
        for (PrepStep.Save ps : save.steps) {
            steps.add(new PrepStep(ps));
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        description = desc;
    }
}
