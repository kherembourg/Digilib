package com.kherembourg.digilib.calendar.dagger;

import com.kherembourg.digilib.calendar.presenters.CalendarPresenter;
import com.kherembourg.digilib.calendar.presenters.CalendarPresenterImpl;
import com.kherembourg.digilib.trakt.api.CalendarService;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import timber.log.Timber;

@Module
public class CalendarModule {

    @CalendarScope
    @Provides
    public CalendarPresenter provideCalendarPresenter(final CalendarService calendarService) {
        return new CalendarPresenterImpl(calendarService);
    }

    @CalendarScope
    @Provides
    public CalendarService provideCalendarService(@Named("retrofit.trakt") Retrofit retrofit) {
        return retrofit.create(CalendarService.class);
    }

}
