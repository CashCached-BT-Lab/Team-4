package edu.manipal.btlab.dto;

import edu.manipal.btlab.entity.ProductTermProfile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductTermProfileDTO {
    private Long id;
    private Integer minTermDays;
    private Integer maxTermDays;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private ProductTermProfile.CompoundingFrequency compoundingFrequency;
    private LocalDateTime createdAt;

    public ProductTermProfileDTO() {
    }

    public ProductTermProfileDTO(Long id, Integer minTermDays, Integer maxTermDays, BigDecimal minAmount, BigDecimal maxAmount, ProductTermProfile.CompoundingFrequency compoundingFrequency, LocalDateTime createdAt) {
        this.id = id;
        this.minTermDays = minTermDays;
        this.maxTermDays = maxTermDays;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.compoundingFrequency = compoundingFrequency;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
