package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import android.text.TextUtils;
import android.util.Pair;

import java.util.concurrent.CountDownLatch;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private CountDownLatch signal;
    private String jokeStr;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void testVerifyJokeNotEmpty() throws InterruptedException {
        LoadJokeTask task = new LoadJokeTask();
        task.execute(new Pair<Context, ResponseListener>(getContext(), new ResponseListener() {

            @Override
            public void onJokeLoaded(String joke) {
                jokeStr = joke;
                signal.countDown();

            }

            @Override
            public void onConnectionError() {
                signal.countDown();
            }
        }));
        signal.await();
        assertNotNull(jokeStr);
        assertFalse(TextUtils.isEmpty(jokeStr));
    }
}