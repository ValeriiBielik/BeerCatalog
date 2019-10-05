package com.my.bielik.beercatalog.api.responce.brewery;

import android.os.Parcel;
import android.os.Parcelable;

public class Brewery implements Parcelable {

    private String id;
    private String name;
    private String nameShortDisplay;
    private String description;
    private String website;
    private String established;
    private String isOrganic;
    private BreweryImage image;
    private String status;
    private String statusDisplay;
    private String createDate;
    private String updateDate;
    private String isMassOwned;
    private String isInBusiness;
    private String brandClassification;
    private String isVerified;

    protected Brewery(Parcel in) {
        id = in.readString();
        name = in.readString();
        nameShortDisplay = in.readString();
        description = in.readString();
        website = in.readString();
        established = in.readString();
        isOrganic = in.readString();
        image = in.readParcelable(BreweryImage.class.getClassLoader());
        status = in.readString();
        statusDisplay = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
        isMassOwned = in.readString();
        isInBusiness = in.readString();
        brandClassification = in.readString();
        isVerified = in.readString();
    }

    public static final Creator<Brewery> CREATOR = new Creator<Brewery>() {
        @Override
        public Brewery createFromParcel(Parcel in) {
            return new Brewery(in);
        }

        @Override
        public Brewery[] newArray(int size) {
            return new Brewery[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BreweryImage getBreweryImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(nameShortDisplay);
        dest.writeString(description);
        dest.writeString(website);
        dest.writeString(established);
        dest.writeString(isOrganic);
        dest.writeParcelable(image, flags);
        dest.writeString(status);
        dest.writeString(statusDisplay);
        dest.writeString(createDate);
        dest.writeString(updateDate);
        dest.writeString(isMassOwned);
        dest.writeString(isInBusiness);
        dest.writeString(brandClassification);
        dest.writeString(isVerified);
    }
}