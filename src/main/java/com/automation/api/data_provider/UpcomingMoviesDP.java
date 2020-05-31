package com.automation.api.data_provider;

import com.automation.api.pojo.UpcomingMovieDatum;
import com.automation.api.service_helper.ApiProxyPaytmHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.DataProvider;

import java.util.List;

public class UpcomingMoviesDP {

    ApiProxyPaytmHelper paytmHelper = new ApiProxyPaytmHelper();

    @DataProvider(name = "getUpcomingMovies")
    public Object[][] getUpcomingMovies() throws JsonProcessingException {
        List<UpcomingMovieDatum> upcomingMovieData = paytmHelper.getUpcomingMovies().getUpcomingMovieData();
        return DataProviderGenerator.generatevariants2(upcomingMovieData);
    }
}
