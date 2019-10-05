package com.my.bielik.beercatalog.api.responce.brewery;

import java.util.List;

public class BreweryResponse {

    private Integer currentPage;
    private Integer numberOfPages;
    private Integer totalResults;
    private List<BreweryData> data;
    private String status;

    public List<BreweryData> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }
}