package com.padcmyanmar.ted_talks_app.network;

import com.padcmyanmar.ted_talks_app.events.ApiErrorEvent;
import com.padcmyanmar.ted_talks_app.events.SuccessGetTalksEvent;
import com.padcmyanmar.ted_talks_app.network.responses.GetTalksResponse;
import com.padcmyanmar.ted_talks_app.utils.TedTalksConstant;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements TalksDataAgent {

    private  TalksApi mTalksApi;

    private  static RetrofitDataAgentImpl objectReference;

    private RetrofitDataAgentImpl(){
      final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TedTalksConstant.TED_TALKS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        mTalksApi = retrofit.create(TalksApi.class);
    }

    public static RetrofitDataAgentImpl getObjectReference() {
        if (objectReference == null) {
            objectReference = new RetrofitDataAgentImpl();
        }
        return objectReference;
    }

    @Override
    public void loadTalkList(int page, String accessToken) {
        Call<GetTalksResponse> apiCall = mTalksApi.loadTalkList(accessToken, page);
        apiCall.enqueue(new Callback<GetTalksResponse>() {
            @Override
            public void onResponse(Call<GetTalksResponse> call, Response<GetTalksResponse> response) {
                GetTalksResponse tedTalkResponse = response.body();
                if (tedTalkResponse != null && tedTalkResponse.isResponseOK()) {
                    SuccessGetTalksEvent event = new SuccessGetTalksEvent(tedTalkResponse.getTalks());
                    EventBus.getDefault().post(event);
                } else {
                    if (tedTalkResponse == null) {
                        ApiErrorEvent errorEvent = new ApiErrorEvent("Empty in Response");
                        EventBus.getDefault().post(errorEvent);

                    } else {
                        ApiErrorEvent errorEvent = new ApiErrorEvent(tedTalkResponse.getMessage());
                        EventBus.getDefault().post(errorEvent);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTalksResponse> call, Throwable t) {

                ApiErrorEvent errorEvent = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        });
    }

    @Override
    public void loadTalkPlayList(int page, String accessToken) {

        Call<GetTalksResponse> apiCall = mTalksApi.loadTalkList(accessToken, page);
        apiCall.enqueue(new Callback<GetTalksResponse>() {
            @Override
            public void onResponse(Call<GetTalksResponse> call, Response<GetTalksResponse> response) {
                GetTalksResponse tedTalkResponse = response.body();
                if (tedTalkResponse != null && tedTalkResponse.isResponseOK()) {

                    SuccessGetTalksEvent event = new SuccessGetTalksEvent(tedTalkResponse.getTalks());
                    EventBus.getDefault().post(event);

                } else {
                    if (tedTalkResponse == null) {

                        ApiErrorEvent errorEvent = new ApiErrorEvent("Empty in response.");
                        EventBus.getDefault().post(errorEvent);

                    } else {
                        ApiErrorEvent errorEvent = new ApiErrorEvent(tedTalkResponse.getMessage());
                        EventBus.getDefault().post(errorEvent);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTalksResponse> call, Throwable t) {
                ApiErrorEvent errorEvent = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        });
    }

    @Override
    public void loadTalkPodCasts(int page, String accessToken) {
        Call<GetTalksResponse> apiCall = mTalksApi.loadTalkPodCasts(accessToken, page);
        apiCall.enqueue(new Callback<GetTalksResponse>() {
            @Override
            public void onResponse(Call<GetTalksResponse> call, Response<GetTalksResponse> response) {

                GetTalksResponse tedTalkResponse = response.body();
                if (tedTalkResponse != null && tedTalkResponse.isResponseOK()) {

                    SuccessGetTalksEvent successGetTedTalkEvent = new SuccessGetTalksEvent(tedTalkResponse.getTalks());
                    EventBus.getDefault().post(successGetTedTalkEvent);

                } else {
                    if (tedTalkResponse == null) {
                        ApiErrorEvent errorEvent = new ApiErrorEvent("Empty in response.");
                        EventBus.getDefault().post(errorEvent);
                    } else {
                        ApiErrorEvent errorEvent = new ApiErrorEvent(tedTalkResponse.getMessage());
                        EventBus.getDefault().post(errorEvent);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTalksResponse> call, Throwable t) {

                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);

            }
        });

    }
}
