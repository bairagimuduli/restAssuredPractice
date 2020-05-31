package com.automation.api.service_helper;

import com.automation.api.pojo.UpcomingMovieDatum;
import com.automation.api.pojo.UpcomingMovies;
import com.automation.api.util.BaseHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class ApiProxyPaytmHelper {

    //    https://apiproxy.paytm.com/v2/movies/upcoming
    BaseHelper baseHelper=new BaseHelper();

    String baseUri= "https://apiproxy.paytm.com";

    public UpcomingMovies getUpcomingMovies() throws JsonProcessingException {
        String path= "v2/movies/upcoming";
        Response response = baseHelper.get(baseUri, path);
        ObjectMapper objectMapper = new ObjectMapper();
        UpcomingMovies upcomingMovies = objectMapper.readValue(response.prettyPrint(), UpcomingMovies.class);
        return upcomingMovies;
    }

    public List<String> getAllPaytmMovieCodes() throws JsonProcessingException {
        List<String> paytmMovieCodes = new ArrayList();
        List<UpcomingMovieDatum> upcomingMovieData = getUpcomingMovies().getUpcomingMovieData();
        for (int i = 0; i < upcomingMovieData.size(); i++) {
            paytmMovieCodes.add(upcomingMovieData.get(i).getPaytmMovieCode());
        }
        return paytmMovieCodes;
    }

    public List<String> getMovieNameContentAvailable0() throws JsonProcessingException {
        UpcomingMovies upcomingMovies = getUpcomingMovies();
        List<String> movieNames = new ArrayList<>();
        for (int i = 0; i < upcomingMovies.getUpcomingMovieData().size(); i++) {
            if (upcomingMovies.getUpcomingMovieData().get(i).getIsContentAvailable()==0){
                movieNames.add(upcomingMovies.getUpcomingMovieData().get(i).getMovieName());
            }
        }
        return movieNames;
    }

}
