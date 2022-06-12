package com.assignment.car.lease.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class CarLeaseControllerTests {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void whenCustomerDateOfBirthIsInvalidFormatThenReturnError() throws Exception {
//        String uri = "/pension/calculate-aow-date";
//        MvcResult mvcResult = mockMvc.perform(
//                        MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(CarLeaseTestConstants.invalidCustomerInput))
//                .andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), status);
//    }
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
