package com.kherembourg.digilib.betaseries.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Character implements Parcelable {

    private int id;
    private int showId;
    private String name;
    private String role;
    private String actor;
    private String picture;
    private String description;

    public Character() {
        super();
    }

    private Character(Parcel in) {
        id = in.readInt();
        showId = in.readInt();
        name = in.readString();
        role = in.readString();
        actor = in.readString();
        picture = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(showId);
        dest.writeString(name);
        dest.writeString(role);
        dest.writeString(actor);
        dest.writeString(picture);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
