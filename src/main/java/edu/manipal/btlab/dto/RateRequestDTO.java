package edu.manipal.btlab.dto;

import edu.manipal.btlab.entity.RateMatrix;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RateRequestDTO {
    private RateMatrix.CustomerType customerType;
    private Integer termFromDays;
    private Integer termToDays;
    private BigDecimal interestRate;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;

    public RateMatrix.CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(RateMatrix.CustomerType customerType) {
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
}
