package com.my.bielik.beercatalog.api.responce.beer;

import android.os.Parcel;
import android.os.Parcelable;

public class Labels implements Parcelable {

    private String icon;
    private String medium;
    private String large;
    private String contentAwareIcon;
    private String contentAwareMedium;
    private String contentAwareLarge;

    protected Labels(Parcel in) {
        icon = in.readString();
        medium = in.readString();
        large = in.readString();
        contentAwareIcon = in.readString();
        contentAwareMedium = in.readString();
        contentAwareLarge = in.readString();
    }

    public Labels(String icon) {
        this.icon = icon;
    }

    public static final Creator<Labels> CREATOR = new Creator<Labels>() {
        @Override
        public Labels createFromParcel(Parcel in) {
            return new Labels(in);
        }

        @Override
        public Labels[] newArray(int size) {
            return new Labels[size];
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
        dest.writeString(contentAwareIcon);
        dest.writeString(contentAwareMedium);
        dest.writeString(contentAwareLarge);
    }
}