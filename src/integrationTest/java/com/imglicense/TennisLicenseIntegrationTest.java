package com.imglicense;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class TennisLicenseIntegrationTest {

    @Test
    public void singleCustomerSingleLicence() throws IOException {

        // given
        String customerId = "123";
        createTestFileSingleRecord();

        // when
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/licenses/" + customerId;
        String response = restTemplate.getForObject(url, String.class);

        // then
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
        String expResponse = jsonResponse.toString();
        ObjectMapper om = new ObjectMapper();
        Assert.assertEquals(om.readTree(expResponse), om.readTree(response));
    }
    @Test
    public void singleCustomerSingleLicenceShortSummary() throws IOException {

        // given
        String customerId = "123";
        createTestFileSingleRecord();

        // when - hit endpoint /licenses/{id}
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/licenses/" + customerId + "?summary=AvB";
        String response = restTemplate.getForObject(url, String.class);

        // then
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
        String expResponse = jsonResponse.toString();
        ObjectMapper om = new ObjectMapper();
        Assert.assertEquals(om.readTree(expResponse), om.readTree(response));
    }

    @Test
    public void singleCustomerSingleLicenceLongSummary() throws IOException {

        // given
        String customerId = "123";
        createTestFileSingleRecord();

        // when - hit endpoint /licenses/{id}
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/licenses/" + customerId + "?summary=AvBTime";
        String response = restTemplate.getForObject(url, String.class);

        // then
        ObjectMapper om = new ObjectMapper();
        Map<String,Object> mso1 = om.readValue ( response, Map.class );
        List<Map<String,Object>> list = (List<Map<String, Object>>) mso1.get ( "licenses" );
        Map<String,Object> mso2 = (Map<String,Object>)list.get(0);
        String summaryString = (String) mso2.get("summary");

        Assert.assertTrue(
                "summaryString=" + summaryString,
                summaryString.matches("Djokovic vs Nadal started 11[0-9][0-9][0-9][0-9][0-9][0-9] minutes ago"));
    }
    @Test
    public void singleCustomerSingleLicenceLongSummaryFuture() throws IOException {

        // given
        String customerId = "123";
        createTestFileSingleRecordFuture();

        // when - hit endpoint /licenses/{id}
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/licenses/" + customerId + "?summary=AvBTime";
        String response = restTemplate.getForObject(url, String.class);

        // then
        ObjectMapper om = new ObjectMapper();
        Map<String,Object> mso1 = om.readValue ( response, Map.class );
        List<Map<String,Object>> list = (List<Map<String, Object>>) mso1.get ( "licenses" );
        Map<String,Object> mso2 = (Map<String,Object>)list.get(0);
        String summaryString = (String) mso2.get("summary");

        Assert.assertTrue(
                "summaryString=" + summaryString,
                summaryString.matches("Djokovic vs Nadal starts in 41[0-9][0-9][0-9][0-9][0-9][0-9] minutes"));
    }

    // TODO make private
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
//      Path path = Paths.get("C:\\IanMcLaughlin\\tennis-data-file.csv"); // TODO remove
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

}
