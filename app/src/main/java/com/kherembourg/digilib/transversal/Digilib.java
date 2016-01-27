package com.kherembourg.digilib.transversal;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.kherembourg.digilib.calendar.dagger.CalendarComponent;
import com.kherembourg.digilib.calendar.dagger.CalendarModule;
import com.kherembourg.digilib.calendar.dagger.DaggerCalendarComponent;
import com.kherembourg.digilib.transversal.dagger.DaggerDigilibComponent;
import com.kherembourg.digilib.transversal.dagger.DaggerHelper;
import com.kherembourg.digilib.transversal.dagger.DigilibComponent;
import com.kherembourg.digilib.transversal.dagger.DigilibModule;

import timber.log.Timber;

public class Digilib extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerHelper.init(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());

        Timber.plant(new Timber.DebugTree());
    }
}
