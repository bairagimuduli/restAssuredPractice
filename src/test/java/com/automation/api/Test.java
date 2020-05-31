package com.automation.api;

import com.automation.api.data_provider.UpcomingMoviesDP;
import com.automation.api.pojo.UpcomingMovieDatum;
import com.automation.api.service_helper.ApiProxyPaytmHelper;
import com.automation.api.util.BaseHelper;
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

    @org.testng.annotations.Test
    public void testName1() throws JsonProcessingException {
        ApiProxyPaytmHelper proxyPaytmHelper=new ApiProxyPaytmHelper();
        BaseHelper baseHelper= new BaseHelper();
        baseHelper.writeToExcelSheet(proxyPaytmHelper.getMovieNameContentAvailable0());
    }
}
