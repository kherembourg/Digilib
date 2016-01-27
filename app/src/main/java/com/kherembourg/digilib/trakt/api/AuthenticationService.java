package com.kherembourg.digilib.trakt.api;

import com.kherembourg.digilib.trakt.model.Token;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface AuthenticationService {

    @POST("oauth/token")
    @FormUrlEncoded
    Observable<Token> getToken(@Field("code") String code,
                               @Field("client_id") String clientPassword,
                               @Field("client_secret") String clientSecret,
                               @Field("redirect_uri") String redirectUri,
                               @Field("grant_type") String grantType);

    @POST("oauth/token")
    @FormUrlEncoded
    Observable<Token> refreshToken(@Field("refresh_token") String refreshToken,
                                   @Field("client_id") String clientPassword,
                                   @Field("client_secret") String clientSecret,
                                   @Field("redirect_uri") String redirectUri,
                                   @Field("grant_type") String grantType);
}
