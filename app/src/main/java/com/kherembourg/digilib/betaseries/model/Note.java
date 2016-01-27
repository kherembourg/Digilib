package com.kherembourg.digilib.betaseries.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class Note implements Parcelable {

    @Expose
    private Integer total;
    @Expose
    private Integer mean;
    @Expose
    private Integer user;

    public Note() {
    }

    private Note(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    /**
     *
     * @return
     * The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     *
     * @return
     * The mean
     */
    public Integer getMean() {
        return mean;
    }

    /**
     *
     * @param mean
     * The mean
     */
    public void setMean(Integer mean) {
        this.mean = mean;
    }

    /**
     *
     * @return
     * The user
     */
    public Integer getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(Integer user) {
        this.user = user;
    }

}