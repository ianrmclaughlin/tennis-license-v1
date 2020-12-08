package com.imglicense.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TennisLicenseRepository {

    private final int CUSTOMER_ID = 0;
    private final int MATCH_ID = 1;
    private final int START_DATE = 2;
    private final int PLAYER_A = 3;
    private final int PLAYER_B = 4;

    // TODO refactor
    public String getLicense(String customerId) throws IOException {
        String[] fileLines = readDataFile();
        List<TennisLicense> tennisLicenses = new ArrayList<>();
        for (String line : fileLines) {
            String[] tokens = line.split(",");
            if (tokens[CUSTOMER_ID].equals(customerId)) {
                TennisLicense tl1 = new TennisLicense();
                tl1.matchId = tokens[MATCH_ID];
                tl1.startDate = tokens[START_DATE];
                tl1.playerA = tokens[PLAYER_A];
                tl1.playerB = tokens[PLAYER_B];
                tennisLicenses.add(tl1);
            }
        }
        return getLicences(tennisLicenses);
    }

    public String getLicenseShortSummary(String customerId) throws IOException {
        String[] fileLines = readDataFile();
        List<TennisLicense> tennisLicenses = new ArrayList<>();
        for (String line : fileLines) {
            String[] tokens = line.split(",");
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
        return getLicences(tennisLicenses);
    }

    public String getLicenseLongSummary(String customerId) throws IOException {
        String[] fileLines = readDataFile();
        List<TennisLicense> tennisLicenses = new ArrayList<>();
        for (String line : fileLines) {
            String[] tokens = line.split(",");
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
        return getLicences(tennisLicenses);
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

    private String getLicences(List<TennisLicense> tennisLicenses) throws JsonProcessingException {
        TennisLicense[] tennisLicenseArray = tennisLicenses.toArray(new TennisLicense[0]);
        TennisLicenses tls = new TennisLicenses(tennisLicenseArray);
        ObjectMapper om = new ObjectMapper();
        String s = om.writeValueAsString(tls);
        return s;
    }

    private String[] readDataFile() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(getFilename()));
        String fileContent = new String(bytes);
        return fileContent.split("\n");
    }

    @Value("${tennis.datafile}")
    String dataFile;
    private String getFilename(){
        String filename;
        if (dataFile==null) {
            filename = "src/main/resources/tennis-data-file.csv";
        } else {
            filename = dataFile;
        }
        return filename;
    }
}
