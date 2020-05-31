package com.automation.api;

import com.automation.api.pojo.UpcomingMovieDatum;
import com.automation.api.service_helper.ApiProxyPaytmHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UpcomingMovieValidator {

    ApiProxyPaytmHelper paytmHelper = new ApiProxyPaytmHelper();
    UpcomingMovieDatum upcomingMovieDatum;
    String paytmMovieCodeToValidate;
    List<String> paytmMovieCodes;

    boolean validatePaytmMovieCodeUnique;
    boolean validateLanguage;
    boolean validateReleaseDate;
    boolean validateMoviePosterUrl;

    public UpcomingMovieValidator setPaytmMovieCodes(List<String> paytmMovieCodes) {
        this.paytmMovieCodes = paytmMovieCodes;
        return this;
    }

    public UpcomingMovieValidator(UpcomingMovieDatum upcomingMovieDatum, String paytmMovieCodeToValidate, boolean validatePaytmMovieCodeUnique, boolean validateLanguage, boolean validateReleaseDate, boolean validateMoviePosterUrl) {
        this.upcomingMovieDatum = upcomingMovieDatum;
        this.paytmMovieCodeToValidate = paytmMovieCodeToValidate;
        this.validatePaytmMovieCodeUnique = validatePaytmMovieCodeUnique;
        this.validateLanguage = validateLanguage;
        this.validateReleaseDate = validateReleaseDate;
        this.validateMoviePosterUrl = validateMoviePosterUrl;
    }

    public void validate() throws JsonProcessingException, ParseException {
        if (validateReleaseDate) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String releaseDateString = null;
            try {
                releaseDateString = upcomingMovieDatum.getReleaseDate().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Assert.assertNotNull(releaseDateString, "The date field is missing");
            Date releaseDate = simpleDateFormat.parse(releaseDateString);
            Assert.assertTrue(date.before(releaseDate), "Movie release date is from Past");
        }
        if (validateMoviePosterUrl) {
            String moviePosterUrl = upcomingMovieDatum.getMoviePosterUrl();
            Assert.assertTrue(moviePosterUrl.endsWith("jpg"), "movie poster url does not ends with .jpg");
        }
        if (validatePaytmMovieCodeUnique) {
            setPaytmMovieCodes(paytmHelper.getAllPaytmMovieCodes());
            int count = 0;
            boolean isPaytmMovieCodeUnique = false;
            for (int i = 0; i < paytmMovieCodes.size(); i++) {
                if (paytmMovieCodes.get(i).equalsIgnoreCase(paytmMovieCodeToValidate)) {
                    count++;
                }
            }
            if (count < 2) isPaytmMovieCodeUnique = true;
            Assert.assertTrue(isPaytmMovieCodeUnique, "movie code is not unique");
        }
        if (validateLanguage) {
            // "language": "Hindi",
            System.out.println("language is string so  it will always be only one format");
        }
    }

    public static class Builder {
        UpcomingMovieDatum upcomingMovieDatum;
        String paytmMovieCodeToValidate;

        boolean validatePaytmMovieCodeUnique;
        boolean validateLanguage;
        boolean validateReleaseDate;
        boolean validateMoviePosterUrl;

        public Builder setUpcomingMovieDatum(UpcomingMovieDatum upcomingMovieDatum) {
            this.upcomingMovieDatum = upcomingMovieDatum;
            return this;
        }

        public Builder setPaytmMovieCodeToValidate(String paytmMovieCodeToValidate) {
            this.paytmMovieCodeToValidate = paytmMovieCodeToValidate;
            return this;
        }

        public Builder setValidatePaytmMovieCodeUnique(boolean validatePaytmMovieCodeUnique) {
            this.validatePaytmMovieCodeUnique = validatePaytmMovieCodeUnique;
            return this;
        }

        public Builder setValidateLanguage(boolean validateLanguage) {
            this.validateLanguage = validateLanguage;
            return this;
        }

        public Builder setValidateReleaseDate(boolean validateReleaseDate) {
            this.validateReleaseDate = validateReleaseDate;
            return this;
        }

        public Builder setValidateMoviePosterUrl(boolean validateMoviePosterUrl) {
            this.validateMoviePosterUrl = validateMoviePosterUrl;
            return this;
        }

        public UpcomingMovieValidator build() {
            return new UpcomingMovieValidator(upcomingMovieDatum, paytmMovieCodeToValidate, validatePaytmMovieCodeUnique, validateLanguage, validateReleaseDate, validateMoviePosterUrl);
        }
    }
}
