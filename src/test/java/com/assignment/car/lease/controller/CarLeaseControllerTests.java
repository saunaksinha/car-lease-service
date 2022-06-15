package com.assignment.car.lease.controller;

import com.assignment.car.lease.util.CarLeaseTestConstants;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CarLeaseControllerTests {

  @Autowired private MockMvc mockMvc;

  private Cookie cookie;

  @Value("${carlease.app.jwtCookieName}")
  private String cookieName;

  @BeforeEach
  @SneakyThrows
  void setup() {
    String uri = "/api/auth/signup";
    String uriSignIn = "/api/auth/signin";
    MvcResult mvcResultSignUp =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(CarLeaseTestConstants.signUpRequest))
            .andReturn();
    MvcResult mvcResultSignIn =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post(uriSignIn)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(CarLeaseTestConstants.signInRequest))
            .andReturn();
    cookie = mvcResultSignIn.getResponse().getCookie(cookieName);
    assertNotNull(cookie, "Cookie is null");
  }

  @Test
  @SneakyThrows
  void whenAddBadCustomer() {
    String uri = "/api/car-lease/customers";
    MvcResult mvcResult =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .cookie(this.cookie)
                    .content(CarLeaseTestConstants.badCustomerPostResponse))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();
    assertEquals(HttpStatus.BAD_REQUEST.value(), status);
  }

  @Test
  @SneakyThrows
  void nullCookie() {
    String uri = "/api/car-lease/customers";
    String uriSignIn = "/api/auth/signin";
    MvcResult mvcResultSignIn =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post(uriSignIn)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(CarLeaseTestConstants.badSignInRequest))
            .andReturn();
    cookie = mvcResultSignIn.getResponse().getCookie("carlease");
    assertNull(cookie, "Cookie is null");
  }

  @SneakyThrows
  @Test
  void whenAddGoodCustomer() {
    String uri = "/api/car-lease/customers";
    MvcResult mvcResult =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .cookie(this.cookie)
                    .content(CarLeaseTestConstants.addGoodCustomer))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();
    assertEquals(HttpStatus.CREATED.value(), status);
  }

  @SneakyThrows
  @Test
  void whenCalcLeaseRate() {
    String uri = "/api/car-lease/calc-lease-rate";
    MvcResult mvcResult =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .cookie(this.cookie)
                    .content(CarLeaseTestConstants.goodLeaseCalcRequest))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();
    assertEquals(HttpStatus.OK.value(), status);
  }
}
