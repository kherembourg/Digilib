package com.kherembourg.digilib.betaseries.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Show implements Parcelable{

    @Expose
    private Integer id;
    @SerializedName("thetvdb_id")
    @Expose
    private Integer thetvdbId;
    @Expose
    private String title;

    public Show() {

    }

    private Show(Parcel in) {
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Show> CREATOR = new Creator<Show>() {
        @Override
        public Show createFromParcel(Parcel in) {
            return new Show(in);
        }

        @Override
        public Show[] newArray(int size) {
            return new Show[size];
        }
    };

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The thetvdbId
     */
    public Integer getThetvdbId() {
        return thetvdbId;
    }

    /**
     *
     * @param thetvdbId
     * The thetvdb_id
     */
    public void setThetvdbId(Integer thetvdbId) {
        this.thetvdbId = thetvdbId;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
