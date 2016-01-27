package com.kherembourg.digilib.calendar.presenters;

import com.kherembourg.digilib.trakt.api.CalendarService;
import com.kherembourg.digilib.trakt.model.EpisodeListResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class CalendarPresenterImpl implements CalendarPresenter, Observer<List<EpisodeListResponse>> {

    private final CalendarService calendarService;
    private CalendarView calendarView;
    private Subscription subscription;
    private List<EpisodeListResponse> episodesList;

    public CalendarPresenterImpl(final CalendarService calendarService) {
        this.calendarService = calendarService;
        episodesList = new ArrayList<>();
    }

    @Override
    public void attach(final CalendarView view) {
        calendarView = view;
    }

    @Override
    public void detach() {
        calendarView = null;
        subscription.unsubscribe();
    }

    @Override
    public void loadItems(boolean refresh) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        if(refresh || episodesList.isEmpty()) {
            subscription = calendarService.getMyShows(simpleDateFormat.format(new Date()), 7)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        } else {
            calendarView.displayItems(episodesList);
        }
    }

    @Override
    public void onCompleted() {
        Timber.d("Calendar completed");
    }

    @Override
    public void onError(final Throwable e) {
        Timber.e(e, "Error getting calender");
    }

    @Override
    public void onNext(final List<EpisodeListResponse> episodesList) {
        Timber.d("Calendar received : %s", episodesList);
        this.episodesList = episodesList;
        calendarView.displayItems(episodesList);
    }
}
