package edu.manipal.btlab.service;

import edu.manipal.btlab.dto.ProductDetailsDTO;
import edu.manipal.btlab.dto.ProductSummaryDTO;
import edu.manipal.btlab.entity.Product;

import java.util.List;

public interface ServiceLyr {
    public List<ProductSummaryDTO> getAllProducts();
    public ProductDetailsDTO getProductDetails(Long id);
    Product createProduct(Product product, String createdBy);
    Product updateProduct(Long productId, Product product, String updatedBy);
    void deleteProduct(Long productId, String deletedBy);
    Product changeStatus(Long productId, String status, String updatedBy);
    ProductDetailsDTO getProductById(Long productId);
    List<ProductSummaryDTO> listProducts(String status);
}
