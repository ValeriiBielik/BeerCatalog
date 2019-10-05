package com.my.bielik.beercatalog.api.responce.beer;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {

    private int id;
    private String name;
    private String createDate;

    protected Category(Parcel in) {
        id = in.readInt();
        name = in.readString();
        createDate = in.readString();
    }

    public Category(String name) {
        this.name = name;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(createDate);
    }
}

