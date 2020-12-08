package com.imglicense.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonResponse {
    private final JsonLicense[] licenses;

    public JsonResponse(JsonLicense[] jl) {
        licenses = jl;
    }

    public String toString() {
        ObjectMapper om = new ObjectMapper();
        String jsonString = null;
        try {
            LicensesWrapper lw = new LicensesWrapper(licenses);
            jsonString = om.writeValueAsString(lw);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
