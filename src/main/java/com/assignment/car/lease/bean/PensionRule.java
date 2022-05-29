package com.assignment.car.lease.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
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
public class PensionRule {

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate bornBefore;

    private int aowAge;

    @JsonProperty("final")
    private String finalIndicator;
}
