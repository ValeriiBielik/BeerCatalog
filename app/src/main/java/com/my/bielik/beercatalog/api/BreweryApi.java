package com.my.bielik.beercatalog.api;

import com.my.bielik.beercatalog.api.responce.beer.BeerResponse;
import com.my.bielik.beercatalog.api.responce.brewery.BreweryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BreweryApi {

    @GET("beers/")
    Call<BeerResponse> getBeerWithName(@Query("name") String text, @Query("key") String key);

    @GET("beers/")
    Call<BeerResponse> getBeerWithPage(@Query("key") String key, @Query("p") int page);

    @GET("search/geo/point")
    Call<BreweryResponse> getBreweriesNear(@Query("key") String key, @Query("lat") double lat, @Query("lng") double lng, @Query("radius") int radius);

    @GET("brewery/{breweryId}/beers/")
    Call<BeerResponse> getBeerWithBreweryId(@Path("breweryId") String breweryId, @Query("key") String key);
}
