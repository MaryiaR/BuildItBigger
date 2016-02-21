package com.udacity.gradle.builditbigger;

public interface ResponseListener {
    void onJokeLoaded(String joke);

    void onConnectionError();
}
