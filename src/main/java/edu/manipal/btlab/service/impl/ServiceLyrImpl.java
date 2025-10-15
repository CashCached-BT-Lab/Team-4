package edu.manipal.btlab.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.manipal.btlab.dto.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.manipal.btlab.entity.*;
import edu.manipal.btlab.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServiceLyrImpl {

    @Autowired private ProductRepository productRepository;
    @Autowired private ProductAuditLogRepository auditLogRepository;
    @Autowired private ProductTermProfileRepository termProfileRepository;
    @Autowired private RateMatrixRepository rateMatrixRepository;
    @Autowired private BusinessRuleRepository ruleRepository;
    @Autowired private BusinessRuleTypeRepository ruleTypeRepository;

    // 1. List all available products
    public List<ProductSummaryDTO> getAllProducts() {
        return productRepository.findAll().stream().map(p -> {
            ProductSummaryDTO dto = new ProductSummaryDTO();
            dto.setId(p.getId());
            dto.setProductCode(p.getProductCode());
            dto.setProductName(p.getProductName());
            dto.setStatus(p.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    // 2. Get detailed product info by ID
    public ProductDetailsDTO getProductDetails(Long id) {
        return productRepository.findById(id).map(p -> {
            ProductDetailsDTO dto = new ProductDetailsDTO();
            dto.setId(p.getId());
            dto.setProductCode(p.getProductCode());
            dto.setProductName(p.getProductName());
            dto.setDescription(p.getDescription());
            dto.setStatus(p.getStatus().name());
            dto.setCurrency(p.getCurrency());
            dto.setCreatedAt(p.getCreatedAt());
            dto.setUpdatedAt(p.getUpdatedAt());

            // Map Term Profiles
            dto.setTermProfiles(p.getTermProfiles().stream().map(tp -> {
                ProductTermProfileDTO tpdto = new ProductTermProfileDTO();
                tpdto.setId(tp.getId());
                tpdto.setMinTermDays(tp.getMinTermDays());
                tpdto.setMaxTermDays(tp.getMaxTermDays());
                tpdto.setMinAmount(tp.getMinAmount());
                tpdto.setMaxAmount(tp.getMaxAmount());
                tpdto.setCompoundingFrequency(tp.getCompoundingFrequency());
                tpdto.setCreatedAt(tp.getCreatedAt());
                return tpdto;
            }).collect(Collectors.toList()));

            // Map Rate Matrices
            dto.setRateMatrices(p.getRateMatrices().stream().map(rm -> {
                RateMatrixDTO rmdto = new RateMatrixDTO();
                rmdto.setId(rm.getId());
                rmdto.setCustomerType(rm.getCustomerType());
                rmdto.setTermFromDays(rm.getTermFromDays());
                rmdto.setTermToDays(rm.getTermToDays());
                rmdto.setInterestRate(rm.getInterestRate());
                rmdto.setEffectiveFrom(rm.getEffectiveFrom());
                rmdto.setEffectiveTo(rm.getEffectiveTo());
                rmdto.setCreatedAt(rm.getCreatedAt());
                return rmdto;
            }).collect(Collectors.toList()));

            // Map Business Rules
            dto.setBusinessRules(p.getBusinessRules().stream().map(br -> {
                BusinessRuleDTO brdto = new BusinessRuleDTO();
                brdto.setId(br.getId());
                brdto.setRuleTypeId(br.getRuleType());
                brdto.setRuleValue(br.getRuleValue());
                brdto.setEffectiveFrom(br.getEffectiveFrom());
                brdto.setEffectiveTo(br.getEffectiveTo());
                brdto.setCreatedAt(br.getCreatedAt());
                return brdto;
            }).collect(Collectors.toList()));

            return dto;
        }).orElse(null);
    }

    // 3. CREATE PRODUCT
    public Product createProduct(Product product, String createdBy) {
        // Validate unique code

        if (productRepository.existsByProductCode(product.getProductCode())) {
            throw new IllegalArgumentException("Product code must be unique!");
        }

        product.setStatus(Product.Status.DRAFT);
        Product saved = productRepository.save(product);
        logAction(saved.getId(), ProductAuditLog.Action.CREATE, saved, createdBy);
        return saved;
    }

    // 4. UPDATE PRODUCT
    public Product updateProduct(Long productId, Product updatedProduct, String updatedBy) {
        Product existing = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        existing.setProductCode(updatedProduct.getProductCode());
        existing.setProductName(updatedProduct.getProductName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setCurrency(updatedProduct.getCurrency());
        existing.setUpdatedAt(updatedProduct.getUpdatedAt());

        Product saved = productRepository.save(existing);
        logAction(saved.getId(), ProductAuditLog.Action.UPDATE, saved, updatedBy);
        return saved;
    }

    // 5. DELETE PRODUCT (SOFT DELETE)
    @Transactional
    public void deleteProduct(Long productId, String deletedBy) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        logAction(productId, ProductAuditLog.Action.DELETE, product, deletedBy);
        productRepository.delete(product);
    }

    // 6. CHANGE STATUS
    public Product changeStatus(Long productId, Product.Status status, String updatedBy) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (status.equals(Product.Status.ACTIVE)) {
            // Check business rules before activation
            boolean hasRules = ruleRepository.existsByProductId(productId);
            boolean hasRates = rateMatrixRepository.existsByProductId(productId);
            if (!hasRules || !hasRates) {
                throw new IllegalStateException("Cannot activate product without rules and rates.");
            }
        }

        product.setStatus(status);
        Product saved = productRepository.save(product);
        logAction(productId, ProductAuditLog.Action.CHANGE_STATUS, saved, updatedBy);
        return product;
    }

    // 7. AUDIT LOGGING

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    private void logAction(Long productId, ProductAuditLog.Action action, Product product, String createdBy) {
        try {
            // Build JSON details
            Map<String, Object> detailsMap = new HashMap<>();
            detailsMap.put("action", action.name());
            detailsMap.put("productId", productId);
            if (product != null) {
                detailsMap.put("productCode", product.getProductCode());
                detailsMap.put("productName", product.getProductName());
            }
            detailsMap.put("performedBy", createdBy);
            detailsMap.put("timestamp", LocalDateTime.now());

            // Convert map to JSON string
            String jsonDetails = objectMapper.writeValueAsString(detailsMap);

            // Save audit log
            ProductAuditLog log = new ProductAuditLog();
            Product prod = new Product();
            prod.setId(productId);
            log.setProduct(prod);
            log.setAction(action);
            log.setDetails(jsonDetails);  // now valid JSON
            log.setCreatedBy(createdBy);
            log.setCreatedAt(LocalDateTime.now());

            auditLogRepository.save(log);
        } catch (Exception e) {
            throw new RuntimeException("Failed to log product action", e);
        }
    }


    // ---------------- Term Profile Management ---------------- //

    private ProductTermProfileDTO mapToTermProfileDTO(ProductTermProfile termProfile) {
        return new ProductTermProfileDTO(
                termProfile.getId(),
                termProfile.getMinTermDays(),
                termProfile.getMaxTermDays(),
                termProfile.getMinAmount(),
                termProfile.getMaxAmount(),
                termProfile.getCompoundingFrequency(),
                LocalDateTime.now()
        );
    }

    public ProductTermProfileDTO addTermProfile(Long productId, TermProfileRequestDTO request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        ProductTermProfile termProfile = new ProductTermProfile();
        termProfile.setProduct(product);
        termProfile.setMinTermDays(request.getMinTermDays());
        termProfile.setMaxTermDays(request.getMaxTermDays());
        termProfile.setMinAmount(request.getMinAmount());
        termProfile.setMaxAmount(request.getMaxAmount());
        termProfile.setCompoundingFrequency(request.getCompoundingFrequency());
        termProfile.setCreatedAt(LocalDateTime.now());

        termProfile = termProfileRepository.save(termProfile);

        return mapToTermProfileDTO(termProfile);
    }

    public List<ProductTermProfileDTO> getTermProfiles(Long productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        List<ProductTermProfile> profiles = termProfileRepository.findByProductId(productId);

        return profiles.stream()
                .map(this::mapToTermProfileDTO)
                .toList();
    }

    public ProductTermProfileDTO updateTermProfile(Long profileId, TermProfileRequestDTO request) {
        ProductTermProfile existingProfile = termProfileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Term Profile not found with ID: " + profileId));

        existingProfile.setMinTermDays(request.getMinTermDays());
        existingProfile.setMaxTermDays(request.getMaxTermDays());
        existingProfile.setMinAmount(request.getMinAmount());
        existingProfile.setMaxAmount(request.getMaxAmount());
        existingProfile.setCompoundingFrequency(request.getCompoundingFrequency());
        existingProfile.setCreatedAt(LocalDateTime.now());

        ProductTermProfile updatedProfile = termProfileRepository.save(existingProfile);

        return mapToTermProfileDTO(updatedProfile);
    }

    public void deleteTermProfile(Long profileId) {
        ProductTermProfile profile = termProfileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Term Profile not found with ID: " + profileId));

        logAction(profile.getProduct().getId(), ProductAuditLog.Action.DELETE, profile.getProduct(), "admin@example.com");

        termProfileRepository.delete(profile);
    }

    // ---------------- Rate Matrix Management ---------------- //

    private RateMatrixDTO mapToRateMatrixDTO(RateMatrix rate) {
        return new RateMatrixDTO(
                rate.getId(),
                rate.getCustomerType(),
                rate.getTermFromDays(),
                rate.getTermToDays(),
                rate.getInterestRate(),
                rate.getEffectiveFrom(),
                rate.getEffectiveTo(),
                rate.getCreatedAt()
        );
    }

    public RateMatrixDTO addRate(Long productId, RateRequestDTO request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        RateMatrix rate = new RateMatrix();
        rate.setProduct(product);
        rate.setCustomerType(request.getCustomerType());
        rate.setTermFromDays(request.getTermFromDays());
        rate.setTermToDays(request.getTermToDays());
        rate.setInterestRate(request.getInterestRate());
        rate.setEffectiveFrom(request.getEffectiveFrom());
        rate.setEffectiveTo(request.getEffectiveTo());
        rate.setCreatedAt(LocalDateTime.now());

        rate = rateMatrixRepository.save(rate);

        return mapToRateMatrixDTO(rate);
    }

    public RateMatrixDTO updateRate(Long rateId, RateRequestDTO request) {
        RateMatrix rate = rateMatrixRepository.findById(rateId)
                .orElseThrow(() -> new IllegalArgumentException("Rate not found with id: " + rateId));

        rate.setCustomerType(request.getCustomerType());
        rate.setEffectiveFrom(request.getEffectiveFrom());
        rate.setTermFromDays(request.getTermFromDays());
        rate.setTermToDays(request.getTermToDays());
        rate.setInterestRate(request.getInterestRate());
        rate.setCreatedAt(LocalDateTime.now());

        rate = rateMatrixRepository.save(rate);

        return mapToRateMatrixDTO(rate);
    }

    public List<RateMatrixDTO> getRates(Long productId, String customerType) {
        productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        List<RateMatrix> rates;
        if (customerType != null) {
            rates = rateMatrixRepository.findByProductIdAndCustomerType(productId, customerType);
        } else {
            rates = rateMatrixRepository.findByProductId(productId);
        }

        return rates.stream()
                .map(this::mapToRateMatrixDTO)
                .toList();
    }

    public void deleteRate(Long rateId) {
        RateMatrix rate = rateMatrixRepository.findById(rateId)
                .orElseThrow(() -> new IllegalArgumentException("Rate Matrix not found with ID: " + rateId));

        logAction(rate.getProduct().getId(), ProductAuditLog.Action.DELETE, rate.getProduct(), "admin@example.com");
        rateMatrixRepository.delete(rate);
    }

    // ---------------- Business Rule Management ---------------- //

    private BusinessRuleDTO mapToBusinessRuleDTO(BusinessRule rule) {
        return new BusinessRuleDTO(
                rule.getId(),
                rule.getRuleType(),
                rule.getRuleValue(),
                rule.getEffectiveFrom(),
                rule.getEffectiveTo(),
                rule.getCreatedAt()
        );
    }

    public BusinessRuleDTO addRule(Long productId, BusinessRuleRequestDTO request) {
        // 1. Fetch the Product entity (Parent)
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        // 2. ⭐️ CRITICAL FIX: Fetch the BusinessRuleType entity (Child Reference)
        // The DTO sends the ID, we must look up the entity to satisfy the ManyToOne relationship.
        BusinessRuleType ruleType = ruleTypeRepository.findById(request.getRuleTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Rule Type not found with id: " + request.getRuleTypeId()));

        BusinessRule rule = new BusinessRule();

        // Set relationships and fields
        rule.setProduct(product);
        rule.setRuleType(ruleType); // ✅ Correct: Setting the fetched entity

        rule.setRuleValue(request.getRuleValue());
        rule.setEffectiveFrom(request.getEffectiveFrom());
        rule.setEffectiveTo(request.getEffectiveTo());
        rule.setCreatedAt(LocalDateTime.now());

        // 3. Log action before saving
        logAction(productId, ProductAuditLog.Action.CREATE, product, "admin@example.com");

        rule = ruleRepository.save(rule);

        return mapToBusinessRuleDTO(rule);
    }

    public List<BusinessRuleDTO> getRules(Long productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        List<BusinessRule> rules = ruleRepository.findByProductId(productId);

        return rules.stream()
                .map(this::mapToBusinessRuleDTO)
                .toList();
    }

    public BusinessRuleDTO updateRule(Long ruleId, BusinessRuleRequestDTO request) {
        BusinessRule existingRule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new IllegalArgumentException("Business Rule not found with ID: " + ruleId));

        // Update fields from DTO
        existingRule.setRuleValue(request.getRuleValue());
        existingRule.setEffectiveFrom(request.getEffectiveFrom());
        existingRule.setEffectiveTo(request.getEffectiveTo());
        // Assuming ruleTypeId needs to be fetched and set
        BusinessRuleType type = ruleTypeRepository.findById(request.getRuleTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Rule Type not found with ID: " + request.getRuleTypeId()));
        existingRule.setRuleType(type);

        BusinessRule updatedRule = ruleRepository.save(existingRule);

        // Log the update action
        logAction(updatedRule.getProduct().getId(), ProductAuditLog.Action.UPDATE, updatedRule.getProduct(), "admin@example.com");

        // Convert and return the DTO
        return mapToBusinessRuleDTO(updatedRule);
    }

    public void deleteRule(Long ruleId) {
        BusinessRule rule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new IllegalArgumentException("Business Rule not found with ID: " + ruleId));

        logAction(rule.getProduct().getId(), ProductAuditLog.Action.DELETE, rule.getProduct(), "admin@example.com");
        ruleRepository.delete(rule);
    }
}

