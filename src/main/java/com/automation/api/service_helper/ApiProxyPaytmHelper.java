package com.automation.api.service_helper;

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
        UpcomingMovies upcomingMovies = objectMapper.readValue(response.getBody().toString(), UpcomingMovies.class);
        return upcomingMovies;
    }

    public List<String> getAllPaytmMovieCodes() throws JsonProcessingException {
        List<String> paytmMovieCodes = new ArrayList();
        for (int i = 0; i < getUpcomingMovies().getUpcomingMovieData().size(); i++) {
            paytmMovieCodes.add(getUpcomingMovies().getUpcomingMovieData().get(i).getPaytmMovieCode());
        }
        return paytmMovieCodes;
    }

    public boolean isPaytmMovieCodeUnique(String paytmMovieCode) throws JsonProcessingException {
        int count= 0;
        boolean isPaytmMovieCodeUnique = false;
        for (int i = 0; i < getAllPaytmMovieCodes().size(); i++) {
            if (getAllPaytmMovieCodes().get(i).equalsIgnoreCase(paytmMovieCode)){
                count++;
            }
        } if (count<2) isPaytmMovieCodeUnique=true;
        return isPaytmMovieCodeUnique;
    }
}
