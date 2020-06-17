package com.pomac.moltaqaelaghnam.view.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pomac.moltaqaelaghnam.R;

public class ContactUsFragment extends Fragment {

    private ImageView instgramImageView;
    private ImageView snapImageView;
    private ImageView twitterImageView;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        instgramImageView = view.findViewById(R.id.instgramImageView);
        snapImageView = view.findViewById(R.id.snapImageView);
        twitterImageView = view.findViewById(R.id.twitterImageView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        assert getActivity() != null;

        instgramImageView.setOnClickListener(v -> {

        });

        snapImageView.setOnClickListener(v -> {

        });

        twitterImageView.setOnClickListener(v -> {

        });
    }
}