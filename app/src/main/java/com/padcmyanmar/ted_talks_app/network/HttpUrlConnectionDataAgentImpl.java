package com.padcmyanmar.ted_talks_app.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.padcmyanmar.ted_talks_app.events.ApiErrorEvent;
import com.padcmyanmar.ted_talks_app.events.SuccessGetTalksEvent;
import com.padcmyanmar.ted_talks_app.network.responses.GetTalksResponse;
import com.padcmyanmar.ted_talks_app.utils.TedTalksConstant;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HttpUrlConnectionDataAgentImpl implements TalksDataAgent {

    private static HttpUrlConnectionDataAgentImpl objInstance;

    private HttpUrlConnectionDataAgentImpl() {

    }

    @Override
    public void loadTalkList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {

            URL url;
            BufferedReader bufferedReader;
            StringBuilder stringBuilder;

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    url = new URL(TedTalksConstant.TED_TALKS_BASE_URL + TedTalksConstant.API_GET_TED_TALKS);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setReadTimeout(15 * 1000);

                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);

                    List<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair(TedTalksConstant.PARAM_ACCESS_TOKEN, accessToken));
                    params.add(new BasicNameValuePair(TedTalksConstant.PARAM_PAGE, String.valueOf(page)));

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    httpURLConnection.connect();

                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    stringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }
                    String responseString = stringBuilder.toString();
                    return responseString;

                } catch (Exception e) {
                    Log.e("LOG", e.getMessage());
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {

                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            Log.e("LOG", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                Gson gson = new Gson();
                GetTalksResponse talksResponse = gson.fromJson(responseString, GetTalksResponse.class);
                //Log.d("onPostExecute", "onPostExecute: "+ talksResponse.getTalks().size());

                if (talksResponse.isResponseOK()) {
                    SuccessGetTalksEvent event = new SuccessGetTalksEvent(talksResponse.getTalks());
                    EventBus.getDefault().post(event);
                } else {
                    ApiErrorEvent errorEvent = new ApiErrorEvent(talksResponse.getMessage());
                    EventBus.getDefault().post(errorEvent);
                }
            }
        }.execute();
    }

    @Override
    public void loadTalkPlayList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {

            URL url;
            BufferedReader bufferedReader;
            StringBuilder stringBuilder;

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    url = new URL(TedTalksConstant.TED_TALKS_BASE_URL + TedTalksConstant.API_GET_TED_PLAYLIST);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setReadTimeout(15 * 1000);

                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    List<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair(TedTalksConstant.PARAM_ACCESS_TOKEN, accessToken));
                    params.add(new BasicNameValuePair(TedTalksConstant.PARAM_PAGE, String.valueOf(page)));

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    httpURLConnection.connect();

                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    stringBuilder = new StringBuilder();
                    String lines = null;

                    while ((lines = bufferedReader.readLine()) != null) {

                        stringBuilder.append(lines + "\n");
                    }
                    String responseString = stringBuilder.toString();
                    return responseString;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
            }
        }.execute();

    }

    @Override
    public void loadTalkPodCasts(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {
            URL url;
            BufferedReader reader;
            StringBuilder stringBuilder;

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    url = new URL(TedTalksConstant.TED_TALKS_BASE_URL + TedTalksConstant.API_GET_TED_PLAYLIST);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setConnectTimeout(15 * 1000);
                    httpURLConnection.setReadTimeout(15 * 1000);

                    List<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair(TedTalksConstant.PARAM_ACCESS_TOKEN, accessToken));
                    params.add(new BasicNameValuePair(TedTalksConstant.PARAM_PAGE, String.valueOf(page)));

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    httpURLConnection.connect();

                    reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    stringBuilder = new StringBuilder();
                    String lines = "";

                    while ((lines = reader.readLine()) != null) {
                        stringBuilder.append(lines + "\n");
                    }

                    String responseString = stringBuilder.toString();
                    return responseString;
                } catch (Exception e) {
                    Log.e("ERR_" + TedTalksConstant.API_GET_TED_PODCASTS, e.getMessage());
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            Log.e("ERR_" + TedTalksConstant.API_GET_TED_PODCASTS, e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
            }
        }.execute();

    }

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {

        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;
        for (NameValuePair pair : params) {
            if (isFirst) {
                isFirst = false;
            } else {
                stringBuilder.append("&");
            }
            stringBuilder.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }
        return stringBuilder.toString();
    }

    public static HttpUrlConnectionDataAgentImpl getObjectReference() {
        if (objInstance == null) {
            objInstance = new HttpUrlConnectionDataAgentImpl();
        }
        return objInstance;
    }

}
