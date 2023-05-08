package com.example.foodit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.foodit.classes.Data;
import com.example.foodit.classes.Helper;
import com.example.foodit.classes.objects.Recipe;
import com.example.foodit.viewmanager.RecipeOverviewAdapter;

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
        RecipeOverviewAdapter adapter = new RecipeOverviewAdapter(Data.getRecipes(), this);
        rvRecipeList.setAdapter(adapter);
        rvRecipeList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addRecipe(View view) {
        Recipe newRecipe = new Recipe("NewRecipe_" + Helper.getNow());
        Data.addRecipe(newRecipe);
        updateRecipeList();
    }
}