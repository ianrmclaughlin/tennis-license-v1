package com.imglicense.controller;

import com.imglicense.service.TennisLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TennisLicenseController {

    @Autowired
    TennisLicenseService tennisLicenseService;

    @GetMapping("/licenses/{customerId}")
    String licencesCustomerId(
            @PathVariable("customerId") String customerId,
            @RequestParam(value = "summary", required = false) String summary) throws IOException {

        String license;
        if (summary == null) {
            license = tennisLicenseService.getLicense(customerId);
        } else if (summary.equals("AvB")) {
            license = tennisLicenseService.getLicenseShortSummary(customerId);
        } else if (summary.equals("AvBTime")) {
            license = tennisLicenseService.getLicenseLongSummary(customerId);
        } else {
            license = null;
        }
        return license;
    }
}

