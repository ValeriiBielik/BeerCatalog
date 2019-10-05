package com.my.bielik.beercatalog.api.responce.beer;

import java.util.List;

public class BeerResponse {

    private Integer currentPage;
    private Integer numberOfPages;
    private Integer totalResults;
    private List<BeerData> data;
    private String status;

    public List<BeerData> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }
}
