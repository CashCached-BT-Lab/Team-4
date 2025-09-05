package edu.manipal.btlab.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/products")
public class CustomerProductController {

    @GetMapping
    public ResponseEntity<?> listActiveProducts() {return null;}

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductDetails(@PathVariable Long productId) {return null;}

    @GetMapping("/search")
    public ResponseEntity<?> searchProducts() {return null;}

    @GetMapping("/categories")
    public ResponseEntity<?> getProductCategories() {return null;}
}
