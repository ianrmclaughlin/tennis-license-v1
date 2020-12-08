package com.imglicense;

public class LicensesWrapper {
    public JsonLicense[] licenses;
    LicensesWrapper(JsonLicense[] jsonLicenses){
        licenses = jsonLicenses;
    }
}
