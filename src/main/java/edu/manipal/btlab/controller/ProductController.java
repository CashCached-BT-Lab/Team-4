package edu.manipal.btlab.controller;

import edu.manipal.btlab.dto.ProductDetailsDTO;
import edu.manipal.btlab.dto.ProductSummaryDTO;
import edu.manipal.btlab.service.ServiceLyr;
import edu.manipal.btlab.service.impl.ServiceLyrImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ServiceLyrImpl productInfoService;

    // 1. Get all products for dropdown
    @GetMapping
    public List<ProductSummaryDTO> getAllProducts() {
        return productInfoService.getAllProducts();
    }

    // 2. Get detailed product info
    @GetMapping("/{id}")
    public ProductDetailsDTO getProductDetails(@PathVariable Long id) {
        return productInfoService.getProductDetails(id);
    }
}
