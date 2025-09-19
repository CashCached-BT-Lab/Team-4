package edu.manipal.btlab.service.impl;

import edu.manipal.btlab.dto.*;

import java.util.List;
import java.util.stream.Collectors;

import edu.manipal.btlab.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLyrImpl {

    @Autowired private ProductRepository productRepository;

    // 1. Get all products (for dropdown)
    public List<ProductSummaryDTO> getAllProducts() {
        return productRepository.findAll().stream().map(p -> {
            ProductSummaryDTO dto = new ProductSummaryDTO();
            dto.setId(p.getId());
            dto.setProductCode(p.getProductCode());
            dto.setProductName(p.getProductName());
            return dto;
        }).collect(Collectors.toList());
    }

    // 2. Get full product info by ID
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
                tpdto.setCompoundingFrequency(tp.getCompoundingFrequency().name());
                tpdto.setCreatedAt(tp.getCreatedAt());
                return tpdto;
            }).collect(Collectors.toList()));

            // Map Rate Matrices
            dto.setRateMatrices(p.getRateMatrices().stream().map(rm -> {
                RateMatrixDTO rmdto = new RateMatrixDTO();
                rmdto.setId(rm.getId());
                rmdto.setCustomerType(rm.getCustomerType().name());
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
                brdto.setRuleTypeId(br.getRuleType().getId());
                brdto.setRuleValue(br.getRuleValue());
                brdto.setEffectiveFrom(br.getEffectiveFrom());
                brdto.setEffectiveTo(br.getEffectiveTo());
                brdto.setCreatedAt(br.getCreatedAt());
                return brdto;
            }).collect(Collectors.toList()));

            return dto;
        }).orElse(null);
    }
}
