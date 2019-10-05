package com.my.bielik.beercatalog.brewery.search;

import android.util.Log;

import com.my.bielik.beercatalog.api.BreweryApi;
import com.my.bielik.beercatalog.api.RetrofitService;
import com.my.bielik.beercatalog.api.responce.brewery.BreweryData;
import com.my.bielik.beercatalog.api.responce.brewery.BreweryResponse;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.my.bielik.beercatalog.api.RetrofitService.API_KEY;

public class BreweryRepository {

    private static final String TAG = "BreweryRepository";

    private static BreweryRepository breweryRepository;
    private BreweryApi breweryApi;

    private BreweryRepository() {
        breweryApi = RetrofitService.createService(BreweryApi.class);
    }

    static BreweryRepository getInstance() {
        if (breweryRepository == null) {
            breweryRepository = new BreweryRepository();
        }
        return breweryRepository;
    }

    MutableLiveData<List<BreweryData>> getBreweriesNear(double lat, double lng, int radius) {
        final MutableLiveData<List<BreweryData>> breweries = new MutableLiveData<>();
        breweryApi.getBreweriesNear(API_KEY, lat, lng, radius).enqueue(new Callback<BreweryResponse>() {
            @Override
            public void onResponse(Call<BreweryResponse> call, Response<BreweryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    breweries.setValue(response.body().getData());
                    Log.d(TAG, "getBreweries(): " + response.body().getStatus());
                } else {
                    Log.e(TAG, "getBreweries(): code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<BreweryResponse> call, Throwable t) {
                Log.e(TAG, "getBreweries(): failure, " + t.getMessage());
            }
        });
        return breweries;
    }
}
