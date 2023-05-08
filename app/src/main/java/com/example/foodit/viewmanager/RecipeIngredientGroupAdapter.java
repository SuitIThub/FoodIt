package com.example.foodit.viewmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foodit.R;
import com.example.foodit.classes.objects.Ingredient;
import com.example.foodit.classes.objects.IngredientGroup;
import com.example.foodit.classes.objects.Recipe;

public class RecipeIngredientGroupAdapter extends RecyclerView.Adapter<RecipeIngredientGroupAdapter.ViewHolder> {
    private IngredientGroup group;
    private Ingredient[] ingredients;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView text;
        private final EditText textEdit;
        private final TextView desc;
        private final EditText descEdit;

        private final LinearLayout amountList;

        private final View view;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            text = (TextView) view.findViewById(R.id.rIngredientTitle);
            textEdit = (EditText) view.findViewById(R.id.rIngredientTitleEdit);
            desc = (TextView) view.findViewById(R.id.rIngredientDesc);
            descEdit = (EditText) view.findViewById(R.id.rIngredientDescEdit);
            amountList = (LinearLayout) view.findViewById(R.id.rIngredentAmountList);
            this.view = view;
        }

        public TextView getTextView() {
            return text;
        }

        public EditText getTextEdit() {
            return textEdit;
        }

        public TextView getDescView() {
            return desc;
        }

        public EditText getDescEdit() {
            return descEdit;
        }

        public LinearLayout getAmountList() {
            return amountList;
        }

        public View getView() {
            return view;
        }
    }
    /**
     * Initialize the dataset of the Adapter
     * by RecyclerView
     */
    public RecipeIngredientGroupAdapter(IngredientGroup group) {
        setGroup(group);
    }

    public void setGroup(IngredientGroup group) {
        if (group == null) {
            this.group = null;
            ingredients = new Ingredient[0];
            return;
        }
        this.group = group;
        ingredients = group.getIngredients();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recipe_ingredient_card, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getView().setTag(R.id.group_id, group.getId());
        viewHolder.getView().setTag(R.id.ingredient_id, ingredients[position].getId());
        viewHolder.getTextView().setText(ingredients[position].getName());
        viewHolder.getTextEdit().setText(ingredients[position].getName());
        String desc = ingredients[position].getDesc();
        if (desc.isEmpty())
            viewHolder.getDescView().setVisibility(View.GONE);
        else {

            viewHolder.getDescView().setVisibility(View.VISIBLE);
            viewHolder.getDescView().setText(ingredients[position].getDesc());
            viewHolder.getDescEdit().setText(ingredients[position].getDesc());
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (group == null)
            return 0;
        return ingredients.length;
    }

}
