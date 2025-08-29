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
@RequestMapping("/api/v1/admin/products")
public class AdminProductController {

    @PostMapping
    public ResponseEntity<?> createProduct() {return null;}

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId) {return null;}

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {return null;}

    @GetMapping
    public ResponseEntity<?> listAllProducts() {return null;}

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductDetails(@PathVariable Long productId) {return null;}

    @PutMapping("/{productId}/status")
    public ResponseEntity<?> changeProductStatus(@PathVariable Long productId) {return null;}

    @GetMapping("/status/{status}")
    public ResponseEntity<?> filterProductsByStatus(@PathVariable String status) {return null;}

    // Charges
    @PostMapping("/{productId}/charges")
    public ResponseEntity<?> addCharges(@PathVariable Long productId) {return null;}

    @PutMapping("/{productId}/charges/{chargeId}")
    public ResponseEntity<?> updateCharges(@PathVariable Long productId, @PathVariable Long chargeId) {return null;}

    @DeleteMapping("/{productId}/charges/{chargeId}")
    public ResponseEntity<?> removeCharges(@PathVariable Long productId, @PathVariable Long chargeId) {return null;}

    @GetMapping("/{productId}/charges")
    public ResponseEntity<?> getAllCharges(@PathVariable Long productId) {return null;}

    // Rates
    @PostMapping("/{productId}/rates")
    public ResponseEntity<?> configureInterestRates(@PathVariable Long productId) {return null;}

    @PutMapping("/{productId}/rates/{rateId}")
    public ResponseEntity<?> updateRates(@PathVariable Long productId, @PathVariable Long rateId) {return null;}

    @GetMapping("/{productId}/rates")
    public ResponseEntity<?> getRateStructure(@PathVariable Long productId) {return null;}
}
