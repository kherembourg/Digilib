package com.kherembourg.digilib.trakt.model;

import com.google.gson.annotations.SerializedName;
import com.kherembourg.digilib.betaseries.model.ServiceId;

public class Show {

    @SerializedName("title")
    private String mTitle;
    @SerializedName("year")
    private int mYear;
    @SerializedName("ids")
    private ServiceId mServiceId;

    @Override
    public String toString() {
        return String.format("%s (%s)", mTitle, mYear);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public ServiceId getServiceId() {
        return mServiceId;
    }

    public void setServiceId(ServiceId serviceId) {
        mServiceId = serviceId;
    }
}