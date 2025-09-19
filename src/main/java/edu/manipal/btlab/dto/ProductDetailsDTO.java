package edu.manipal.btlab.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ProductDetailsDTO {
    private Long id;
    private String productCode;
    private String productName;
    private String description;
    private String status;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<ProductTermProfileDTO> termProfiles;
    private List<RateMatrixDTO> rateMatrices;
    private List<BusinessRuleDTO> businessRules;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ProductTermProfileDTO> getTermProfiles() {
        return termProfiles;
    }

    public void setTermProfiles(List<ProductTermProfileDTO> termProfiles) {
        this.termProfiles = termProfiles;
    }

    public List<RateMatrixDTO> getRateMatrices() {
        return rateMatrices;
    }

    public void setRateMatrices(List<RateMatrixDTO> rateMatrices) {
        this.rateMatrices = rateMatrices;
    }

    public List<BusinessRuleDTO> getBusinessRules() {
        return businessRules;
    }

    public void setBusinessRules(List<BusinessRuleDTO> businessRules) {
        this.businessRules = businessRules;
    }
}
