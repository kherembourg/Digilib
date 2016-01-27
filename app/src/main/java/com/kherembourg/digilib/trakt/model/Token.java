package com.kherembourg.digilib.trakt.model;

import com.google.gson.annotations.SerializedName;

import java.util.concurrent.TimeUnit;

public class Token {

    @SerializedName("access_token")
    private String mAccessToken;
    @SerializedName("token_type")
    private String mTokenType;
    @SerializedName("expires_in")
    private long mExpiresIn;
    @SerializedName("refresh_token")
    private String mRefreshToken;
    @SerializedName("scope")
    private String mScope;

    private long mIssuedAt;

    public Token() {
        mIssuedAt = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }

    public boolean hasExpired() {
        long currentTimestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        return (mIssuedAt + mExpiresIn) - currentTimestamp <= 0;
    }

    @Override
    public String toString() {
        return "access_token=" + mAccessToken
                + " & refresh_token=" + mRefreshToken
                + " & expires_in=" + mExpiresIn
                + " & token_type=" + mTokenType;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public String getTokenType() {
        return mTokenType;
    }

    public void setTokenType(String tokenType) {
        mTokenType = tokenType;
    }

    public long getExpiresIn() {
        return mExpiresIn;
    }

    public void setmxpiresIn(long expiresIn) {
        mExpiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        mRefreshToken = refreshToken;
    }

    public String getScope() {
        return mScope;
    }

    public void setScope(String scope) {
        mScope = scope;
    }
}
