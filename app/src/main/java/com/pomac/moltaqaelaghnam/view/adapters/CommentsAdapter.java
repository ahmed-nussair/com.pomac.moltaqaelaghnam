package com.pomac.moltaqaelaghnam.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.pomac.moltaqaelaghnam.R;
import com.pomac.moltaqaelaghnam.view.uimodels.Comment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    private Context context;
    private List<Comment> comments;

    public CommentsAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comments_item_layout, parent, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        holder.commentTextView.setText(comments.get(position).getComment());
        holder.commentUserNameTextView.setText(comments.get(position).getUserName());

        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(10)
                .oval(false)
                .build();
        try {
            Picasso.get()
                    .load(comments.get(position).getImagePath())
                    .fit()
                    .transform(transformation)
                    .into(holder.commentUserImageView);
        } catch (Exception e) {
            Picasso.get()
                    .load(R.mipmap.user_phone)
                    .fit()
                    .transform(transformation)
                    .into(holder.commentUserImageView);
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    static class CommentsViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView commentUserImageView;
        TextView commentUserNameTextView;
        TextView commentTextView;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            commentUserImageView = itemView.findViewById(R.id.commentUserImageView);
            commentUserNameTextView = itemView.findViewById(R.id.commentUserNameTextView);
            commentTextView = itemView.findViewById(R.id.commentTextView);
        }
    }
}
