package com.pomac.moltaqaelaghnam.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pomac.moltaqaelaghnam.R;
import com.pomac.moltaqaelaghnam.view.uimodels.DrawerItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder> {

    private Context context;
    private List<DrawerItem> drawerItems;

    public DrawerAdapter(Context context, List<DrawerItem> drawerItems) {
        this.context = context;
        this.drawerItems = drawerItems;
    }

    @NonNull
    @Override
    public DrawerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.drawer_item_layout, parent, false);
        return new DrawerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerViewHolder holder, int position) {
        holder.drawerTitleTextView.setText(drawerItems.get(position).getDrawerItemTitle());

        Picasso.get()
                .load(drawerItems.get(position).getDrawerItemImageResource())
                .into(holder.drawerIconImageView);

        holder.drawerItem.setOnClickListener(drawerItems.get(position).getOnClickListener());
    }

    @Override
    public int getItemCount() {
        return drawerItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class DrawerViewHolder extends RecyclerView.ViewHolder {

        ImageView drawerIconImageView;
        TextView drawerTitleTextView;
        LinearLayout drawerItem;

        public DrawerViewHolder(@NonNull View itemView) {
            super(itemView);
            drawerIconImageView = itemView.findViewById(R.id.drawerIconImageView);
            drawerTitleTextView = itemView.findViewById(R.id.drawerTitleTextView);
            drawerItem = itemView.findViewById(R.id.drawerItem);
        }
    }
}
