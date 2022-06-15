package com.assignment.car.lease.util;

public class CarLeaseTestConstants {

  public static final String badCustomerPostResponse = "{}";

  public static final String goodLeaseCalcRequest =
      "    {\n"
          + "        \"mileage\": 45000.00,\n"
          + "        \"duration\": 60,\n"
          + "        \"interestRate\": 4.500,\n"
          + "        \"netPrice\": 63000.00\n"
          + "    }";
  public static final String signUpRequest =
      "{\n"
          + "    \"username\": \"test2\",\n"
          + "    \"email\": \"test2@test2.com\",\n"
          + "    \"password\": \"test2password\",\n"
          + "    \"role\": [\"user\",\"mod\",\"admin\"]\n"
          + "}";

  public static final String signInRequest =
      "{\n" + "    \"username\": \"test2\",\n" + "    \"password\": \"test2password\"\n" + "}";

  public static final String badSignInRequest =
          "{\n" + "    \"username\": \"test1\",\n" + "    \"password\": \"test2password\"\n" + "}";

  public static final String addGoodCustomer =
      "    {\n"
          + "        \"id\": 9,\n"
          + "        \"name\": \"CUSTOMER 9 added\",\n"
          + "        \"street\": \"STREET 9\",\n"
          + "        \"houseNumber\": \"HOUSE 9\",\n"
          + "        \"zipCode\": \"ZIP9\",\n"
          + "        \"place\": \"PLACE9\",\n"
          + "        \"emailAddress\": \"TEST9@EMAIL.COM\",\n"
          + "        \"phoneNumber\": \"1234567891\",\n"
          + "        \"createdAt\": \"2022-06-14T22:00:00.000+00:00\",\n"
          + "        \"updatedAt\": null\n"
          + "    }";
}
