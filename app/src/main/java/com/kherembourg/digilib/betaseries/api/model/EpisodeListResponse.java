package com.kherembourg.digilib.betaseries.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.kherembourg.digilib.betaseries.model.Episode;

import java.util.List;

public class EpisodeListResponse implements Parcelable {

    private List<Episode> episodes;
    private Object errors;

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

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
