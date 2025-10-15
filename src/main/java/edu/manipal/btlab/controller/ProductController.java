package edu.manipal.btlab.controller;

import edu.manipal.btlab.dto.*;
import edu.manipal.btlab.entity.BusinessRule;
import edu.manipal.btlab.entity.BusinessRuleType;
import edu.manipal.btlab.entity.Product;
import edu.manipal.btlab.entity.Product.*;
import edu.manipal.btlab.repository.BusinessRuleTypeRepository;
import edu.manipal.btlab.service.ServiceLyr;
import edu.manipal.btlab.service.impl.ServiceLyrImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ServiceLyrImpl Service;

    // 1. Get all products for dropdown
    @GetMapping
    public List<ProductSummaryDTO> getAllProducts() {
        return Service.getAllProducts();
    }

    // 2. Get detailed product info
    @GetMapping("/{id}")
    public ProductDetailsDTO getProductDetails(@PathVariable Long id) {
        return Service.getProductDetails(id);
    }

    // ---------------- Product Management ---------------- //

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDTO request) {
        // Convert DTO to Entity
        Product product = new Product();
        product.setProductCode(request.getProductCode());
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setCurrency(request.getCurrency());
        product.setStatus(Status.DRAFT); // default status when creating
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        String createdBy = "admin@example.com"; // temporary for audit logging

        Product createdProduct = Service.createProduct(product, createdBy);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO request) {
        Product updated = new Product();
        updated.setProductCode(request.getProductCode());
        updated.setProductName(request.getProductName());
        updated.setDescription(request.getDescription());
        updated.setCurrency(request.getCurrency());
        updated.setUpdatedAt(LocalDateTime.now());

        String updatedBy = "admin@example.com";

        Product result = Service.updateProduct(id, updated, updatedBy);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        String deletedBy = "admin@example.com";

        Service.deleteProduct(productId, deletedBy);
    }

    @PatchMapping("/{productId}/status")
    public ResponseEntity<Product> changeStatus(@PathVariable Long productId, @RequestParam Status status) {
        String updatedBy = "admin@example.com";

        Product result = Service.changeStatus(productId, status, updatedBy);
        return ResponseEntity.ok(result);
    }

    // ---------------- Term Profile Management ---------------- //

    @PostMapping("/{productId}/term-profiles")
    public ProductTermProfileDTO addTermProfile(@PathVariable Long productId, @RequestBody TermProfileRequestDTO request) {
        return Service.addTermProfile(productId, request);
    }

    @GetMapping("/{productId}/term-profiles")
    public List<ProductTermProfileDTO> getTermProfiles(@PathVariable Long productId) {
        return Service.getTermProfiles(productId);
    }

    @PutMapping("/{productId}/term-profiles")
    public ProductTermProfileDTO updateTermProfile(@PathVariable Long profileId, @RequestBody TermProfileRequestDTO request) {
        // Service method must fetch the profile by ID, update its properties from the request DTO, and save.
        return Service.updateTermProfile(profileId, request);
    }

    @DeleteMapping("/{productId}/term-profiles")
    public ResponseEntity<Void> deleteTermProfile(@PathVariable Long profileId) {
        Service.deleteTermProfile(profileId);
        return ResponseEntity.noContent().build();
    }

    // ---------------- Rate Matrix Management ---------------- //

    @PostMapping("/{productId}/rates")
    public RateMatrixDTO addRate(@PathVariable Long productId, @RequestBody RateRequestDTO request) {
        return Service.addRate(productId, request);
    }

    @PutMapping("/rates/{rateId}")
    public RateMatrixDTO updateRate(@PathVariable Long rateId, @RequestBody RateRequestDTO request) {
        return Service.updateRate(rateId, request);
    }

    @DeleteMapping("/rates/{rateId}")
    public ResponseEntity<Void> deleteRate(@PathVariable Long rateId) {
        Service.deleteRate(rateId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productId}/rates")
    public List<RateMatrixDTO> getRates(@PathVariable Long productId, @RequestParam(required = false) String customerType) {
        return Service.getRates(productId, customerType);
    }

    // ---------------- Business Rule Management ---------------- //

    @PostMapping("/{productId}/rules")
    public BusinessRuleDTO addRule(@PathVariable Long productId, @RequestBody BusinessRuleRequestDTO request) {
        return Service.addRule(productId, request);
    }

    @GetMapping("/{productId}/rules")
    public List<BusinessRuleDTO> getRules(@PathVariable Long productId) {
        return Service.getRules(productId);
    }

    @PutMapping("/rules/{ruleId}")
    public BusinessRuleDTO updateRule(@PathVariable Long ruleId, @RequestBody BusinessRuleRequestDTO request) {
        // Service method must fetch the rule by ID, update its properties from the request DTO, and save.
        return Service.updateRule(ruleId, request);
    }

    @DeleteMapping("/rules/{ruleId}")
    public ResponseEntity<Void> deleteRule(@PathVariable Long ruleId) {
        Service.deleteRule(ruleId);
        return ResponseEntity.noContent().build();
    }

    @Autowired
    private BusinessRuleTypeRepository ruleTypeRepository;

    @GetMapping("/rule-types")
    public List<BusinessRuleType> getAllRuleTypes() {
        // Return all rule types for the dropdown
        return ruleTypeRepository.findAll();
    }

}

