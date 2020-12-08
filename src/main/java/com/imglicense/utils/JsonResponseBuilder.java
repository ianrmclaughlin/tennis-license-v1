package com.imglicense.utils;

import java.util.ArrayList;
import java.util.List;

public class JsonResponseBuilder {

    List<JsonLicense> jsonLicenses = new ArrayList<>();

    public JsonResponseBuilder withLicense(JsonLicense jsonLicense) {
        jsonLicenses.add(jsonLicense);
        return this;
    }

    public JsonResponse build() {
        JsonLicense[] jsonLicenseArray = jsonLicenses.toArray(new JsonLicense[0]);
        JsonResponse jsonResponse = new JsonResponse(jsonLicenseArray);
        return jsonResponse;
    }
}
