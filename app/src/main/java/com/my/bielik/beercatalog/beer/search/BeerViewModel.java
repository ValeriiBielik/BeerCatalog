package com.my.bielik.beercatalog.beer.search;

import android.util.Log;

import com.my.bielik.beercatalog.api.responce.beer.BeerData;

import java.util.List;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class BeerViewModel extends ViewModel {

    private static final String TAG = "BeerViewModel";
    private int page;

    private MediatorLiveData<List<BeerData>> beerData = new MediatorLiveData<>();

    MutableLiveData<List<BeerData>> getBeerData() {
        return beerData;
    }

    void getNextBeerPage() {
        page++;
        MutableLiveData<List<BeerData>> newBeerData = BeerRepository.getInstance().getBeerWithPage(page);
        if (beerData.getValue() != null)
            beerData.getValue().clear();
        beerData.addSource(newBeerData, new Observer<List<BeerData>>() {
            @Override
            public void onChanged(List<BeerData> newBeerData) {
                beerData.setValue(newBeerData);
            }
        });
        Log.d(TAG, "load beer data, page: " + page);
    }

    void getBeerWithName(String name) {
        MutableLiveData<List<BeerData>> newBeerData = BeerRepository.getInstance().getBeerWithName(name);
        if (beerData.getValue() != null)
            beerData.getValue().clear();
        beerData.addSource(newBeerData, new Observer<List<BeerData>>() {
            @Override
            public void onChanged(List<BeerData> newBeerData) {
                beerData.setValue(newBeerData);
            }
        });
        Log.d(TAG, "load beer data, name: " + name);
    }

    void resetPage() {
        page = 0;
    }

}
