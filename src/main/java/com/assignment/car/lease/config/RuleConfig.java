package com.assignment.car.lease.config;

import com.assignment.car.lease.bean.PensionRule;
import com.assignment.car.lease.bean.PensionRuleSet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Configuration
public class RuleConfig {

    @Autowired
    private ResourceLoader resourceLoader;
    private Object sortByDateComparator;

    private ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    @SneakyThrows
    public PensionRuleSet pensionRuleSet() {
        File file = ResourceUtils.getFile("classpath:rule.json");
        PensionRule[] pensionRules = objectMapper().readValue(file, PensionRule[].class);
        log.info(String.valueOf(pensionRules.length));

        Comparator<PensionRule> comparator = Comparator.comparing(PensionRule::getBornBefore);
        List<PensionRule> pensionRulesList = new java.util.ArrayList<>(List.of(pensionRules));
        pensionRulesList.sort(comparator);
        return PensionRuleSet
                .builder()
                .pensionRuleList(pensionRulesList)
                .build();
    }

}
