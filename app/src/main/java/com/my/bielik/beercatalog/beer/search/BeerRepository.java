package com.my.bielik.beercatalog.beer.search;

import android.util.Log;

import com.my.bielik.beercatalog.api.BreweryApi;
import com.my.bielik.beercatalog.api.RetrofitService;
import com.my.bielik.beercatalog.api.responce.beer.BeerData;
import com.my.bielik.beercatalog.api.responce.beer.BeerResponse;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.my.bielik.beercatalog.api.RetrofitService.API_KEY;

public class BeerRepository {

    private static final String TAG = "BeerRepository";
    private static BeerRepository beerRepository;
    private BreweryApi breweryApi;

    private BeerRepository() {
        breweryApi = RetrofitService.createService(BreweryApi.class);
    }

    public static BeerRepository getInstance() {
        if (beerRepository == null) {
            beerRepository = new BeerRepository();
        }
        return beerRepository;
    }

    MutableLiveData<List<BeerData>> getBeerWithPage(int page) {
        final MutableLiveData<List<BeerData>> beerData = new MutableLiveData<>();
        breweryApi.getBeerWithPage(API_KEY, page).enqueue(new Callback<BeerResponse>() {
            @Override
            public void onResponse(Call<BeerResponse> call, Response<BeerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getStatus().equals("success")) {
                        beerData.setValue(response.body().getData());
                        Log.d(TAG, "getBeer(): " + response.body().getStatus());
                    }
                } else {
                    Log.e(TAG, "getBeer(): code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<BeerResponse> call, Throwable t) {
                Log.e(TAG, "getBeer(): failure, " + t.getMessage());
            }
        });
        return beerData;
    }

    MutableLiveData<List<BeerData>> getBeerWithName(String name) {
        final MutableLiveData<List<BeerData>> beerData = new MutableLiveData<>();
        breweryApi.getBeerWithName(name, API_KEY).enqueue(new Callback<BeerResponse>() {
            @Override
            public void onResponse(Call<BeerResponse> call, Response<BeerResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                    beerData.setValue(response.body().getData());
                    Log.d(TAG, "getBeerWithName(): " + response.body().getStatus());
                } else {
                    Log.e(TAG, "getBeerWithName(): code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<BeerResponse> call, Throwable t) {
                Log.e(TAG, "getBeerWithName(): failure, " + t.getMessage());
            }
        });
        return beerData;
    }

    public MutableLiveData<List<BeerData>> getBeerWithBreweryId(String breweryId) {
        final MutableLiveData<List<BeerData>> beerData = new MutableLiveData<>();
        breweryApi.getBeerWithBreweryId(breweryId, API_KEY).enqueue(new Callback<BeerResponse>() {
            @Override
            public void onResponse(Call<BeerResponse> call, Response<BeerResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                    beerData.setValue(response.body().getData());
                    Log.d(TAG, "getBeerWithBreweryId(): " + response.body().getStatus());
                } else {
                    Log.e(TAG, "getBeerWithBreweryId(): code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<BeerResponse> call, Throwable t) {
                Log.e(TAG, "getBeerWithBreweryId(): failure, " + t.getMessage());
            }
        });
        return beerData;
    }

}
