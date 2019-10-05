package com.my.bielik.beercatalog.api.responce.brewery;

public class BreweryData {

    private String id;
    private String name;
    private String streetAddress;
    private String locality;
    private String region;
    private String postalCode;
    private String phone;
    private String website;
    private double latitude;
    private double longitude;
    private String isPrimary;
    private String inPlanning;
    private String isClosed;
    private String openToPublic;
    private String locationType;
    private String locationTypeDisplay;
    private String countryIsoCode;
    private String status;
    private String statusDisplay;
    private String createDate;
    private String updateDate;
    private String breweryId;
    private Brewery brewery;
    private Country country;
    private double distance;

    public Brewery getBrewery() {
        return brewery;
    }

}
