package com.imglicense.utils;

import com.imglicense.utils.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TennisLicenseHelper {

    public void createTestFileSingleRecord() throws IOException {
        TennisDataBuilder tennisDataBuilder = new TennisDataBuilder();
        TennisData tennisData = tennisDataBuilder
                .withCustomerId("123")
                .withMatchId("111")
                .withStartDate("01/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Nadal")
                .build();
        String fileContent = tennisData.toString();
        byte[] bytes = fileContent.getBytes();
        Path path = Paths.get(getDataFile());
        Files.write(path, bytes);
    }

    public void createTestFileSingleRecordFuture() throws IOException {
        TennisDataBuilder tennisDataBuilder = new TennisDataBuilder();
        TennisData tennisData = tennisDataBuilder
                .withCustomerId("123")
                .withMatchId("111")
                .withStartDate("01/01/2099 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Nadal")
                .build();
        String fileContent = tennisData.toString();
        byte[] bytes = fileContent.getBytes();
        Path path = Paths.get(getDataFile());
        Files.write(path, bytes);
    }

    public String createTestResponseSingleLicense() {
        JsonLicenseBuilder jsonLicenseBuilder = new JsonLicenseBuilder();
        JsonLicense jsonLicense = jsonLicenseBuilder
                .withMatchId("111")
                .withStartDate("01/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Nadal")
                .build();
        JsonResponseBuilder jsonResponseBuilder = new JsonResponseBuilder();
        JsonResponse jsonResponse = jsonResponseBuilder
                .withLicense(jsonLicense)
                .build();
        String expectedResponse = jsonResponse.toString();
        return expectedResponse;
    }

    public String createTestResponseSingleLicenseShortSummary() {
        JsonLicenseBuilder jsonLicenseBuilder = new JsonLicenseBuilder();
        JsonLicense jsonLicense = jsonLicenseBuilder
                .withMatchId("111")
                .withStartDate("01/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Nadal")
                .withSummary("Djokovic vs Nadal")
                .build();
        JsonResponseBuilder jsonResponseBuilder = new JsonResponseBuilder();
        JsonResponse jsonResponse = jsonResponseBuilder
                .withLicense(jsonLicense)
                .build();
        String expectedResponse = jsonResponse.toString();
        return expectedResponse;
    }

    public String createTestResponseSingleLicenseLongSummary() {
        JsonLicenseBuilder jsonLicenseBuilder = new JsonLicenseBuilder();
        JsonLicense jsonLicense = jsonLicenseBuilder
                .withMatchId("111")
                .withStartDate("01/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Nadal")
                .withSummary("Djokovic vs Nadal started 999999999 minutes ago")
                .build();
        JsonResponseBuilder jsonResponseBuilder = new JsonResponseBuilder();
        JsonResponse jsonResponse = jsonResponseBuilder
                .withLicense(jsonLicense)
                .build();
        String expectedResponse = jsonResponse.toString();
        return expectedResponse;
    }

    @Value("${tennis.datafile}")
    String dataFile;

    public String getDataFile() {
        String filename;
        if (dataFile == null) {
            filename = "src/main/resources/tennis-data-file.csv";
        } else {
            filename = dataFile;
        }
        return filename;
    }


}
