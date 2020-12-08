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

//    @GetMapping("/licenses/{customerId}")
//    String controllerMethod(@PathVariable String customerId) throws IOException {
//        return tennisLicenseService.getLicense(customerId);
//    }

//    @GetMapping("/licenses/{customerId}")
//    String controllerMethod1(@PathVariable String customerId) throws IOException {
//        return tennisLicenseService.getLicense(customerId);
//    }

    @GetMapping("/licenses/{customerId}")
    String controllerMethod2(
            @PathVariable("customerId") String customerId,
            @RequestParam(value = "summary", required = false) String summary) throws IOException {

        String licenseShortSummary;
        if (summary == null) {
            licenseShortSummary = tennisLicenseService.getLicense(customerId);
        } else if (summary.equals("AvB")) {
            licenseShortSummary = tennisLicenseService.getLicenseShortSummary(customerId);
        } else if (summary.equals("AvBTime")) {
            licenseShortSummary = tennisLicenseService.getLicenseLongSummary(customerId);
        } else {
            licenseShortSummary = null;
        }
        return licenseShortSummary;
    }
}

