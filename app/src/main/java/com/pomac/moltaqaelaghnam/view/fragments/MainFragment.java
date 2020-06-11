package com.pomac.moltaqaelaghnam.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pomac.moltaqaelaghnam.R;
import com.pomac.moltaqaelaghnam.view.adapters.AdsAdapter;
import com.pomac.moltaqaelaghnam.view.adapters.CategoriesAdapter;
import com.pomac.moltaqaelaghnam.view.interfaces.OnCategorySelected;
import com.pomac.moltaqaelaghnam.view.uimodels.AdItem;
import com.pomac.moltaqaelaghnam.view.uimodels.CategoryItem;
import com.pomac.moltaqaelaghnam.viewmodel.CategoriesViewModel;
import com.pomac.moltaqaelaghnam.viewmodel.FilterAdvertisementsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements OnCategorySelected {

    private RecyclerView categoriesRecycleView;
    private RecyclerView adsRecycleView;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        categoriesRecycleView = view.findViewById(R.id.categoriesRecycleView);
        adsRecycleView = view.findViewById(R.id.adsRecycleView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        assert getActivity() != null;

        CategoriesViewModel viewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);

        viewModel.getCategories().observe(getActivity(), categoriesResponse -> {

            List<CategoryItem> data = new ArrayList<>();
            for (int i = 0; i < categoriesResponse.getData().size(); i++) {
                CategoryItem item = new CategoryItem(categoriesResponse.getData().get(i).getId(), categoriesResponse.getData().get(i).getName());
                data.add(item);
            }

            categoriesRecycleView.setAdapter(new CategoriesAdapter(getContext(), data, this));
            categoriesRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        });
    }

    @Override
    public void onCategorySelected(int areaId, int mainId) {
        FilterAdvertisementsViewModel filterAdvertisementsViewModel =
                ViewModelProviders.of(this).get(FilterAdvertisementsViewModel.class);

        assert getActivity() != null;
        filterAdvertisementsViewModel.filterAdvertisements(areaId, mainId).observe(getActivity(),
                filterAdvertisementsResponse -> {
                    List<AdItem> adItems = new ArrayList<>();

                    for (int i = 0; i < filterAdvertisementsResponse.getData().size(); i++) {
                        AdItem item = new AdItem(
                                filterAdvertisementsResponse.getData().get(i).getTitle(),
                                filterAdvertisementsResponse.getData().get(i).getPhone(),
                                filterAdvertisementsResponse.getData().get(i).getCreatedAt(),
                                filterAdvertisementsResponse.getData().get(i).getArea(),
                                filterAdvertisementsResponse.getData().get(i).getImagePath()
                        );
                        adItems.add(item);
                    }

                    adsRecycleView.setAdapter(new AdsAdapter(getActivity(), adItems));
                    adsRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
                });
    }
}