package com.pomac.moltaqaelaghnam.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pomac.moltaqaelaghnam.Globals;
import com.pomac.moltaqaelaghnam.R;
import com.pomac.moltaqaelaghnam.view.adapters.WishListAdapter;
import com.pomac.moltaqaelaghnam.view.interfaces.AppNavigator;
import com.pomac.moltaqaelaghnam.view.interfaces.OnAdSelected;
import com.pomac.moltaqaelaghnam.view.interfaces.OnWishListItemDelete;
import com.pomac.moltaqaelaghnam.view.uimodels.AdItem;
import com.pomac.moltaqaelaghnam.viewmodel.DeleteFromWishListViewModel;
import com.pomac.moltaqaelaghnam.viewmodel.WishListViewModel;

import java.util.ArrayList;
import java.util.List;

public class WishListFragment extends Fragment implements OnAdSelected, OnWishListItemDelete {

    private TextView wishlistLoginFirstTextView;
    private TextView noWishListItemTextView;

    private RecyclerView wishListRecyclerView;

    private ProgressBar wishListProgressBar;

    private AppNavigator navigator;

    public WishListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);
        wishlistLoginFirstTextView = view.findViewById(R.id.wishlistLoginFirstTextView);
        noWishListItemTextView = view.findViewById(R.id.noWishListItemTextView);
        wishListRecyclerView = view.findViewById(R.id.wishListRecyclerView);
        wishListProgressBar = view.findViewById(R.id.wishListProgressBar);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        assert getActivity() != null;

        navigator = (AppNavigator) getActivity();

        if (Globals.token.isEmpty()) {
            wishListProgressBar.setVisibility(View.GONE);
            wishlistLoginFirstTextView.setVisibility(View.VISIBLE);
            return;
        }

        WishListViewModel viewModel = ViewModelProviders.of(this).get(WishListViewModel.class);

        viewModel.getWishList(Globals.token).observe(getActivity(), response -> {
            if (response.getData().size() > 0) {

                List<AdItem> adItems = new ArrayList<>();

                for (int i = 0; i < response.getData().size(); i++) {
                    AdItem item = new AdItem(
                            response.getData().get(i).getAdvertisement().getId(),
                            response.getData().get(i).getAdvertisement().getTitle(),
                            response.getData().get(i).getAdvertisement().getPhone(),
                            response.getData().get(i).getAdvertisement().getCreatedAt(),
                            response.getData().get(i).getAdvertisement().getArea(),
                            response.getData().get(i).getAdvertisement().getImagePath()
                    );

                    adItems.add(item);
                }

                wishListRecyclerView.setAdapter(new WishListAdapter(getActivity(), adItems, this, this));
                wishListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                wishListProgressBar.setVisibility(View.GONE);
                wishListRecyclerView.setVisibility(View.VISIBLE);
            } else {
                wishListProgressBar.setVisibility(View.GONE);
                noWishListItemTextView.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onAdSelected(int adId, String adTitle) {
        navigator.navigateToAdDetailsPage(adId, adTitle, Globals.FROM_WISH_LIST);
    }

    @Override
    public void onItemDeleted(int adId) {
        assert getActivity() != null;
        DeleteFromWishListViewModel viewModel = ViewModelProviders.of(this).get(DeleteFromWishListViewModel.class);

        viewModel.deleteFromWishList(Globals.token, adId).observe(getActivity(), response -> {
            Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_LONG).show();
        });
    }
}