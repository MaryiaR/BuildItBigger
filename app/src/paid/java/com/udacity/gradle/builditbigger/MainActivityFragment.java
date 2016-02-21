package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.mrasulava.jokesdisplay.DisplayJokeActivity;

public class MainActivityFragment extends Fragment {

    private ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        Button btnJoke = (Button) root.findViewById(R.id.btn_joke);
        btnJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                tellJoke();
            }
        });
        return root;
    }

    public void tellJoke() {
        new LoadJokeTask().execute(new Pair<Context, ResponseListener>(getActivity(), new ResponseListener() {

            @Override
            public void onJokeLoaded(String joke) {
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(getActivity(), DisplayJokeActivity.class);
                intent.putExtra(DisplayJokeActivity.EXTRA_JOKE, joke);
                startActivity(intent);
            }

            @Override
            public void onConnectionError() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), getString(R.string.connection_error_message),
                        Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
