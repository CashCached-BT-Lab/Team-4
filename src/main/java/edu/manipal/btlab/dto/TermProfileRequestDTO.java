package edu.manipal.btlab.dto;

import edu.manipal.btlab.entity.ProductTermProfile;

import java.math.BigDecimal;

public class TermProfileRequestDTO {
    private Integer minTermDays;
    private Integer maxTermDays;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private ProductTermProfile.CompoundingFrequency compoundingFrequency;

    // Getters and Setters
    public Integer getMinTermDays() {
        return minTermDays;
    }

    public void setMinTermDays(Integer minTermDays) {
        this.minTermDays = minTermDays;
    }

    public Integer getMaxTermDays() {
        return maxTermDays;
    }

    public void setMaxTermDays(Integer maxTermDays) {
        this.maxTermDays = maxTermDays;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public ProductTermProfile.CompoundingFrequency getCompoundingFrequency() {
        return compoundingFrequency;
    }

    public void setCompoundingFrequency(ProductTermProfile.CompoundingFrequency compoundingFrequency) {
        this.compoundingFrequency = compoundingFrequency;
    }
}
