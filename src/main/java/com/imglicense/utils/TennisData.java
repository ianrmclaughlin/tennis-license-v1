package com.imglicense.utils;

public class TennisData {
    // TODO do these really need to be static?
    static String customerId;
    static String matchId;
    static String startDate;
    static String playerA;
    static String playerB;

    public String toString() {
        return customerId + "," + matchId + "," + startDate + "," + playerA + "," + playerB;
    }
}
