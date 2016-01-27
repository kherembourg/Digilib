package com.kherembourg.digilib.trakt.model;

import com.google.gson.annotations.SerializedName;
import com.kherembourg.digilib.betaseries.model.ServiceId;

public class Episode {

    @SerializedName("season")
    private int mSeason;
    @SerializedName("number")
    private int mNumber;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("ids")
    private ServiceId mServiceId;

    @Override
    public String toString() {
        return mTitle + " S" + mSeason + "E" + mNumber;
    }

    public int getSeason() {
        return mSeason;
    }

    public void setSeason(int season) {
        mSeason = season;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        mNumber = number;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public ServiceId getServiceId() {
        return mServiceId;
    }

    public void setServiceId(ServiceId serviceId) {
        mServiceId = serviceId;
    }
}
