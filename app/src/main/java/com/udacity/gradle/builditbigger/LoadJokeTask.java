package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.util.Utils;
import com.udacity.mrasulava.jokes.backend.jokeApi.JokeApi;

import java.io.IOException;

public class LoadJokeTask extends AsyncTask<Pair<Context, ResponseListener>, Void, String> {
    private static JokeApi jokeApiService = null;
    private ResponseListener listener;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, ResponseListener>... params) {
        context = params[0].first;
        listener = params[0].second;
        if (!Utils.haveInternetConnection(context))
            return null;
        if (jokeApiService == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            jokeApiService = builder.build();
        }


        try {
            return jokeApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (result == null)
            listener.onConnectionError();
        else
            listener.onJokeLoaded(result);
    }
}