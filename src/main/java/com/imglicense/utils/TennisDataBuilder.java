package com.imglicense.utils;

public class TennisDataBuilder {

    String customerId;
    String matchId;
    String startDate;
    String playerA;
    String playerB;

    public TennisDataBuilder withCustomerId(String ci) {
        customerId = ci;
        return this;
    }

    public TennisDataBuilder withMatchId(String mi) {
        matchId = mi;
        return this;
    }

    public TennisDataBuilder withStartDate(String sd) {
        startDate = sd;
        return this;
    }

    public TennisDataBuilder withPlayerA(String pa) {
        playerA = pa;
        return this;
    }
    public TennisDataBuilder withPlayerB(String pb) {
        playerB = pb;
        return this;
    }

    public TennisData build() {
        // TODO investigate intellij highlighted issues
        TennisData tennisData = new TennisData();
        tennisData.customerId = customerId;
        tennisData.matchId = matchId;
        tennisData.startDate = startDate;
        tennisData.playerA = playerA;
        tennisData.playerB = playerB;
        return tennisData;
    }

}
