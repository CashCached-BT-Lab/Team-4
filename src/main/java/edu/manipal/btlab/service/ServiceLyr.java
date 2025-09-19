package edu.manipal.btlab.service;

import edu.manipal.btlab.dto.ProductDetailsDTO;
import edu.manipal.btlab.dto.ProductSummaryDTO;

import java.util.List;

public interface ServiceLyr {
    public List<ProductSummaryDTO> getAllProducts();
    public ProductDetailsDTO getProductDetails(Long id);
}
