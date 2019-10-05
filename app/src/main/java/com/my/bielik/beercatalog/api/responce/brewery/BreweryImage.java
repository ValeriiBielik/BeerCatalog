package com.my.bielik.beercatalog.api.responce.brewery;

import android.os.Parcel;
import android.os.Parcelable;

public class BreweryImage implements Parcelable {

    private String icon;
    private String medium;
    private String large;
    private String squareMedium;
    private String squareLarge;

    protected BreweryImage(Parcel in) {
        icon = in.readString();
        medium = in.readString();
        large = in.readString();
        squareMedium = in.readString();
        squareLarge = in.readString();
    }

    public static final Creator<BreweryImage> CREATOR = new Creator<BreweryImage>() {
        @Override
        public BreweryImage createFromParcel(Parcel in) {
            return new BreweryImage(in);
        }

        @Override
        public BreweryImage[] newArray(int size) {
            return new BreweryImage[size];
        }
    };

    public String getIcon() {
        return icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(icon);
        dest.writeString(medium);
        dest.writeString(large);
        dest.writeString(squareMedium);
        dest.writeString(squareLarge);
    }
}
