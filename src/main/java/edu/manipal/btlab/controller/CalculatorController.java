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

// Optionally import validation and security if needed
// import org.springframework.security.access.prepost.PreAuthorize;
// import javax.validation.Valid;
@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    @PostMapping("/simulate")
    public ResponseEntity<?> calculateFdReturns() {return null;}

    @PostMapping("/compare")
    public ResponseEntity<?> compareMultipleProducts() {return null;}

    @GetMapping("/rates/{productId}")
    public ResponseEntity<?> getCurrentInterestRates(@PathVariable Long productId) {return null;}
}
{
}
