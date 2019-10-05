package com.my.bielik.beercatalog.api.responce.beer;

import android.os.Parcel;
import android.os.Parcelable;

public class Glass implements Parcelable {

    private int id;
    private String name;
    private String createDate;

    protected Glass(Parcel in) {
        id = in.readInt();
        name = in.readString();
        createDate = in.readString();
    }

    public Glass(String name) {
        this.name = name;
    }

    public static final Creator<Glass> CREATOR = new Creator<Glass>() {
        @Override
        public Glass createFromParcel(Parcel in) {
            return new Glass(in);
        }

        @Override
        public Glass[] newArray(int size) {
            return new Glass[size];
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
