package com.kherembourg.digilib.betaseries.model;

import com.google.gson.annotations.SerializedName;

public class ServiceId {

    @SerializedName("trakt")
    private int mTrakt;
    @SerializedName("tvdb")
    private int mTvdb;
    @SerializedName("imdb")
    private String mImdb;
    @SerializedName("tmdb")
    private int mTmdb;
    @SerializedName("tvrage")
    private String mTvrage;

}
