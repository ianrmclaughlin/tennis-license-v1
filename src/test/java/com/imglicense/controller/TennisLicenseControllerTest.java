package com.imglicense.controller;

import com.imglicense.utils.TennisLicenseHelper;
import com.imglicense.service.TennisLicenseService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TennisLicenseControllerTest {

    TennisLicenseHelper tlh = new TennisLicenseHelper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TennisLicenseService tennisLicenseService;

    @Test
    public void givenCustomerId_whenRequestLicense_thenReturnLicense() throws Exception {

        // Given
        String customerId = "123";
        String expectedResponse = tlh.createTestResponseSingleLicense();
        MockitoAnnotations.initMocks(this);
        Mockito.when(tennisLicenseService.getLicense(customerId)).thenReturn(expectedResponse);
        MockHttpServletRequestBuilder mockBuilder = MockMvcRequestBuilders.get("/licenses/" + customerId);

        // when
        String responseString = mockMvc.perform(mockBuilder).andReturn().getResponse().getContentAsString();

        // then
        Assert.assertEquals(expectedResponse, responseString);

    }

    @Test
    public void givenShortSummaryRequest_whenRequestLicense_thenReturnShortSummary() throws Exception {

        // Given
        String customerId = "123";
        String expectedResponse = tlh.createTestResponseSingleLicenseShortSummary();
        MockitoAnnotations.initMocks(this);
        Mockito.when(tennisLicenseService.getLicenseShortSummary(customerId)).thenReturn(expectedResponse);
        MockHttpServletRequestBuilder mockBuilder = MockMvcRequestBuilders.get("/licenses/" + customerId + "?summary=AvB");

        // when
        String responseString = mockMvc.perform(mockBuilder).andReturn().getResponse().getContentAsString();

        // then
        Assert.assertEquals(expectedResponse, responseString);
    }

    @Test
    public void givenLongSummaryRequest_whenRequestLicense_thenReturnLongSummary() throws Exception {

        // Given
        String customerId = "123";
        String expectedResponse = tlh.createTestResponseSingleLicenseShortSummary();
        MockitoAnnotations.initMocks(this);
        Mockito.when(tennisLicenseService.getLicenseLongSummary(customerId)).thenReturn(expectedResponse);
        MockHttpServletRequestBuilder mockBuilder = MockMvcRequestBuilders.get("/licenses/" + customerId + "?summary=AvBTime");

        // when
        String responseString = mockMvc.perform(mockBuilder).andReturn().getResponse().getContentAsString();

        // then
        Assert.assertEquals(expectedResponse, responseString);
    }

}
