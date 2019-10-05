package com.my.bielik.beercatalog.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "beer_table",
        indices = {@Index(value = "name", unique = true),
                @Index(value = "photoUrl", unique = true)})
public class Beer {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;
    private String photoUrl;
    private String glassName;
    private String description;
    private String categoryName;

    @Ignore
    public Beer(@NonNull String name) {
        this.name = name;
    }

    public Beer(@NonNull String name, String photoUrl, String glassName, String description, String categoryName) {
        this.name = name;
        this.photoUrl = photoUrl;
        this.glassName = glassName;
        this.description = description;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getGlassName() {
        return glassName;
    }

    public void setGlassName(String glassName) {
        this.glassName = glassName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
