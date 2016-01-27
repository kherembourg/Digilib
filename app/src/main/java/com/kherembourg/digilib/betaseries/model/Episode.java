package com.kherembourg.digilib.betaseries.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Episode implements Parcelable {

    @Expose
    private Integer id;
    @SerializedName("thetvdb_id")
    @Expose
    private Integer thetvdbId;
    @SerializedName("youtube_id")
    @Expose
    private String youtubeId;
    @Expose
    private String title;
    @Expose
    private Integer season;
    @Expose
    private Integer episode;
    @Expose
    private Show show;
    @Expose
    private String code;
    @Expose
    private Integer global;
    @Expose
    private String description;
    @Expose
    private String date;
    @Expose
    private Note note;
    @Expose
    private User user;
    @Expose
    private String comments;
    @Expose
    private List<Object> subtitles = new ArrayList<>();

    public Episode() {

    }

    private Episode(Parcel in) {
        youtubeId = in.readString();
        title = in.readString();
        show = in.readParcelable(Show.class.getClassLoader());
        code = in.readString();
        description = in.readString();
        date = in.readString();
        note = in.readParcelable(Note.class.getClassLoader());
        user = in.readParcelable(User.class.getClassLoader());
        comments = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(youtubeId);
        dest.writeString(title);
        dest.writeParcelable(show, flags);
        dest.writeString(code);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeParcelable(note, flags);
        dest.writeParcelable(user, flags);
        dest.writeString(comments);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Episode> CREATOR = new Creator<Episode>() {
        @Override
        public Episode createFromParcel(Parcel in) {
            return new Episode(in);
        }

        @Override
        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };

    @Override
    public String toString() {
        return getShow().getTitle() + " S" + getSeason() + "E" + getEpisode() + " le " + getDate();
    }

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
     * The youtubeId
     */
    public String getYoutubeId() {
        return youtubeId;
    }

    /**
     *
     * @param youtubeId
     * The youtube_id
     */
    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
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

    /**
     *
     * @return
     * The season
     */
    public Integer getSeason() {
        return season;
    }

    /**
     *
     * @param season
     * The season
     */
    public void setSeason(Integer season) {
        this.season = season;
    }

    /**
     *
     * @return
     * The episode
     */
    public Integer getEpisode() {
        return episode;
    }

    /**
     *
     * @param episode
     * The episode
     */
    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    /**
     *
     * @return
     * The show
     */
    public Show getShow() {
        return show;
    }

    /**
     *
     * @param show
     * The show
     */
    public void setShow(Show show) {
        this.show = show;
    }

    /**
     *
     * @return
     * The code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The global
     */
    public Integer getGlobal() {
        return global;
    }

    /**
     *
     * @param global
     * The global
     */
    public void setGlobal(Integer global) {
        this.global = global;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The note
     */
    public Note getNote() {
        return note;
    }

    /**
     *
     * @param note
     * The note
     */
    public void setNote(Note note) {
        this.note = note;
    }

    /**
     *
     * @return
     * The user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The comments
     */
    public String getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     * The comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     * The subtitles
     */
    public List<Object> getSubtitles() {
        return subtitles;
    }

    /**
     *
     * @param subtitles
     * The subtitles
     */
    public void setSubtitles(List<Object> subtitles) {
        this.subtitles = subtitles;
    }

}