package com.assignment.car.lease.common;

/**
 * Class containing list of constants that are to be used throughout the application
 *
 * @author Saunak
 */

public final class ApplicationConstants {

    private ApplicationConstants() {
        // restrict instantiation
    }

    public static final String SUCCESSFUL = "SUCCESSFUL";
    public static final String DUPLICATE_REFERENCE = "DUPLICATE_REFERENCE";
    public static final String BAD_REQUEST = "Invalid format of customer data received";
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";

    public static final String CUSTOMER_IS_NULL = "Customer is null";
    public static final String CUSTOMER_DOB_IS_NULL = "Invalid customer object received, missing date of birth";
    public static final String ERROR_IN_PROCESSING_AGE = "Pension Age can not be calculated";
}