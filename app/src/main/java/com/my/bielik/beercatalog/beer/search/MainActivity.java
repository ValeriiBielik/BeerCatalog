package com.my.bielik.beercatalog.beer.search;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.my.bielik.beercatalog.R;
import com.my.bielik.beercatalog.api.responce.beer.BeerData;
import com.my.bielik.beercatalog.api.responce.brewery.Brewery;
import com.my.bielik.beercatalog.beer.info.BeerInfoFragment;
import com.my.bielik.beercatalog.brewery.info.BreweryInfoFragment;
import com.my.bielik.beercatalog.brewery.search.BreweryNearFragment;
import com.my.bielik.beercatalog.favourite.FavouriteBeerFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements OnListItemClickListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, BeerSearchFragment.newInstance()).commit();
        }

        setUpBottomNavigationView();
    }

    private void setUpBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.navigation_search_breweries:
                        fragment = BreweryNearFragment.newInstance();
                        break;
                    case R.id.navigation_favourite_beer:
                        fragment = FavouriteBeerFragment.newInstance();
                        break;
                    default:
                        fragment = BeerSearchFragment.newInstance();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                return true;
            }
        });
    }

    @Override
    public void onBeerClick(BeerData beerData) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, BeerInfoFragment.newInstance(beerData)).addToBackStack(null).commit();
        bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void onBreweryClick(Brewery brewery) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, BreweryInfoFragment.newInstance(brewery)).addToBackStack(null).commit();
        bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}
