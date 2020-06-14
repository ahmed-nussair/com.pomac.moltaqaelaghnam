package com.pomac.moltaqaelaghnam.view.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.pomac.moltaqaelaghnam.Globals;
import com.pomac.moltaqaelaghnam.R;
import com.pomac.moltaqaelaghnam.view.adapters.CommentsAdapter;
import com.pomac.moltaqaelaghnam.view.uimodels.Comment;
import com.pomac.moltaqaelaghnam.viewmodel.ShowAdvertisementViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

public class AdDetailsFragment extends Fragment {

    private TextView adDetailsErrorConnectionTextView;
    private TextView adDetailsUserNameTextView;
    private TextView adAreaTextView;
    private TextView adDetailsPhoneTextView;
    private TextView adDescriptionTextView;

    private FrameLayout messageFrameLayout;
    private FrameLayout shareFrameLayout;
    private FrameLayout commentFrameLayout;
    private FrameLayout addToWishListFrameLayout;

    private ProgressBar adDetailsProgressBar;

    private ScrollView adContentScrollView;

    private RoundedImageView adDetailsImageView;

    private RecyclerView commentsRecyclerView;

    public AdDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ad_details, container, false);
        adDetailsErrorConnectionTextView = view.findViewById(R.id.adDetailsErrorConnectionTextView);
        adDetailsUserNameTextView = view.findViewById(R.id.adDetailsUserNameTextView);
        adAreaTextView = view.findViewById(R.id.adAreaTextView);
        adDetailsPhoneTextView = view.findViewById(R.id.adDetailsPhoneTextView);
        adDescriptionTextView = view.findViewById(R.id.adDescriptionTextView);

        adDetailsProgressBar = view.findViewById(R.id.adDetailsProgressBar);

        adContentScrollView = view.findViewById(R.id.adContentScrollView);

        adDetailsImageView = view.findViewById(R.id.adDetailsImageView);

        messageFrameLayout = view.findViewById(R.id.messageFrameLayout);
        shareFrameLayout = view.findViewById(R.id.shareFrameLayout);
        commentFrameLayout = view.findViewById(R.id.commentFrameLayout);
        addToWishListFrameLayout = view.findViewById(R.id.addToWishListFrameLayout);

        commentsRecyclerView = view.findViewById(R.id.commentsRecyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        assert getArguments() != null;

        int adId = getArguments().getInt(Globals.AD_ID);

        assert getActivity() != null;
        ShowAdvertisementViewModel viewModel = ViewModelProviders.of(this).get(ShowAdvertisementViewModel.class);

        viewModel.showAdvertisement(adId).observe(getActivity(),
                response -> {

                    adDetailsUserNameTextView.setText(response.getData().getUser().getName());
                    adAreaTextView.setText(response.getData().getArea());
                    adDetailsPhoneTextView.setText(response.getData().getPhone());
                    adDescriptionTextView.setText(response.getData().getDescription());

                    Transformation transformation = new RoundedTransformationBuilder()
                            .cornerRadiusDp(10)
                            .oval(false)
                            .build();
                    Picasso.get()
                            .load(response.getData().getImagePath())
                            .fit()
                            .transform(transformation)
                            .into(adDetailsImageView);

                    List<Comment> comments = new ArrayList<>();

                    for (int i = 0; i < response.getData().getComments().size(); i++) {
                        Comment comment = new Comment(
                                response.getData().getComments().get(i).getUser().getName(),
                                response.getData().getComments().get(i).getComment(),
                                response.getData().getComments().get(i).getUser().getImage()
                        );

                        comments.add(comment);
                    }

                    commentsRecyclerView.setAdapter(new CommentsAdapter(getActivity(), comments));
                    commentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    adDetailsProgressBar.setVisibility(View.GONE);
                    adContentScrollView.setVisibility(View.VISIBLE);
                });
    }
}