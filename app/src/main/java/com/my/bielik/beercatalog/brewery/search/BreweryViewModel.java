package com.my.bielik.beercatalog.brewery.search;

import com.my.bielik.beercatalog.api.responce.brewery.BreweryData;

import java.util.List;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class BreweryViewModel extends ViewModel {

    private MediatorLiveData<List<BreweryData>> breweryData = new MediatorLiveData<>();

    MediatorLiveData<List<BreweryData>> getBreweryData() {
        return breweryData;
    }

    void getBreweriesNear(double lat, double lng, int radius) {
        MutableLiveData<List<BreweryData>> breweries = BreweryRepository.getInstance().getBreweriesNear(lat, lng, radius);
        breweryData.addSource(breweries, new Observer<List<BreweryData>>() {
            @Override
            public void onChanged(List<BreweryData> breweries) {
                breweryData.setValue(breweries);
            }
        });
    }
}
