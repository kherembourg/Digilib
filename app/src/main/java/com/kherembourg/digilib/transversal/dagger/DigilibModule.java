package com.kherembourg.digilib.transversal.dagger;

import com.google.gson.Gson;
import com.kherembourg.digilib.BuildConfig;
import com.kherembourg.digilib.trakt.account.authentication.AuthenticationRepository;
import com.kherembourg.digilib.trakt.account.authentication.AuthenticationStore;
import com.kherembourg.digilib.trakt.api.AuthenticationService;
import com.kherembourg.digilib.trakt.api.TraktHttpInterceptor;
import com.kherembourg.digilib.transversal.Digilib;

import java.util.Collections;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

@Module
public class DigilibModule {

    private final Digilib digilib;

    public DigilibModule(Digilib digilib) {
        this.digilib = digilib;
    }

    @Provides
    @Singleton
    public Digilib provideDigilib() {
        return digilib;
    }

    @Provides
    @Singleton
    @Named("retrofit.trakt")
    public Retrofit provideTraktRetrofit(AuthenticationRepository authenticationRepository) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new TraktHttpInterceptor(authenticationRepository))
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .build();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.TRAKT_API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @Named("retrofit.trakt.auth")
    public Retrofit provideTraktAuthRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.TRAKT_API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public AuthenticationStore provideAuthenticationStore(Digilib digilib) {
        return new AuthenticationStore(digilib, new Gson());
    }

    @Provides
    @Singleton
    public AuthenticationService provideAuthenticationService(@Named("retrofit.trakt.auth") Retrofit retrofit) {
        return retrofit.create(AuthenticationService.class);
    }

    @Provides
    @Singleton
    public AuthenticationRepository provideAuthenticationRepository(AuthenticationStore authenticationStore,
                                                                    AuthenticationService authenticationService) {
        return new AuthenticationRepository(authenticationStore, authenticationService);
    }
}
