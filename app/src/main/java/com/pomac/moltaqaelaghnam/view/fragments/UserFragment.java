package com.pomac.moltaqaelaghnam.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.makeramen.roundedimageview.RoundedImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.pomac.moltaqaelaghnam.Globals;
import com.pomac.moltaqaelaghnam.R;
import com.pomac.moltaqaelaghnam.viewmodel.UpdateProfileViewModel;
import com.pomac.moltaqaelaghnam.viewmodel.UserAccountViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class UserFragment extends Fragment {

    private ProgressBar userAccountProgressBar;
    private TextView userAccountLoginFirstTextView;
    private TextView userNameTextView;
    private TextView userAccountUpdateTextView;
    private ScrollView userAccountContent;
    private RoundedImageView commentUserImageView;
    private EditText userNameEditText;
    private TextView userPhoneEditText;
    private EditText userPasswordEditText;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        userAccountProgressBar = view.findViewById(R.id.userAccountProgressBar);
        userAccountLoginFirstTextView = view.findViewById(R.id.userAccountLoginFirstTextView);
        userAccountContent = view.findViewById(R.id.userAccountContent);
        commentUserImageView = view.findViewById(R.id.commentUserImageView);
        userNameTextView = view.findViewById(R.id.userNameTextView);
        userNameEditText = view.findViewById(R.id.userNameEditText);
        userPhoneEditText = view.findViewById(R.id.userPhoneEditText);
        userPasswordEditText = view.findViewById(R.id.userPasswordEditText);
        userAccountUpdateTextView = view.findViewById(R.id.userAccountUpdateTextView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        assert getActivity() != null;

        if (Globals.token.isEmpty()) {
            userAccountProgressBar.setVisibility(View.GONE);
            userAccountLoginFirstTextView.setVisibility(View.VISIBLE);
            return;
        }

        UserAccountViewModel viewModel = ViewModelProviders.of(this).get(UserAccountViewModel.class);

        viewModel.getUserAccount(Globals.token).observe(getActivity(), response -> {

            userNameTextView.setText(response.getUserData().getName());
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(30)
                    .oval(false)
                    .build();
            try {
                Picasso.get()
                        .load(response.getUserData().getImage())
                        .fit()
                        .transform(transformation)
                        .into(commentUserImageView);
            } catch (Exception e) {
                Picasso.get()
                        .load(R.drawable.user)
                        .fit()
                        .transform(transformation)
                        .into(commentUserImageView);
            }

            userNameEditText.setHint(response.getUserData().getName());
            userPhoneEditText.setText(response.getUserData().getPhone());
            userPasswordEditText.setHint("******");

            userAccountProgressBar.setVisibility(View.GONE);
            userAccountContent.setVisibility(View.VISIBLE);

            userAccountUpdateTextView.setOnClickListener(v -> {
                if (!userNameEditText.getText().toString().trim().equals("")) {
                    UpdateProfileViewModel updateProfileViewModel = ViewModelProviders.of(this).get(UpdateProfileViewModel.class);

                    updateProfileViewModel.updateProfile(Globals.token, userNameEditText.getText().toString())
                            .observe(getActivity(), updateProfileResponse -> Toast.makeText(getActivity(), updateProfileResponse.getMessage(), Toast.LENGTH_LONG).show());
                }

            });
        });
    }
}