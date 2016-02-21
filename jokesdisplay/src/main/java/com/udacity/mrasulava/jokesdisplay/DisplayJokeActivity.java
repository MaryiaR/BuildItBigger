package com.udacity.mrasulava.jokesdisplay;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Мария on 20.02.2016.
 */
public class DisplayJokeActivity extends Activity {

    public static final String EXTRA_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        String joke = getIntent().getStringExtra(EXTRA_JOKE);
        ((TextView) findViewById(R.id.tv_joke)).setText(joke);
    }
}
