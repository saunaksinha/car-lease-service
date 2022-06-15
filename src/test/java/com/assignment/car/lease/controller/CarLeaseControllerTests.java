package com.assignment.car.lease.controller;

import com.assignment.car.lease.util.CarLeaseTestConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenCondition1WithoutCookie() throws Exception {
        String uri = "/api/car-lease/customers";
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(CarLeaseTestConstants.goodCustomerGetResponse))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);
    }

//
//    @Test
//    public void whenCustomerDateOfBirthIsValidFormatThenReturnSuccess() throws Exception {
//        String uri = "/pension/calculate-aow-date";
//        MvcResult mvcResult = mockMvc.perform(
//                        MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(CarLeaseTestConstants.validCustomerInput))
//                .andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.OK.value(), status);
//    }

}
