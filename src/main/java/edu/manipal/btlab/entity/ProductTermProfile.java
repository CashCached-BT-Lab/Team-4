package edu.manipal.btlab.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_term_profile")
public class ProductTermProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer minTermDays;
    private Integer maxTermDays;

    @Column(precision = 18, scale = 2)
    private Double minAmount;

    @Column(precision = 18, scale = 2)
    private Double maxAmount;

    @Enumerated(EnumType.STRING)
    private CompoundingFrequency compoundingFrequency;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public enum CompoundingFrequency {
        DAILY, MONTHLY, QUARTERLY, YEARLY
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

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public CompoundingFrequency getCompoundingFrequency() {
        return compoundingFrequency;
    }

    public void setCompoundingFrequency(CompoundingFrequency compoundingFrequency) {
        this.compoundingFrequency = compoundingFrequency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
