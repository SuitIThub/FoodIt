package com.example.foodit.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Recipe {
    private String id;

    private String name;
    private String description;

    private ArrayList<String> groups;
    private Map<String, List<Ingredient>> ingredientsByGroup;

    private ArrayList<PrepStep> steps;

    public Recipe (String name) {
        id = Helper.id();

        this.name = name;
        description = "";

        groups = new ArrayList<>();
        groups.add("");
        ingredientsByGroup.put("", new ArrayList<>());

        steps = new ArrayList<>();
    }

}
