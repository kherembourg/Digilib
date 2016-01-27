package com.kherembourg.digilib.trakt.account.authentication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.JsonSyntaxException;
import com.kherembourg.digilib.BuildConfig;
import com.kherembourg.digilib.trakt.api.AuthenticationService;
import com.kherembourg.digilib.trakt.model.Token;

import rx.Observable;
import rx.functions.Action1;
import timber.log.Timber;

public class AuthenticationRepository {

    //region attributes
    private final AuthenticationStore authenticationStore;
    private final AuthenticationService authenticationService;
    private Token token;
    //endregion

    //region PUBLIC
    public AuthenticationRepository(AuthenticationStore authenticationStore,
                                    AuthenticationService authenticationResource) {
        this.authenticationStore = authenticationStore;
        authenticationService = authenticationResource;
    }

    public void saveToken(@NonNull Token token) {
        checkTokenIsValid(token);

        this.token = token;
        authenticationStore.saveToken(token);
    }

    /**
     * Check if the token is not expired and is still valid server side
     *
     * @return true if token is valid
     */
    public boolean isTokenValid() {
        return token != null && !token.hasExpired();
    }

    public Token getToken() {
        if (token == null) {
            try {
                token = authenticationStore.getToken();
            } catch (JsonSyntaxException e) {
                Timber.i(e, "Unable to retrieve stored token");
            }
        }

        return token;
    }

    public void removeToken() {
        token = null;
        authenticationStore.saveToken(null);
    }

    /**
     * Launch a synchronous request to refresh token
     * This method has to be called from a background thread.
     *
     * @return new token or null if not found
     */
    @Nullable
    public Observable<Token> refreshToken() {
        //If we don't have an existing token then we can't refresh it
        if(getToken() == null) {
            return Observable.empty();
        }

        return authenticationService.refreshToken(token.getRefreshToken(), BuildConfig.TRAKT_CLIENT_ID,
                BuildConfig.TRAKT_CLIENT_SECRET, BuildConfig.TRAKT_REDIRECT_URI, "refresh_token");
    }

    /**
     * Launch a request to login
     * This method has to be called from a background thread
     *
     * @param code send by trakt
     * @return new token
     */
    public Observable<Token> login(final String code) {
        return authenticationService.getToken(code, BuildConfig.TRAKT_CLIENT_ID,
                BuildConfig.TRAKT_CLIENT_SECRET, BuildConfig.TRAKT_REDIRECT_URI, "authorization_code")
                .doOnNext(new Action1<Token>() {
                    @Override
                    public void call(Token token) {
                        saveToken(token);
                    }
                });
    }

    //endregion

    //region PACKAGE
    final void checkTokenIsValid(Token token) {
        if (token == null) {
            throw new IllegalStateException("token must not be null to save it");
        }
        if (token.getAccessToken() == null) {
            throw new IllegalStateException("access token must not be null to save it");
        }
        if (token.getRefreshToken() == null) {
            throw new IllegalStateException("refresh token must not be null to save it");
        }
    }
    //endregion
}
