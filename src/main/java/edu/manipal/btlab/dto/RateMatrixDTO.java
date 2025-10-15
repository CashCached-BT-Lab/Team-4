package edu.manipal.btlab.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.manipal.btlab.entity.RateMatrix;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RateMatrixDTO {
    private Long id;
    private RateMatrix.CustomerType customerType;
    private Integer termFromDays;
    private Integer termToDays;
    private BigDecimal interestRate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private LocalDateTime createdAt;

    public RateMatrixDTO() {
    }

    public RateMatrixDTO(Long id, RateMatrix.CustomerType customerType, Integer termFromDays, Integer termToDays, BigDecimal interestRate, LocalDate effectiveFrom, LocalDate effectiveTo, LocalDateTime createdAt) {
        this.id = id;
        this.customerType = customerType;
        this.termFromDays = termFromDays;
        this.termToDays = termToDays;
        this.interestRate = interestRate;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
