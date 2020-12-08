package com.imglicense.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imglicense.utils.*;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Component
public class TennisLicenseRepositoryTest {

// TODO
//    Note: C:\Users\ian_m\IdeaProjects\tennis-license-v1\src\test\java\com\imglicense\repository\TennisLicenseRepositoryTest.java uses unchecked or unsafe operations.
//    Note: Recompile with -Xlint:unchecked for details.

    TennisLicenseHelper tlh = new TennisLicenseHelper();

    @Test
    public void givenCustomerId_whenGet_thenGetTennisLicense() throws IOException {

        // given
        tlh.createTestFileSingleRecord();
        TennisLicenseRepository tennisLicenseRepository = new TennisLicenseRepository();

        // when
        String licenses = tennisLicenseRepository.getLicense("123");

        // then
        String expectedResponse = tlh.createTestResponseSingleLicense();
        ObjectMapper om = new ObjectMapper();
        Assert.assertEquals(om.readTree(expectedResponse), om.readTree(licenses));
    }

    @Test
    public void givenCustomerId_whenGet_thenGetMultipleTennisLicenses() throws IOException {

        // given

        String fileContent = "";
        TennisDataBuilder tennisDataBuilder = new TennisDataBuilder();
        TennisData tennisData1 = tennisDataBuilder
                .withCustomerId("123")
                .withMatchId("111")
                .withStartDate("01/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Nadal")
                .build();
        fileContent += tennisData1.toString() + "\n";
        TennisData tennisData2 = tennisDataBuilder
                .withCustomerId("123")
                .withMatchId("222")
                .withStartDate("02/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Federer")
                .build();
        fileContent += tennisData2.toString() + "\n";
        TennisData tennisData3 = tennisDataBuilder
                .withCustomerId("123")
                .withMatchId("333")
                .withStartDate("03/01/2000 00:00")
                .withPlayerA("Federer")
                .withPlayerB("Nadal")
                .build();
        fileContent += tennisData3.toString();

        byte[] bytes = fileContent.getBytes();
        Path path = Paths.get(tlh.getDataFile());
        Files.write(path, bytes);
        TennisLicenseRepository tennisLicenseRepository = new TennisLicenseRepository();

        // when
        String licenses = tennisLicenseRepository.getLicense("123");

        // then
        JsonLicenseBuilder jsonLicenseBuilder = new JsonLicenseBuilder();

        JsonLicense jsonLicense1 = jsonLicenseBuilder
                .withMatchId("111")
                .withStartDate("01/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Nadal")
                .build();
        JsonLicense jsonLicense2 = jsonLicenseBuilder
                .withMatchId("222")
                .withStartDate("02/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Federer")
                .build();
        JsonLicense jsonLicense3 = jsonLicenseBuilder
                .withMatchId("333")
                .withStartDate("03/01/2000 00:00")
                .withPlayerA("Federer")
                .withPlayerB("Nadal")
                .build();

        JsonResponseBuilder jsonResponseBuilder = new JsonResponseBuilder();

        JsonResponse jsonResponse = jsonResponseBuilder
                .withLicense(jsonLicense1)
                .withLicense(jsonLicense2)
                .withLicense(jsonLicense3)
                .build();

        String expResponse = jsonResponse.toString();
        ObjectMapper om = new ObjectMapper();
        Assert.assertEquals(om.readTree(expResponse), om.readTree(licenses));
    }

