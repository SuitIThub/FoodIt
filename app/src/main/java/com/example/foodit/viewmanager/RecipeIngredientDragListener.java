package com.example.foodit.viewmanager;

import android.view.DragEvent;
import android.view.View;

import com.example.foodit.R;
import com.example.foodit.classes.activities.RecipeActivity;
import com.example.foodit.classes.objects.Ingredient;
import com.example.foodit.classes.objects.IngredientGroup;
import com.example.foodit.classes.objects.Recipe;

import java.util.List;

public class RecipeIngredientDragListener implements View.OnDragListener {

    private boolean isDropped = false;
    private Listener listener;

    private RecipeActivity activity;

    public RecipeIngredientDragListener(Listener listener, RecipeActivity activity) {
        this.listener = listener;
        this.activity = activity;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DROP:
                isDropped = true;
                int positionTarget = -1;

                View viewSource = (View) event.getLocalState();
                Recipe recipe = activity.getActiveRecipe();

                String targetRVID = (String)v.getTag(R.id.group_id);
                IngredientGroup targetGroup = recipe.getGroup(targetRVID);

                String sourceRVID = (String)viewSource.getTag(R.id.group_id);
                String sourceID = (String)viewSource.getTag(R.id.ingredient_id);
                IngredientGroup sourceGroup = recipe.getGroup(sourceRVID);
                Ingredient ingredient = recipe.getIngredient(sourceID);

                sourceGroup.removeIngredient(ingredient);
                targetGroup.addIngredient(ingredient);

                activity.updateIngredients();
                break;
        }

        if (!isDropped && event.getLocalState() != null) {
            ((View) event.getLocalState()).setVisibility(View.VISIBLE);
        }
        return true;
    }
}
