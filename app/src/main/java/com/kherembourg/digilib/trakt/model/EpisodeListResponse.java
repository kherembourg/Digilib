package com.kherembourg.digilib.trakt.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class EpisodeListResponse implements Parcelable {

    @SerializedName("first_aired")
    private String mFirstAired;
    @SerializedName("episode")
    private Episode mEpisode;
    @SerializedName("show")
    private Show mShow;

    private EpisodeListResponse(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EpisodeListResponse> CREATOR = new Creator<EpisodeListResponse>() {
        @Override
        public EpisodeListResponse createFromParcel(Parcel in) {
            return new EpisodeListResponse(in);
        }

        @Override
        public EpisodeListResponse[] newArray(int size) {
            return new EpisodeListResponse[size];
        }
    };

    @Override
    public String toString() {
        return "Episode => " + mEpisode +
                "\nShow => " + mShow;
    }

    public String getFirstAired() {
        return mFirstAired;
    }

    public void setFirstAired(String firstAired) {
        mFirstAired = firstAired;
    }

    public Episode getEpisode() {
        return mEpisode;
    }

    public void setEpisode(Episode episode) {
        mEpisode = episode;
    }

    public Show getShow() {
        return mShow;
    }

    public void setShow(Show show) {
        mShow = show;
    }
}
