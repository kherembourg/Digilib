package com.kherembourg.digilib.transversal;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.kherembourg.digilib.transversal.dagger.DaggerHelper;

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
