package com.kherembourg.digilib.betaseries.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class User implements Parcelable {

    @Expose
    private Boolean seen;
    @Expose
    private Boolean downloaded;

    public User() {

    }

    private User(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /**
     *
     * @return
     * The seen
     */
    public Boolean getSeen() {
        return seen;
    }

    /**
     *
     * @param seen
     * The seen
     */
    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    /**
     *
     * @return
     * The downloaded
     */
    public Boolean getDownloaded() {
        return downloaded;
    }

    /**
     *
     * @param downloaded
     * The downloaded
     */
    public void setDownloaded(Boolean downloaded) {
        this.downloaded = downloaded;
    }


}
