package com.kherembourg.digilib.transversal.dagger;

import com.kherembourg.digilib.calendar.ui.activities.CalendarActivity;
import com.kherembourg.digilib.trakt.account.activities.LoginActivity;
import com.kherembourg.digilib.trakt.account.authentication.AuthenticationRepository;
import com.kherembourg.digilib.transversal.Digilib;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {DigilibModule.class})
public interface DigilibComponent {
    void inject(Digilib digilib);
    void inject(LoginActivity loginActivity);
    void inject(CalendarActivity calendarActivity);

    Digilib digilib();
    AuthenticationRepository authenticationRepository();
    @Named("retrofit.trakt") Retrofit retrofit();
}
