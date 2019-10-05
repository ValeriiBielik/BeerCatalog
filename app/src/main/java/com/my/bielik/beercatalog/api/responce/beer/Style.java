package com.my.bielik.beercatalog.api.responce.beer;

import android.os.Parcel;
import android.os.Parcelable;

public class Style implements Parcelable {

    private int id;
    private int categoryId;
    private Category category;
    private String name;
    private String shortName;
    private String description;
    private String ibuMin;
    private String ibuMax;
    private String abvMin;
    private String abvMax;
    private String srmMin;
    private String srmMax;
    private String ogMin;
    private String fgMin;
    private String fgMax;
    private String createDate;
    private String updateDate;

    public Style(String description, Category category) {
        this.description = description;
        this.category = category;
    }

    protected Style(Parcel in) {
        id = in.readInt();
        categoryId = in.readInt();
        category = in.readParcelable(Category.class.getClassLoader());
        name = in.readString();
        shortName = in.readString();
        description = in.readString();
        ibuMin = in.readString();
        ibuMax = in.readString();
        abvMin = in.readString();
        abvMax = in.readString();
        srmMin = in.readString();
        srmMax = in.readString();
        ogMin = in.readString();
        fgMin = in.readString();
        fgMax = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
    }

    public static final Creator<Style> CREATOR = new Creator<Style>() {
        @Override
        public Style createFromParcel(Parcel in) {
            return new Style(in);
        }

        @Override
        public Style[] newArray(int size) {
            return new Style[size];
        }
    };

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(categoryId);
        dest.writeParcelable(category, flags);
        dest.writeString(name);
        dest.writeString(shortName);
        dest.writeString(description);
        dest.writeString(ibuMin);
        dest.writeString(ibuMax);
        dest.writeString(abvMin);
        dest.writeString(abvMax);
        dest.writeString(srmMin);
        dest.writeString(srmMax);
        dest.writeString(ogMin);
        dest.writeString(fgMin);
        dest.writeString(fgMax);
        dest.writeString(createDate);
        dest.writeString(updateDate);
    }
}

