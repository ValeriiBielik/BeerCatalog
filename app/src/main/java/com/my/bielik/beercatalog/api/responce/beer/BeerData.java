package com.my.bielik.beercatalog.api.responce.beer;

import android.os.Parcel;
import android.os.Parcelable;

public class BeerData implements Parcelable {

    private String id;
    private String name;
    private String nameDisplay;
    private String abv;
    private int glasswareId;
    private int styleId;
    private String isOrganic;
    private String isRetired;
    private Labels labels;
    private String status;
    private String statusDisplay;
    private String createDate;
    private String updateDate;
    private Glass glass;
    private Style style;

    public BeerData(String name, Labels labels, Glass glass, Style style) {
        this.name = name;
        this.labels = labels;
        this.glass = glass;
        this.style = style;
    }

    protected BeerData(Parcel in) {
        id = in.readString();
        name = in.readString();
        nameDisplay = in.readString();
        abv = in.readString();
        glasswareId = in.readInt();
        styleId = in.readInt();
        isOrganic = in.readString();
        isRetired = in.readString();
        labels = in.readParcelable(Labels.class.getClassLoader());
        status = in.readString();
        statusDisplay = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
        glass = in.readParcelable(Glass.class.getClassLoader());
        style = in.readParcelable(Style.class.getClassLoader());
    }

    public static final Creator<BeerData> CREATOR = new Creator<BeerData>() {
        @Override
        public BeerData createFromParcel(Parcel in) {
            return new BeerData(in);
        }

        @Override
        public BeerData[] newArray(int size) {
            return new BeerData[size];
        }
    };

    public String getName() {
        return name;
    }

    public Labels getLabels() {
        return labels;
    }

    public Glass getGlass() {
        return glass;
    }

    public Style getStyle() {
        return style;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(nameDisplay);
        dest.writeString(abv);
        dest.writeInt(glasswareId);
        dest.writeInt(styleId);
        dest.writeString(isOrganic);
        dest.writeString(isRetired);
        dest.writeParcelable(labels, flags);
        dest.writeString(status);
        dest.writeString(statusDisplay);
        dest.writeString(createDate);
        dest.writeString(updateDate);
        dest.writeParcelable(glass, flags);
        dest.writeParcelable(style, flags);
    }
}