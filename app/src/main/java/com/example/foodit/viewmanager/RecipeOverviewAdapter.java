package com.example.foodit.viewmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foodit.R;
import com.example.foodit.classes.Recipe;

import java.util.List;

public class RecipeOverviewAdapter extends RecyclerView.Adapter<RecipeOverviewAdapter.ViewHolder> {
    private List<Recipe> recipes;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            title = (TextView) view.findViewById(R.id.rOverviewCardTitle);
            description = (TextView) view.findViewById(R.id.rOverviewCardDesc);
        }

        public TextView getTitleView() {
            return title;
        }

        public TextView getDescView() {
            return description;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param recipes List<Recipe> containing the data to populate views to be used
     * by RecyclerView
     */
    public RecipeOverviewAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recipe_overview_card, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTitleView().setText(recipes.get(position).getTitle());
        viewHolder.getDescView().setText(recipes.get(position).getDescription());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
