package com.pomac.moltaqaelaghnam.view.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pomac.moltaqaelaghnam.Globals;
import com.pomac.moltaqaelaghnam.R;
import com.pomac.moltaqaelaghnam.view.interfaces.OnCategorySelected;
import com.pomac.moltaqaelaghnam.view.uimodels.CategoryItem;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private Context context;
    private List<CategoryItem> categoriesList;
    private OnCategorySelected onCategorySelected;

    private int selectedItemIndex;

    public CategoriesAdapter(Context context, List<CategoryItem> categoriesList, OnCategorySelected onCategorySelected) {
        this.context = context;
        this.categoriesList = categoriesList;
        this.onCategorySelected = onCategorySelected;
        selectedItemIndex = 0;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item_layout, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        holder.categoryTextView.setText(categoriesList.get(position).getTitle());
        holder.categoryTextView.setOnClickListener(v -> {
            selectedItemIndex = position;
            notifyDataSetChanged();
        });

        if (position == selectedItemIndex) {
            holder.categoryTextView.setBackgroundResource(R.drawable.category_background_selected);
            holder.categoryTextView.setTextColor(Color.WHITE);

            SharedPreferences sharedPreferences = context.getSharedPreferences(Globals.SHARED_PREFERENCES, Context.MODE_PRIVATE);

            onCategorySelected.onCategorySelected(
                    sharedPreferences.getInt(Globals.AREA_ID, 1),
                    categoriesList.get(position).getId()
            );
        } else {
            holder.categoryTextView.setBackgroundResource(R.drawable.category_background_unselected);
            holder.categoryTextView.setTextColor(context.getResources().getColor(R.color.AppColor));
        }
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    static class CategoriesViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTextView;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
        }
    }
}
