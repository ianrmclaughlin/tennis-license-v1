package com.imglicense;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.imglicense.TennisLicenseHelper.*;

@Component
public class TennisLicenseServiceTest {

    @InjectMocks
    TennisLicenseService tennisLicenseService;

    @Mock
    TennisLicenseRepository tennisLicenseRepository;

    @Test
    public void givenCustomerId_whenGet_thenCustomerDetailsReturned() throws IOException {

        // given
        String customerId = "123";
        MockitoAnnotations.initMocks(this);
        createTestFileSingleRecord();
        String expectedResponse = createTestResponseSingleLicense();
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
        createTestFileSingleRecord();
        String expectedResponse = createTestResponseSingleLicenseShortSummary();
        Mockito.when(tennisLicenseRepository.getLicenseShortSummary(customerId)).thenReturn(expectedResponse);

        // when
        String jsonString = tennisLicenseService.getLicenseShortSummary(customerId);

        // then
        Assert.assertEquals(expectedResponse, jsonString);
    }
}
