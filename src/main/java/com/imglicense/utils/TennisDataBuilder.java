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
        TennisData tennisData = new TennisData();
        TennisData.customerId = customerId;
        TennisData.matchId = matchId;
        TennisData.startDate = startDate;
        TennisData.playerA = playerA;
        TennisData.playerB = playerB;
        return tennisData;
    }

}
