package com.kherembourg.digilib.trakt.api;

import com.kherembourg.digilib.BuildConfig;
import com.kherembourg.digilib.trakt.account.authentication.AuthenticationRepository;
import com.kherembourg.digilib.trakt.model.Token;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class TraktHttpInterceptor implements Interceptor {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";
    private static final String HEADER_ACCEPT = "Accept";
    private static final String APPLICATION_JSON = "application/json";
    public static final String TRAKT_API_VERSION = "trakt-api-version";
    public static final String TRAKT_API_KEY = "trakt-api-key";

    private final AuthenticationRepository authenticationRepository;

    public TraktHttpInterceptor(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder()
                .header(HEADER_ACCEPT, APPLICATION_JSON)
                .header(TRAKT_API_VERSION, "2")
                .header(TRAKT_API_KEY, BuildConfig.TRAKT_CLIENT_ID);

        Token token = authenticationRepository.getToken();
        if (token != null) {
            builder.header(HEADER_AUTHORIZATION, BEARER + " " + token.getAccessToken());
        }

        Request request = builder.build();

        Timber.d("%s\n%s", request, request.headers());

        Response response = chain.proceed(request);

        Timber.d("%s\n%s\n%s", response, response.headers(), response.body());

        return response;
    }

}
