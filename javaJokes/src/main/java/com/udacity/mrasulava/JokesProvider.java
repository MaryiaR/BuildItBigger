package com.udacity.mrasulava;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class JokesProvider {
    private List<String> jokesList;

    public JokesProvider() {
        loadJokes();
    }

    public String getJoke() {
        if (jokesList.isEmpty())
            return "";
        return jokesList.get(new Random().nextInt(jokesList.size()));
    }

    private void loadJokes() {
        jokesList = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jokes.txt");
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String joke = scanner.nextLine();
            jokesList.add(joke);
        }
    }
}
