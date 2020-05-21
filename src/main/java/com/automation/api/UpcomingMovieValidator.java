package com.automation.api;

import com.automation.api.pojo.UpcomingMovieDatum;
import com.automation.api.service_helper.ApiProxyPaytmHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;

import java.util.Date;

public class UpcomingMovieValidator {

    ApiProxyPaytmHelper paytmHelper= new ApiProxyPaytmHelper();

    public void validateMovieReleaseDate(UpcomingMovieDatum upcomingMovieDatum){
        Date date=new Date();
        Date releaseDate = (Date) upcomingMovieDatum.getReleaseDate();
        Assert.assertTrue(date.after(releaseDate), "Movie release date is from Past");
    }

    public void validateMoviePosterUrl(UpcomingMovieDatum upcomingMovieDatum){
        String moviePosterUrl = upcomingMovieDatum.getMoviePosterUrl();
        Assert.assertTrue(moviePosterUrl.endsWith("jpg"), "movie poster url does not ends with .jpg");
    }

    public void validateIsMovieCodeUnique(UpcomingMovieDatum upcomingMovieDatum) throws JsonProcessingException {
        Assert.assertTrue(paytmHelper.isPaytmMovieCodeUnique(upcomingMovieDatum.getPaytmMovieCode()),"movie code is not unique");
    }


}
