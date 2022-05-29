package com.assignment.car.lease.bean;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Data object for the response payload
 *
 * @author Saunak
 */
@Value
@Builder
@Jacksonized
public class PensionRuleSet {
    private List<PensionRule> pensionRuleList;
}
