package com.imglicense.service;

import com.imglicense.repository.TennisLicenseRepository;
import com.imglicense.service.TennisLicenseService;
import com.imglicense.utils.TennisLicenseHelper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Component;

import java.io.IOException;

// TODO put in service folder
@Component
public class TennisLicenseServiceTest {

    TennisLicenseHelper tlh = new TennisLicenseHelper();

    @InjectMocks
    TennisLicenseService tennisLicenseService;

    @Mock
    TennisLicenseRepository tennisLicenseRepository;

    @Test
    public void givenCustomerId_whenGet_thenCustomerDetailsReturned() throws IOException {

        // given
        String customerId = "123";
        MockitoAnnotations.initMocks(this);
        tlh.createTestFileSingleRecord();
        String expectedResponse = tlh.createTestResponseSingleLicense();
        Mockito.when(tennisLicenseRepository.getLicense(customerId)).thenReturn(expectedResponse);

        // when
        String jsonString = tennisLicenseService.getLicense(customerId);

        // then
        Assert.assertEquals(expectedResponse, jsonString);
    }

    @Test
    public void givenShortSummaryRequest_whenGet_thenShortSummaryReturned() throws IOException {

        // given
        String customerId = "123";
        MockitoAnnotations.initMocks(this);
        tlh.createTestFileSingleRecord();
        String expectedResponse = tlh.createTestResponseSingleLicenseShortSummary();
        Mockito.when(tennisLicenseRepository.getLicenseShortSummary(customerId)).thenReturn(expectedResponse);

        // when
        String jsonString = tennisLicenseService.getLicenseShortSummary(customerId);

        // then
        Assert.assertEquals(expectedResponse, jsonString);
    }
}
