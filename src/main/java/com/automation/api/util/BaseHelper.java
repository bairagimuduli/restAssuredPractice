package com.automation.api.util;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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

    /**
     * writeToSheet
     *
     * @param data
     */
    public void writeToExcelSheet(List<String> data) {
        Workbook workbook = new XSSFWorkbook();
        logger.info("================== creating sheet ==================");
        Sheet sheet = workbook.createSheet("Movie Name");
        logger.info("================== writing values to sheet ==================");
        Row headerRow = sheet.createRow(0);
        Cell headerRowCell = headerRow.createCell(0);
        headerRowCell.setCellValue("Movie name with content available 0");
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Cell cell = row.createCell(0);
            cell.setCellValue(data.get(i));
        }
        try {
            FileOutputStream outputStream = new FileOutputStream("src/main/resources/" + "test.xlsx");
            logger.info("================== creating the excel file ==================");
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
