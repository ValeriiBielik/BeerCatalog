package com.my.bielik.beercatalog.brewery.search;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.my.bielik.beercatalog.api.responce.brewery.BreweryData;
import com.my.bielik.beercatalog.beer.search.OnListItemClickListener;

import java.util.List;

public class BreweryNearFragment extends Fragment {

    private static final double NEW_YORK_LATITUDE = 40.730610;
    private static final double NEW_YORK_LONGITUDE = -73.935242;

    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private RecyclerView rvBreweries;

    private BreweryAdapter adapter;
    private BreweryViewModel breweryViewModel;
    private OnListItemClickListener onListItemClickListener;

    private double latitude = NEW_YORK_LATITUDE;
    private double longitude = NEW_YORK_LONGITUDE;

    public BreweryNearFragment() {
    }

    public static BreweryNearFragment newInstance() {
        return new BreweryNearFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_brewery_near, container, false);
        rvBreweries = view.findViewById(R.id.rv_breweries);

        setUpRecyclerView();
        setLatLng();
        breweryViewModel.getBreweriesNear(latitude, longitude, 50);
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

    private void setUpRecyclerView() {
        rvBreweries.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new BreweryAdapter(new BreweryAdapter.OnBreweryClickListener() {
            @Override
            public void onItemCLick(int position) {
                onListItemClickListener.onBreweryClick(adapter.getBreweryData().get(position).getBrewery());
            }
        });
        rvBreweries.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        breweryViewModel = ViewModelProviders.of(this).get(BreweryViewModel.class);
        breweryViewModel.getBreweryData().observe(this, new Observer<List<BreweryData>>() {
            @Override
            public void onChanged(List<BreweryData> breweries) {
                if (breweries != null)
                    adapter.updateBreweryData(breweries);
                else
                    Toast.makeText(getActivity(), getString(R.string.toast_no_breweries_near), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setLatLng() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            List<String> providers = locationManager.getProviders(true);
            Location location = null;
            for (String provider : providers) {
                Location l = locationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (location == null || l.getAccuracy() < location.getAccuracy()) {
                    location = l;
                }
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }

}
