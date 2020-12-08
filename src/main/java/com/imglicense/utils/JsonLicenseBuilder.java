package com.imglicense.utils;

public class JsonLicenseBuilder {

    String matchId;
    String startDate;
    String playerA;
    String playerB;
    String summary;

    public JsonLicenseBuilder withMatchId(String mi) {
        matchId = mi;
        return this;
    }

    public JsonLicenseBuilder withStartDate(String sd) {
        startDate = sd;
        return this;
    }

    public JsonLicenseBuilder withPlayerA(String pa) {
        playerA = pa;
        return this;
    }

    public JsonLicenseBuilder withPlayerB(String pb) {
        playerB = pb;
        return this;
    }

    public JsonLicenseBuilder withSummary(String s) {
        summary = s;
        return this;
    }

    public JsonLicense build() {
        JsonLicense jl = new JsonLicense();
        jl.matchId = matchId;
        jl.startDate = startDate;
        jl.playerA = playerA;
        jl.playerB = playerB;
        jl.summary = summary;
        return jl;
    }

}