package com.padcmyanmar.ted_talks_app.network;

import com.padcmyanmar.ted_talks_app.network.responses.GetTalksResponse;
import com.padcmyanmar.ted_talks_app.utils.TedTalksConstant;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TalksApi {
    @FormUrlEncoded
    @POST(TedTalksConstant.API_GET_TED_TALKS)
    Call<GetTalksResponse> loadTalkList(
            @Field(TedTalksConstant.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(TedTalksConstant.PARAM_PAGE) int page
    );

    @FormUrlEncoded
    @POST(TedTalksConstant.API_GET_TED_PLAYLIST)
    Call<GetTalksResponse> loadTalkPlayList(
            @Field(TedTalksConstant.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(TedTalksConstant.PARAM_PAGE) int page
    );

    @FormUrlEncoded
    @POST(TedTalksConstant.API_GET_TED_PODCASTS)
    Call<GetTalksResponse> loadTalkPodCasts(
            @Field(TedTalksConstant.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(TedTalksConstant.PARAM_PAGE) int page
    );
}
