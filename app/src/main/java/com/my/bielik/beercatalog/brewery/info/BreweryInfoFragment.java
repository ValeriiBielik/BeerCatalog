package com.my.bielik.beercatalog.brewery.info;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.my.bielik.beercatalog.R;
import com.my.bielik.beercatalog.api.responce.beer.BeerData;
import com.my.bielik.beercatalog.api.responce.brewery.Brewery;
import com.my.bielik.beercatalog.beer.search.BeerAdapter;
import com.my.bielik.beercatalog.beer.search.OnListItemClickListener;

import java.util.List;

public class BreweryInfoFragment extends Fragment {

    private static final String BREWERY_DATA = "brewery_data";

    private Toolbar toolbar;
    private ImageView ivBreweryIcon;
    private TextView tvBreweryDescription;
    private RecyclerView rvBeer;

    private BeerAdapter beerAdapter;
    private BreweryProductionViewModel breweryProductionViewModel;
    private OnListItemClickListener onListItemClickListener;

    private Brewery brewery;

    public BreweryInfoFragment() {
    }

    public static BreweryInfoFragment newInstance(Brewery brewery) {
        BreweryInfoFragment fragment = new BreweryInfoFragment();

        Bundle args = new Bundle();
        args.putParcelable(BREWERY_DATA, brewery);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brewery_info, container, false);

        initViews(view);
        setUpToolbar();
        setUpRecyclerView();
        setContent();

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnListItemClickListener) {
            onListItemClickListener = (OnListItemClickListener) context;
        } else {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        breweryProductionViewModel = ViewModelProviders.of(this).get(BreweryProductionViewModel.class);
        breweryProductionViewModel.getBeerData().observe(this, new Observer<List<BeerData>>() {
            @Override
            public void onChanged(List<BeerData> beerData) {
                beerAdapter.addData(beerData);
            }
        });

        beerAdapter = new BeerAdapter(new BeerAdapter.OnBeerClickListener() {
            @Override
            public void onItemClick(int position) {
                onListItemClickListener.onBeerClick(beerAdapter.getBeerData().get(position));
            }
        });

        if (getArguments() != null) {
            brewery = getArguments().getParcelable(BREWERY_DATA);
        }

        breweryProductionViewModel.getBreweryProduction(brewery.getId());
    }

    private void initViews(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        ivBreweryIcon = view.findViewById(R.id.iv_brewery);
        tvBreweryDescription = view.findViewById(R.id.tv_brewery_description);
        rvBeer = view.findViewById(R.id.rv_beer);
    }

    private void setUpToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(brewery.getName());
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setHasOptionsMenu(true);
    }

    private void setUpRecyclerView() {
        rvBeer.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvBeer.setAdapter(beerAdapter);
    }

    private void setContent() {
        if (brewery.getBreweryImage() != null) {
            Glide.with(getActivity())
                    .load(brewery.getBreweryImage().getIcon())
                    .into(ivBreweryIcon);
        } else {
            Glide.with(getActivity())
                    .load(getResources().getDrawable(R.drawable.ic_not_found))
                    .into(ivBreweryIcon);
        }
        tvBreweryDescription.setText(brewery.getDescription());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
