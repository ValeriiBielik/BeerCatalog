package com.my.bielik.beercatalog.beer.search;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.my.bielik.beercatalog.R;
import com.my.bielik.beercatalog.api.responce.beer.BeerData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BeerSearchFragment extends Fragment {

    private MaterialSearchView materialSearchView;
    private Toolbar toolbar;
    private RecyclerView rvBeer;

    private BeerAdapter beerAdapter;
    private BeerViewModel beerViewModel;
    private OnListItemClickListener onListItemClickListener;

    private boolean isLoading;

    public BeerSearchFragment() {
    }

    public static BeerSearchFragment newInstance() {
        return new BeerSearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_beer, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        materialSearchView = view.findViewById(R.id.search_view);
        rvBeer = view.findViewById(R.id.rv_beer);

        setUpToolbar();
        setUpRecyclerView();
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
        beerViewModel = ViewModelProviders.of(this).get(BeerViewModel.class);
        beerViewModel.getBeerData().observe(this, new Observer<List<BeerData>>() {
            @Override
            public void onChanged(List<BeerData> beerData) {
                if (beerData != null)
                    beerAdapter.addData(beerData);
                isLoading = false;
            }
        });

        beerAdapter = new BeerAdapter(new BeerAdapter.OnBeerClickListener() {
            @Override
            public void onItemClick(int position) {
                onListItemClickListener.onBeerClick(beerAdapter.getBeerData().get(position));
            }
        });
        beerViewModel.getNextBeerPage();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu_item, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        materialSearchView.setMenuItem(menuItem);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setUpToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setHasOptionsMenu(true);

        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                beerAdapter.clearData();
                beerViewModel.getBeerWithName(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                beerViewModel.resetPage();
                beerAdapter.clearData();
                beerViewModel.getNextBeerPage();
            }
        });
    }

    private void setUpRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvBeer.setLayoutManager(layoutManager);

        rvBeer.setAdapter(beerAdapter);
        rvBeer.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemsCount = layoutManager.getItemCount();
                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                if (!isLoading) {
                    if (firstVisibleItem >= totalItemsCount * 0.8) {
                        isLoading = true;
                        beerViewModel.getNextBeerPage();
                    }
                }
            }
        });
    }

}
