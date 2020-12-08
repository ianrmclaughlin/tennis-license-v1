package com.imglicense.utils;

import com.imglicense.utils.JsonLicense;

public class LicensesWrapper {
    public JsonLicense[] licenses;
    LicensesWrapper(JsonLicense[] jsonLicenses){
        licenses = jsonLicenses;
    }
}
