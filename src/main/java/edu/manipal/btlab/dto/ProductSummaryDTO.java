package edu.manipal.btlab.dto;

import edu.manipal.btlab.entity.Product;

import java.math.BigInteger;

public class ProductSummaryDTO {
    private Long id;
    private String productCode;
    private String productName;
    private Product.Status status;

    public Product.Status getStatus() {
        return status;
    }

    public void setStatus(Product.Status status) {
        this.status = status;
    }

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
}
