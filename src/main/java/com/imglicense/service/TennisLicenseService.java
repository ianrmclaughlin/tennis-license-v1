package com.imglicense.service;

import com.imglicense.repository.TennisLicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TennisLicenseService {

    @Autowired
    TennisLicenseRepository tennisLicenseRepository;

    public String getLicense(String customerId) throws IOException {
        return tennisLicenseRepository.getLicense(customerId);
    }

    public String getLicenseShortSummary(String customerId) throws IOException {
        return tennisLicenseRepository.getLicenseShortSummary(customerId);
    }

    public String getLicenseLongSummary(String customerId) throws IOException {
        return tennisLicenseRepository.getLicenseLongSummary(customerId);
    }
}
