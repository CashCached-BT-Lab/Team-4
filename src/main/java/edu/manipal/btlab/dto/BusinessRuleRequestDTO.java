package edu.manipal.btlab.dto;

import edu.manipal.btlab.entity.BusinessRuleType;

import java.time.LocalDate;

public class BusinessRuleRequestDTO {
    private Long ruleTypeId;
    private String ruleValue;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;

    public Long getRuleTypeId() {
        return ruleTypeId;
    }

    public void setRuleTypeId(Long ruleTypeId) {
        this.ruleTypeId = ruleTypeId;
    }

    public String getRuleValue() {
        return ruleValue;
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public LocalDate getEffectiveTo() {
        return effectiveTo;
    }

    public void setEffectiveTo(LocalDate effectiveTo) {
        this.effectiveTo = effectiveTo;
    }
}
