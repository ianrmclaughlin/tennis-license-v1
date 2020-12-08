package com.imglicense;

import com.imglicense.utils.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TennisLicenseHelper {
    public static void createTestFileSingleRecord() throws IOException {
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
        Path path = Paths.get("src/main/resources/tennis-data-file.csv"); // TODO remove
        Files.write(path, bytes);
    }

    public static void createTestFileSingleRecordFuture() throws IOException {
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
        Path path = Paths.get("src/main/resources/tennis-data-file.csv"); // TODO remove
        Files.write(path, bytes);
    }

    public static String createTestResponseSingleLicense() {
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

    public static String createTestResponseSingleLicenseShortSummary() {
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

    public static String createTestResponseSingleLicenseLongSummary() {
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

}
