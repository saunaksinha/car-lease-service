package com.assignment.car.lease.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Data object for the response payload
 *
 * @author Saunak
 */
@Value
@Builder
@Jacksonized
public class PensionResultDetails {

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate aowDate;

    private String ageOnAowDate;

    private int durationInMonths;

    private String finalIndicator;

}
