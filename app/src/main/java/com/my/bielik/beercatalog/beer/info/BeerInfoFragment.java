package com.my.bielik.beercatalog.beer.info;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.my.bielik.beercatalog.R;
import com.my.bielik.beercatalog.api.responce.beer.BeerData;
import com.my.bielik.beercatalog.database.entity.Beer;
import com.my.bielik.beercatalog.favourite.FavouriteBeerViewModel;

public class BeerInfoFragment extends Fragment {

    private static final String BEER_DATA = "beer_data";

    private Toolbar toolbar;
    private ImageView ivBeer;
    private TextView tvCategory;
    private TextView tvGlass;
    private TextView tvDescription;

    private BeerData beerData;

    private FavouriteBeerViewModel favouriteBeerViewModel;

    public BeerInfoFragment() {
    }

    public static BeerInfoFragment newInstance(BeerData beerData) {
        BeerInfoFragment beerInfoFragment = new BeerInfoFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(BEER_DATA, beerData);
        beerInfoFragment.setArguments(bundle);
        return beerInfoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_info, container, false);
        initViews(view);

        if (getArguments() != null) {
            beerData = getArguments().getParcelable(BEER_DATA);
        }

        setUpToolbar();
        setContent();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favouriteBeerViewModel = ViewModelProviders.of(this).get(FavouriteBeerViewModel.class);
    }

    private void setUpToolbar() {
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(beerData.getName());
            toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
            setHasOptionsMenu(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.favourite_menu_item, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initViews(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        ivBeer = view.findViewById(R.id.iv_beer);
        tvCategory = view.findViewById(R.id.tv_beer_category);
        tvGlass = view.findViewById(R.id.tv_beer_glass);
        tvDescription = view.findViewById(R.id.tv_beer_description);
    }

    private void setContent() {
        if (beerData.getLabels() != null && beerData.getLabels().getIcon() != null) {
            Glide.with(this)
                    .load(beerData.getLabels().getIcon())
                    .into(ivBeer);
        } else {
            Glide.with(this)
                    .load(getResources().getDrawable(R.drawable.ic_not_found))
                    .into(ivBeer);
        }

        if (beerData.getGlass() != null && beerData.getGlass().getName() != null) {
            tvGlass.setText(beerData.getGlass().getName());
        } else {
            tvGlass.setText(getString(R.string.glass_info_not_found));
            tvGlass.setTextSize(20);
            tvGlass.setTextColor(getResources().getColor(R.color.colorTextNotFound));
        }

        if (beerData.getStyle() != null && beerData.getStyle().getDescription() != null) {
            tvDescription.setText(beerData.getStyle().getDescription());
            if (beerData.getStyle().getCategory() != null && beerData.getStyle().getCategory().getName() != null)
                tvCategory.setText(beerData.getStyle().getCategory().getName());
        } else {
            tvGlass.setText(getString(R.string.info_not_found));
            tvGlass.setTextSize(20);
            tvGlass.setTextColor(getResources().getColor(R.color.colorTextNotFound));
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            case R.id.action_add_to_favourite:
                final Beer beer = new Beer(beerData.getName());
                if (beerData.getLabels() != null) {
                    beer.setPhotoUrl(beerData.getLabels().getIcon());
                }
                if (beerData.getGlass() != null) {
                    beer.setGlassName(beerData.getGlass().getName());
                }
                if (beerData.getStyle() != null) {
                    beer.setDescription(beerData.getStyle().getDescription());
                    if (beerData.getStyle().getCategory() != null) {
                        beer.setCategoryName(beerData.getStyle().getCategory().getName());
                    }
                }
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        favouriteBeerViewModel.insertFavouriteBeer(beer);
                    }
                });
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
