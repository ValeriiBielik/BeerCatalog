package com.my.bielik.beercatalog.favourite;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.my.bielik.beercatalog.R;
import com.my.bielik.beercatalog.api.responce.beer.BeerData;
import com.my.bielik.beercatalog.beer.search.BeerAdapter;
import com.my.bielik.beercatalog.beer.search.OnListItemClickListener;
import com.my.bielik.beercatalog.database.entity.Beer;

import java.util.List;

public class FavouriteBeerFragment extends Fragment {

    private RecyclerView rvFavouriteBeer;

    private OnListItemClickListener onListItemClickListener;
    private BeerAdapter beerAdapter;
    private FavouriteBeerViewModel favouriteBeerViewModel;

    public FavouriteBeerFragment() {
    }

    public static FavouriteBeerFragment newInstance() {
        return new FavouriteBeerFragment();
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
        favouriteBeerViewModel = ViewModelProviders.of(this).get(FavouriteBeerViewModel.class);
        favouriteBeerViewModel.getBeerData().observe(this, new Observer<List<BeerData>>() {
            @Override
            public void onChanged(List<BeerData> beerData) {
                if (beerData != null) {
                    beerAdapter.addData(beerData);
                }
            }
        });
        beerAdapter = new BeerAdapter(new BeerAdapter.OnBeerClickListener() {
            @Override
            public void onItemClick(int position) {
                onListItemClickListener.onBeerClick(beerAdapter.getBeerData().get(position));
            }
        });
        favouriteBeerViewModel.getFavouriteBeerList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_beer, container, false);
        rvFavouriteBeer = view.findViewById(R.id.rv_favourite_beer);
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        rvFavouriteBeer.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFavouriteBeer.setAdapter(beerAdapter);
    }

}
