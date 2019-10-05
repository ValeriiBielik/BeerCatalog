package com.my.bielik.beercatalog.beer.search;

import com.my.bielik.beercatalog.api.responce.beer.BeerData;
import com.my.bielik.beercatalog.api.responce.brewery.Brewery;

public interface OnListItemClickListener {
    void onBeerClick(BeerData beerData);

    void onBreweryClick(Brewery brewery);
}