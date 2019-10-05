package com.my.bielik.beercatalog.favourite;

import android.app.Application;
import android.util.Log;

import com.my.bielik.beercatalog.api.responce.beer.BeerData;
import com.my.bielik.beercatalog.api.responce.beer.Category;
import com.my.bielik.beercatalog.api.responce.beer.Glass;
import com.my.bielik.beercatalog.api.responce.beer.Labels;
import com.my.bielik.beercatalog.api.responce.beer.Style;
import com.my.bielik.beercatalog.database.Database;
import com.my.bielik.beercatalog.database.dao.BeerDao;
import com.my.bielik.beercatalog.database.entity.Beer;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class FavouriteBeerViewModel extends AndroidViewModel {

    private static final String TAG = "FavouriteBeerViewModel";

    private MediatorLiveData<List<BeerData>> beerData = new MediatorLiveData<>();
    private BeerDao beerDao;

    public FavouriteBeerViewModel(@NonNull Application application) {
        super(application);
        beerDao = Database.getInstance(application).beerDao();
    }

    public void getFavouriteBeerList() {
        LiveData<List<Beer>> beerList = beerDao.getFavouriteBeerList();

        beerData.addSource(beerList, new Observer<List<Beer>>() {
            @Override
            public void onChanged(List<Beer> beers) {
                List<BeerData> beerDataList = new ArrayList<>();
                for (Beer beer : beers) {
                    beerDataList.add(new BeerData(beer.getName(),
                            new Labels(beer.getPhotoUrl()),
                            new Glass(beer.getGlassName()),
                            new Style(beer.getDescription(), new Category(beer.getCategoryName()))));
                }
                beerData.setValue(beerDataList);
            }
        });
    }

    public MediatorLiveData<List<BeerData>> getBeerData() {
        return beerData;
    }

    public void insertFavouriteBeer(Beer beer) {
        Log.d(TAG, "Inserted beer id:" + beerDao.insert(beer));
    }
}
