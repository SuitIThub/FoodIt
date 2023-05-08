package com.example.foodit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodit.classes.Recipe;
import com.example.foodit.viewmanager.RecipeOverviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecipeOverview extends AppCompatActivity {

    RecyclerView rvRecipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_overview);

        rvRecipeList = (RecyclerView) findViewById(R.id.rOverviewRecyclerView);
        updateRecipeList();
    }

    public void updateRecipeList() {
        // Load test recipes
        List<Recipe> recipes = new ArrayList<Recipe>();
        for (int i = 0; i < 5; i++) {
            recipes.add(new Recipe("recipe " + i));
        }

        RecipeOverviewAdapter adapter = new RecipeOverviewAdapter(recipes);
        rvRecipeList.setAdapter(adapter);
        rvRecipeList.setLayoutManager(new LinearLayoutManager(this));
    }
}