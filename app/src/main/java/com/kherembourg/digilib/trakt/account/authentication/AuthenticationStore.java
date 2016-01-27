package com.kherembourg.digilib.trakt.account.authentication;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kherembourg.digilib.trakt.model.Token;

public class AuthenticationStore {

    //region ATTRIBUTES
    static final String AUTHENTICATION_PREFERENCES = "authentication_preferences";
    static final String PREF_TOKEN = "pref_token";

    private final SharedPreferences sharedPreferences;
    private final Gson gson;
    //endregion

    //region PACKAGE
    public AuthenticationStore(Context context, Gson gson) {
        sharedPreferences = context.getSharedPreferences(AUTHENTICATION_PREFERENCES, Context.MODE_PRIVATE);
        this.gson = gson;
    }

    final void saveToken(Token token) {
        sharedPreferences.edit()
                .putString(PREF_TOKEN, gson.toJson(token))
                .apply();
    }

    /**
     * This method should be called from a background thread
     *
     * @return stored token
     * @throws JsonSyntaxException
     */
    Token getToken() throws JsonSyntaxException {
        String jsonToken = sharedPreferences.getString(PREF_TOKEN, null);
        return gson.fromJson(jsonToken, Token.class);
    }
    //endregion

}
