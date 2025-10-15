package edu.manipal.btlab.dto;

import edu.manipal.btlab.entity.BusinessRuleType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BusinessRuleDTO {
    private Long id;
    private BusinessRuleType ruleTypeId;
    private String ruleValue;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private LocalDateTime createdAt;

    public BusinessRuleDTO() {

    }

    public BusinessRuleDTO(Long id, BusinessRuleType ruleTypeId, String ruleValue, LocalDate effectiveFrom, LocalDate effectiveTo, LocalDateTime createdAt) {
        this.id = id;
        this.ruleTypeId = ruleTypeId;
        this.ruleValue = ruleValue;
        this.effectiveFrom = effectiveFrom;
        this.effectiveTo = effectiveTo;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusinessRuleType getRuleTypeId() {
        return ruleTypeId;
    }

    public void setRuleTypeId(BusinessRuleType ruleTypeId) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
