package com.kherembourg.digilib.trakt.api;

import com.kherembourg.digilib.trakt.model.EpisodeListResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface CalendarService {

    @GET("calendars/my/shows/{start_date}/{days}")
    Observable<List<EpisodeListResponse>> getMyShows(@Path("start_date") String startDate, @Path("days") int days);
}
