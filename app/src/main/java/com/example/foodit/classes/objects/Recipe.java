package com.example.foodit.classes.objects;

import com.example.foodit.classes.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

            steps = new ArrayList<>();
            for (int i = 0; i < recipe.steps.size(); i++) {
                steps.add(new PrepStep.Save(recipe.steps.get(i)));
            }
        }

    }

    private final String id;

    private String title;
    private String description;

    private IngredientGroup defaultGroup;
    private HashMap<String, IngredientGroup> groups;
    private ArrayList<PrepStep> steps;

    public Recipe (String title) {
        id = Helper.id();

        this.title = title;
        description = "";

        defaultGroup = new IngredientGroup("~DEFAULT~");
        groups = new HashMap<>();

        steps = new ArrayList<>();
    }

    public Recipe (Save save){
        id = save.id;
        title = save.title;
        description = save.description;
        defaultGroup = new IngredientGroup(save.defaultGroup);

        groups = new HashMap<>();
        for (IngredientGroup.Save gs : save.groups) {
            IngredientGroup i = new IngredientGroup(gs);
            groups.put(i.getId(), i);
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

    public void addGroup(IngredientGroup group) {
        groups.put(group.getId(), group);
    }

    public boolean containsGroup(String id) {
        if (defaultGroup.getId().equals(id))
            return true;
        else if (groups.containsKey(id))
            return true;
        return false;
    }

    public IngredientGroup getGroup(String id) {
        if (defaultGroup.getId().equals(id) || id.equals("Default"))
            return defaultGroup;
        else if (groups.containsKey(id))
            return groups.get(id);
        return null;
    }

    public IngredientGroup[] getGroups() {
        return groups.values().toArray(new IngredientGroup[0]);
    }

    public Ingredient getIngredient(String groupId, String id) {
        if (defaultGroup.getId().equals(groupId) && defaultGroup.containsIngredient(id))
            return defaultGroup.getIngredient(id);
        else if (groups.containsKey(groupId) && groups.get(id).containsIngredient(id)) {
            return groups.get(groupId).getIngredient(id);
        }
        return null;
    }

    public Ingredient getIngredient(String id) {
        if (defaultGroup.containsIngredient(id))
            return defaultGroup.getIngredient(id);

        for (IngredientGroup group : groups.values()) {
            if (group.containsIngredient(id))
                return group.getIngredient(id);
        }

        return null;
    }

    public boolean containsIngredient(String id) {
        if (defaultGroup.containsIngredient(id))
            return true;

        for (IngredientGroup group : groups.values()) {
            if (group.containsIngredient(id))
                return true;
        }

        return false;
    }
}
