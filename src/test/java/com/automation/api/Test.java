package com.automation.api;

import com.automation.api.data_provider.UpcomingMoviesDP;
import com.automation.api.pojo.UpcomingMovieDatum;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.ParseException;

public class Test {

    @org.testng.annotations.Test(dataProviderClass = UpcomingMoviesDP.class,dataProvider = "getUpcomingMovies")
    public void testName(UpcomingMovieDatum upcomingMovieDatum) throws JsonProcessingException, ParseException {

        UpcomingMovieValidator upcomingMovieValidator = new UpcomingMovieValidator.Builder().
                setUpcomingMovieDatum(upcomingMovieDatum).
                setValidateMoviePosterUrl(true).
                setValidateLanguage(true).
                setValidateReleaseDate(true).
                setPaytmMovieCodeToValidate(upcomingMovieDatum.getPaytmMovieCode()).
                setValidatePaytmMovieCodeUnique(true).
                build();
        upcomingMovieValidator.validate();
    }
}