    @Test
    public void givenTwoCustomerIds_whenGet_thenGetMultipleTennisLicenses() throws IOException {

        // given

        String fileContent = "";

        TennisDataBuilder tennisDataBuilder = new TennisDataBuilder();
        TennisData tennisData1 = tennisDataBuilder
                .withCustomerId("123")
                .withMatchId("111")
                .withStartDate("01/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Nadal")
                .build();
        fileContent += tennisData1.toString() + "\n";
        TennisData tennisData2 = tennisDataBuilder
                .withCustomerId("123")
                .withMatchId("222")
                .withStartDate("02/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Federer")
                .build();
        fileContent += tennisData2.toString() + "\n";
        TennisData tennisData3 = tennisDataBuilder
                .withCustomerId("123")
                .withMatchId("333")
                .withStartDate("03/01/2000 00:00")
                .withPlayerA("Federer")
                .withPlayerB("Nadal")
                .build();
        fileContent += tennisData3.toString() + "\n";
        TennisData tennisData4 = tennisDataBuilder
                .withCustomerId("456")
                .withMatchId("444")
                .withStartDate("04/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Nadal")
                .build();
        fileContent += tennisData4.toString() + "\n";
        TennisData tennisData5 = tennisDataBuilder
                .withCustomerId("456")
                .withMatchId("555")
                .withStartDate("05/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Federer")
                .build();
        fileContent += tennisData5.toString() + "\n";
        TennisData tennisData6 = tennisDataBuilder
                .withCustomerId("456")
                .withMatchId("666")
                .withStartDate("06/01/2000 00:00")
                .withPlayerA("Federer")
                .withPlayerB("Nadal")
                .build();
        fileContent += tennisData6.toString();

        byte[] bytes = fileContent.getBytes();
        Path path = Paths.get(tlh.getDataFile());
        Files.write(path, bytes);
        TennisLicenseRepository tennisLicenseRepository = new TennisLicenseRepository();

        // when
        String licenses1 = tennisLicenseRepository.getLicense("123");
        String licenses2 = tennisLicenseRepository.getLicense("456");

        // then
        JsonLicenseBuilder jsonLicenseBuilder = new JsonLicenseBuilder();

        JsonLicense jsonLicense1 = jsonLicenseBuilder
                .withMatchId("111")
                .withStartDate("01/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Nadal")
                .build();
        JsonLicense jsonLicense2 = jsonLicenseBuilder
                .withMatchId("222")
                .withStartDate("02/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Federer")
                .build();
        JsonLicense jsonLicense3 = jsonLicenseBuilder
                .withMatchId("333")
                .withStartDate("03/01/2000 00:00")
                .withPlayerA("Federer")
                .withPlayerB("Nadal")
                .build();
        JsonLicense jsonLicense4 = jsonLicenseBuilder
                .withMatchId("444")
                .withStartDate("04/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Nadal")
                .build();
        JsonLicense jsonLicense5 = jsonLicenseBuilder
                .withMatchId("555")
                .withStartDate("05/01/2000 00:00")
                .withPlayerA("Djokovic")
                .withPlayerB("Federer")
                .build();
        JsonLicense jsonLicense6 = jsonLicenseBuilder
                .withMatchId("666")
                .withStartDate("06/01/2000 00:00")
                .withPlayerA("Federer")
                .withPlayerB("Nadal")
                .build();

        JsonResponseBuilder jsonResponseBuilder1 = new JsonResponseBuilder();
        JsonResponse jsonResponse1 = jsonResponseBuilder1
                .withLicense(jsonLicense1)
                .withLicense(jsonLicense2)
                .withLicense(jsonLicense3)
                .build();

        JsonResponseBuilder jsonResponseBuilder2 = new JsonResponseBuilder();
        JsonResponse jsonResponse2 = jsonResponseBuilder2
                .withLicense(jsonLicense4)
                .withLicense(jsonLicense5)
                .withLicense(jsonLicense6)
                .build();

        ObjectMapper om = new ObjectMapper();
        String expResponse1 = jsonResponse1.toString();
        Assert.assertEquals(om.readTree(expResponse1), om.readTree(licenses1));
        String expResponse2 = jsonResponse2.toString();
        Assert.assertEquals(om.readTree(expResponse2), om.readTree(licenses2));
    }

    @Test
    public void givenShortSummaryRequest_whenGet_thenGetShortSummary() throws IOException {

        // given
        tlh.createTestFileSingleRecord();
        TennisLicenseRepository tennisLicenseRepository = new TennisLicenseRepository();

        // when
        String licenses = tennisLicenseRepository.getLicenseShortSummary("123");

        // then
        String expectedResponse = tlh.createTestResponseSingleLicenseShortSummary();
        ObjectMapper om = new ObjectMapper();
        Assert.assertEquals(om.readTree(expectedResponse), om.readTree(licenses));
    }

    @Test
    public void givenLongSummaryRequest_whenGet_thenGetLongSummary() throws IOException {

        // given
        tlh.createTestFileSingleRecord();
        TennisLicenseRepository tennisLicenseRepository = new TennisLicenseRepository();

        // when
        String licenses = tennisLicenseRepository.getLicenseLongSummary("123");

        // then
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> mso1 = om.readValue(licenses, Map.class);
        List<Map<String, Object>> list = (List<Map<String, Object>>) mso1.get("licenses");
        Map<String, Object> mso2 = list.get(0);
        String summaryString = (String) mso2.get("summary");

        Assert.assertTrue("verify summary string is correct", summaryString.matches("Djokovic vs Nadal started 11[0-9][0-9][0-9][0-9][0-9][0-9] minutes ago"));
    }

    @Test
    public void givenLongSummaryRequest_whenGet_thenGetLongSummaryFuture() throws IOException {

        // given
        tlh.createTestFileSingleRecordFuture();
        TennisLicenseRepository tennisLicenseRepository = new TennisLicenseRepository();

        // when
        String licenses = tennisLicenseRepository.getLicenseLongSummary("123");

        // then
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> mso1 = om.readValue(licenses, Map.class);
        List<Map<String, Object>> list = (List<Map<String, Object>>) mso1.get("licenses");
        Map<String, Object> mso2 = list.get(0);
        String summaryString = (String) mso2.get("summary");

        Assert.assertTrue(
                "summaryString=" + summaryString,
                summaryString.matches("Djokovic vs Nadal starts in 41[0-9][0-9][0-9][0-9][0-9][0-9] minutes"));
    }

    @Test
    public void givenDate_whenCalculateMins_thenMinsInPast() {

        TennisLicenseRepository tennisLicenseRepository = new TennisLicenseRepository();

        // given
        String dateString = "01/01/2000 00:00";

        // when
        String minutesString = tennisLicenseRepository.getMinsInPast(dateString);

        // then
        Assert.assertTrue(
                "minutesString=" + minutesString,
                minutesString.matches("11[0-9][0-9][0-9][0-9][0-9][0-9]"));
    }

    @Test
    public void givenDate_whenCalculateMins_thenMinsInFuture() {

        TennisLicenseRepository tennisLicenseRepository = new TennisLicenseRepository();

        // given
        String dateString = "01/01/2099 00:00";

        // when
        String minutesString = tennisLicenseRepository.getMinsInPast(dateString);

        // then
        Assert.assertTrue(
                "minutesString=" + minutesString,
                minutesString.matches("-41[0-9][0-9][0-9][0-9][0-9][0-9]"));
    }

}
