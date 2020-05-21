package com.automation.api.util;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseHelper {

    Logger logger = LogManager.getLogger(BaseHelper.class);

    /**
     * get
     *
     * @param baseURI
     * @param pathParam
     * @return
     */
    public Response get(String baseURI, String pathParam) {
        RestAssured.baseURI = baseURI;
        logger.info("==============================================================");
        logger.info(baseURI + "/" + pathParam);
        Response response = RestAssured.get(pathParam);
        logger.info("==============================================================");
        return response;
    }

}
