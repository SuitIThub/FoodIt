package com.example.foodit.viewmanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodit.R;
import com.example.foodit.classes.activities.RecipeActivity;
import com.example.foodit.classes.objects.Ingredient;
import com.example.foodit.classes.objects.IngredientGroup;
import com.example.foodit.classes.objects.Recipe;

import java.util.ArrayList;

public class RecipeIngredientAdapter extends RecyclerView.Adapter<RecipeIngredientAdapter.ViewHolder> {

    private String defaultGroupId;
    private Ingredient[] defaultIngredients;
    private IngredientGroup[] groups;

    private Listener listener;

    private Context context;

    private RecipeActivity activity;

    public abstract static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class IngredientViewHolder extends ViewHolder {
        private final TextView text;
        private final EditText textEdit;
        private final TextView desc;
        private final EditText descEdit;

        private final LinearLayout amountList;

        private final View view;

        public IngredientViewHolder(View view) {
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

    public static class GroupViewHolder extends ViewHolder {
        private final TextView text;
        private final ImageButton addBtn;
        private final RecyclerView rvList;
        private final RecipeIngredientGroupAdapter adapter;
        private final RecipeIngredientAdapter secAdapter;

        private final View view;

        public GroupViewHolder(View view, Context context, RecipeIngredientAdapter secAdapter) {
            super(view);

            this.secAdapter = secAdapter;

            // Define click listener for the ViewHolder's View

            text = (TextView) view.findViewById(R.id.rIngredientGroupTitle);

            addBtn = (ImageButton) view.findViewById(R.id.rIngredientGroupAdd);

            rvList = (RecyclerView) view.findViewById(R.id.rIngredientGroupRecyclerView);
            rvList.setLayoutManager(new LinearLayoutManager(
                    context, LinearLayoutManager.VERTICAL, false));

            adapter = new RecipeIngredientGroupAdapter(null);
            rvList.setAdapter(adapter);
            rvList.setOnDragListener(secAdapter.getDragInstance());

            this.view = view;
        }

        public TextView getTextView() {
            return text;
        }

        public RecipeIngredientGroupAdapter getAdapter() {
            return adapter;
        }

        public View getView() {
            return view;
        }

        public ImageButton getAddBtn() {
            return addBtn;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     */
    public RecipeIngredientAdapter(Recipe recipe, Context context, RecipeActivity activity) {
        setRecipe(recipe);
        this.context = context;
        this.activity = activity;
    }

    public void setRecipe(Recipe recipe) {
        this.defaultGroupId = recipe.getGroup("Default").getId();
        this.defaultIngredients = recipe.getGroup("Default").getIngredients();
        this.groups = recipe.getGroups();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item

        switch (viewType) {
            case 0:
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.recipe_ingredient_card, viewGroup, false);
                return new IngredientViewHolder(view);
            default:
                View view2 = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.recipe_ingredient_group_card, viewGroup, false);
                return new GroupViewHolder(view2, viewGroup.getContext(), this);
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        if (getItemViewType(position) == 0) {
            IngredientViewHolder iViewHolder = (IngredientViewHolder)viewHolder;
            iViewHolder.getView().setTag(R.id.group_id, defaultGroupId);
            iViewHolder.getView().setTag(R.id.ingredient_id, defaultIngredients[position].getId());
            iViewHolder.getTextView().setText(defaultIngredients[position].getName());
            iViewHolder.getTextEdit().setText(defaultIngredients[position].getName());
            String desc = defaultIngredients[position].getDesc();
            if (desc.isEmpty())
                iViewHolder.getDescView().setVisibility(View.GONE);
            else {

                iViewHolder.getDescView().setVisibility(View.VISIBLE);
                iViewHolder.getDescView().setText(defaultIngredients[position].getDesc());
                iViewHolder.getDescEdit().setText(defaultIngredients[position].getDesc());
            }
        }
        else {
            int pos = position - defaultIngredients.length;
            GroupViewHolder iViewHolder = (GroupViewHolder)viewHolder;
            iViewHolder.getView().setTag(R.id.group_id, groups[pos].getId());
            iViewHolder.getTextView().setText(groups[pos].getName());
            iViewHolder.getAdapter().setGroup(groups[pos]);
            iViewHolder.getAdapter().notifyDataSetChanged();
            iViewHolder.getAddBtn().setTag(R.id.group_id, groups[pos].getId());
        }
    }

    @Override
    public int getItemViewType(int position) {
        // 0 = Ingredient, 1 = Group
        return (position < defaultIngredients.length) ? 0 : 1;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return defaultIngredients.length + groups.length;
    }

    public RecipeIngredientDragListener getDragInstance() {
        if (listener != null) {
            return new RecipeIngredientDragListener(listener, activity);
        } else {
            Log.e("ListAdapter", "Listener wasn't initialized!");
            return null;
        }
    }
}
