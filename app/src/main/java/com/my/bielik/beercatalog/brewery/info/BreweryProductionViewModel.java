package com.my.bielik.beercatalog.brewery.info;

import com.my.bielik.beercatalog.api.responce.beer.BeerData;
import com.my.bielik.beercatalog.beer.search.BeerRepository;

import java.util.List;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class BreweryProductionViewModel extends ViewModel {

    private MediatorLiveData<List<BeerData>> beerData = new MediatorLiveData<>();

    MutableLiveData<List<BeerData>> getBeerData() {
        return beerData;
    }

    void getBreweryProduction(final String breweryId) {
        MutableLiveData<List<BeerData>> productionList = BeerRepository.getInstance().getBeerWithBreweryId(breweryId);
        beerData.addSource(productionList, new Observer<List<BeerData>>() {
            @Override
            public void onChanged(List<BeerData> productionList) {
                beerData.setValue(productionList);
            }
        });
    }
}
