package com.udacity.mrasulava.jokes.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.mrasulava.JokesProvider;

@Api(
        name = "jokeApi",
        version = "v1",
        resource = "joke",
        namespace = @ApiNamespace(
                ownerDomain = "backend.jokes.mrasulava.udacity.com",
                ownerName = "backend.jokes.mrasulava.udacity.com",
                packagePath = ""
        )
)
public class JokeEndpoint {

    @ApiMethod(name = "getJoke")
    public Joke getJoke() {
        Joke joke = new Joke();
        joke.setData(new JokesProvider().getJoke());
        return joke;
    }

}