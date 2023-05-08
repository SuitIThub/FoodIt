package com.example.foodit.classes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.foodit.R;
import com.example.foodit.classes.Helper;
import com.example.foodit.classes.objects.Ingredient;
import com.example.foodit.classes.objects.IngredientGroup;
import com.example.foodit.classes.objects.Recipe;
import com.example.foodit.viewmanager.RecipeIngredientAdapter;
import com.example.foodit.viewmanager.RecipeIngredientDragListener;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    private Recipe activeRecipe;

    private RecyclerView rvIngredient;
    private RecipeIngredientAdapter ingredientListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        activeRecipe = new Recipe("test");

        rvIngredient = findViewById(R.id.rIngredientRecyclerView);
        initIngredientRecyclerView();
        updateIngredients();
    }

    private void initIngredientRecyclerView() {
        rvIngredient.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));

        ingredientListAdapter = new RecipeIngredientAdapter(activeRecipe, this, this);
        rvIngredient.setAdapter(ingredientListAdapter);
        rvIngredient.setOnDragListener(ingredientListAdapter.getDragInstance());
    }

    public Recipe getActiveRecipe() {
        return activeRecipe;
    }

    public void updateIngredients() {
        ingredientListAdapter.setRecipe(activeRecipe);
        ingredientListAdapter.notifyDataSetChanged();
    }


    public void test1(View view) {
        Ingredient i = new Ingredient("");
        i.setName(i.getId());
        activeRecipe.getGroup("Default").addIngredient(i);
        updateIngredients();
    }

    public void test2(View view) {
        IngredientGroup ig = new IngredientGroup("");
        ig.setName(ig.getId());
        activeRecipe.addGroup(ig);
        updateIngredients();
    }

    public void test3(View view) {
        IngredientGroup ig = activeRecipe.getGroup((String)view.getTag(R.id.group_id));
        Ingredient i = new Ingredient("");
        i.setName(i.getId());
        ig.addIngredient(i);
        updateIngredients();
    }
}