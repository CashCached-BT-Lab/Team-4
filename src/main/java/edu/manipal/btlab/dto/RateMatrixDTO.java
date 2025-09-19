package edu.manipal.btlab.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RateMatrixDTO {
    private Long id;
    private String customerType;
    private Integer termFromDays;
    private Integer termToDays;
    private BigDecimal interestRate;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Integer getTermFromDays() {
        return termFromDays;
    }

    public void setTermFromDays(Integer termFromDays) {
        this.termFromDays = termFromDays;
    }

    public Integer getTermToDays() {
        return termToDays;
    }

    public void setTermToDays(Integer termToDays) {
        this.termToDays = termToDays;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
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
