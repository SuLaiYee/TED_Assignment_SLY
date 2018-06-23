package com.padcmyanmar.ted_talks_app.network;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.padcmyanmar.ted_talks_app.events.ApiErrorEvent;
import com.padcmyanmar.ted_talks_app.events.SuccessGetTalksEvent;
import com.padcmyanmar.ted_talks_app.network.responses.GetTalksResponse;
import com.padcmyanmar.ted_talks_app.utils.TedTalksConstant;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpDataAgentImpl implements TalksDataAgent {

    private  static OkHttpDataAgentImpl objectReference;

    private OkHttpClient mOkHttpClient;

    private OkHttpDataAgentImpl(){
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
    }
    public static OkHttpDataAgentImpl getObjectReference() {
        if (objectReference == null) {
            objectReference = new OkHttpDataAgentImpl();
        }
        return objectReference;
    }

    @Override
    public void loadTalkList(final int page,final String accessToken) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {

                RequestBody requestBody = new FormBody.Builder()
                        .add(TedTalksConstant.PARAM_PAGE, String.valueOf(page))
                        .add(TedTalksConstant.PARAM_ACCESS_TOKEN, accessToken)
                        .build();

                Request request = new Request.Builder()
                        .url(TedTalksConstant.TED_TALKS_BASE_URL + TedTalksConstant.API_GET_TED_TALKS)
                        .post(requestBody)
                        .build();

                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseString = response.body().string();
                        return responseString;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                Gson gson = new Gson();
                GetTalksResponse getTedTalkResponse = gson.fromJson(responseString, GetTalksResponse.class);
                if (getTedTalkResponse.isResponseOK()) {
                    SuccessGetTalksEvent event = new SuccessGetTalksEvent(getTedTalkResponse.getTalks());
                    EventBus.getDefault().post(event);
                } else {
                    ApiErrorEvent errorEvent = new ApiErrorEvent(getTedTalkResponse.getMessage());
                    EventBus.getDefault().post(errorEvent);
                }

            }
        }.execute();

    }

    @Override
    public void loadTalkPlayList(final int page,final String accessToken) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                RequestBody body = new FormBody.Builder()
                        .add(TedTalksConstant.PARAM_ACCESS_TOKEN, accessToken)
                        .add(TedTalksConstant.PARAM_PAGE, String.valueOf(page))
                        .build();
                Request request = new Request.Builder()
                        .url(TedTalksConstant.TED_TALKS_BASE_URL + TedTalksConstant.API_GET_TED_PLAYLIST)
                        .post(body)
                        .build();

                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    String responseString = response.body().string();
                    return responseString;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                Gson gson = new Gson();
                GetTalksResponse tedTalkResponse = gson.fromJson(responseString, GetTalksResponse.class);
                if (tedTalkResponse.isResponseOK()) {
                    SuccessGetTalksEvent successGetTedTalkEvent = new SuccessGetTalksEvent(tedTalkResponse.getTalks());
                    EventBus.getDefault().post(successGetTedTalkEvent);
                } else {
                    ApiErrorEvent errorEvent = new ApiErrorEvent(tedTalkResponse.getMessage());
                    EventBus.getDefault().post(errorEvent);
                }
            }
        }.execute();

    }

    @Override
    public void loadTalkPodCasts(final int page,final String accessToken) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                RequestBody body = new FormBody.Builder()
                        .add(TedTalksConstant.PARAM_ACCESS_TOKEN, accessToken)
                        .add(TedTalksConstant.PARAM_PAGE, String.valueOf(page))
                        .build();
                Request request = new Request.Builder()
                        .url(TedTalksConstant.TED_TALKS_BASE_URL + TedTalksConstant.API_GET_TED_PODCASTS)
                        .post(body)
                        .build();
                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    String responseString = response.body().string();
                    return responseString;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                Gson gson = new Gson();
                GetTalksResponse tedTalkResponse = gson.fromJson(responseString, GetTalksResponse.class);
                if (tedTalkResponse.isResponseOK()) {
                    SuccessGetTalksEvent event = new SuccessGetTalksEvent(tedTalkResponse.getTalks());
                    EventBus.getDefault().post(event);
                } else {
                    ApiErrorEvent errorEvent = new ApiErrorEvent(tedTalkResponse.getMessage());
                    EventBus.getDefault().post(errorEvent);
                }
            }
        }.execute();


    }
}
