package com.kherembourg.digilib.betaseries.api.service;

import com.kherembourg.digilib.betaseries.api.model.EpisodeListResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface PlanningService {

    @GET("/planning/general")
    Observable<EpisodeListResponse> getGeneralPlanning(@Query("before") int before, @Query("after") int after);
}
