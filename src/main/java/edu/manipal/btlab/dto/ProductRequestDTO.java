package edu.manipal.btlab.dto;

public class ProductRequestDTO {
    private String productCode;
    private String productName;
    private String description;
    private String currency;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String productCode, String productName, String description, String currency) {
        this.productCode = productCode;
        this.productName = productName;
        this.description = description;
        this.currency = currency;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

