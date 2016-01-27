package com.kherembourg.digilib.calendar.presenters;

import com.kherembourg.digilib.trakt.model.EpisodeListResponse;
import com.kherembourg.digilib.transversal.presenters.PresenterView;

import java.util.List;

public interface CalendarView extends PresenterView {

    void displayItems(final List<EpisodeListResponse> episodes);

}
