package com.imglicense;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// @PropertySource("classpath:application.properties")      TODO delete


@Component

@Repository
public class TennisLicenseRepository {

    // TODO properties file
    int CUSTOMER_ID = 0;
    int MATCH_ID = 1;
    int START_DATE = 2;
    int PLAYER_A = 3;
    int PLAYER_B = 4;
    final private String tennisDataFile = "src/main/resources/tennis-data-file.csv"; // TODO remove - have as command line param

    public String getLicense(String customerId) throws IOException {
        Path path = Paths.get(tennisDataFile);
        byte[] bytes = Files.readAllBytes(path);
        String fileContent = new String(bytes);
        String[] fileLines = fileContent.split("\n");
        List<TennisLicense> tennisLicenses = new ArrayList<>();
        for (String line : fileLines) {
            String[] tokens = line.split(",");
            // TODO put these numbers in a properties file
            if (tokens[CUSTOMER_ID].equals(customerId)) {
                TennisLicense tl1 = new TennisLicense();
                tl1.matchId = tokens[MATCH_ID];
                tl1.startDate = tokens[START_DATE];
                tl1.playerA = tokens[PLAYER_A];
                tl1.playerB = tokens[PLAYER_B];
                tennisLicenses.add(tl1);
            }
        }
        // TODO use builder
        TennisLicense[] tennisLicenseArray = tennisLicenses.toArray(new TennisLicense[0]);
        TennisLicenses tls = new TennisLicenses(tennisLicenseArray);
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(tls);
    }

    public String getLicenseShortSummary(String customerId) throws IOException {
        Path path = Paths.get(tennisDataFile);
        byte[] bytes = Files.readAllBytes(path);
        String fileContent = new String(bytes);
        String[] fileLines = fileContent.split("\n");
        List<TennisLicense> tennisLicenses = new ArrayList<>();
        for (String line : fileLines) {
            String[] tokens = line.split(",");
            // TODO put these numbers in a properties file
            if (tokens[CUSTOMER_ID].equals(customerId)) {
                TennisLicense tl1 = new TennisLicense();
                tl1.matchId = tokens[MATCH_ID];
                tl1.startDate = tokens[START_DATE];
                tl1.playerA = tokens[PLAYER_A];
                tl1.playerB = tokens[PLAYER_B];
                tl1.summary = tokens[PLAYER_A] + " vs " + tokens[PLAYER_B];
                tennisLicenses.add(tl1);
            }
        }
        // TODO use builder
        TennisLicense[] tennisLicenseArray = tennisLicenses.toArray(new TennisLicense[0]);
        TennisLicenses tls = new TennisLicenses(tennisLicenseArray);
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(tls);
    }

    public String getLicenseLongSummary(String customerId) throws IOException {
        // TODO refactor
        Path path = Paths.get(tennisDataFile);
        byte[] bytes = Files.readAllBytes(path);
        String fileContent = new String(bytes);
        String[] fileLines = fileContent.split("\n");
        List<TennisLicense> tennisLicenses = new ArrayList<>();
        for (String line : fileLines) {
            String[] tokens = line.split(",");
            // TODO put these numbers in a properties file
            if (tokens[CUSTOMER_ID].equals(customerId)) {
                TennisLicense tl1 = new TennisLicense();
                tl1.matchId = tokens[MATCH_ID];
                tl1.startDate = tokens[START_DATE];
                tl1.playerA = tokens[PLAYER_A];
                tl1.playerB = tokens[PLAYER_B];
                String minsInPast = getMinsInPast(tokens[START_DATE]);
                if(minsInPast.startsWith("-")){
                    minsInPast = minsInPast.replace("-", "");
                    tl1.summary = tokens[PLAYER_A] + " vs " + tokens[PLAYER_B] + " starts in " + minsInPast + " minutes";
                } else {
                    tl1.summary = tokens[PLAYER_A] + " vs " + tokens[PLAYER_B] + " started " + minsInPast + " minutes ago";
                }
                tennisLicenses.add(tl1);
            }
        }
        // TODO use builder
        TennisLicense[] tennisLicenseArray = tennisLicenses.toArray(new TennisLicense[0]);
        TennisLicenses tls = new TennisLicenses(tennisLicenseArray);
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(tls);
    }

    public String getMinsInPast(String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime ldt = LocalDateTime.parse(dateString, dtf);
        long dateStringSeconds = ldt.toEpochSecond(ZoneOffset.UTC);
        LocalDateTime ldt2 = LocalDateTime.now();
        long nowSeconds = ldt2.toEpochSecond(ZoneOffset.UTC);
        long duration = nowSeconds - dateStringSeconds;
        long minsInPast = duration / 60;
        String minsInPastString = String.valueOf(minsInPast);
        return minsInPastString;
    }
}
