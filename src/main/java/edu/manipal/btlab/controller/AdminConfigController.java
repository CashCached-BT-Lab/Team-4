package edu.manipal.btlab.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Optionally import validation and security if needed
// import org.springframework.security.access.prepost.PreAuthorize;
// import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/admin")
public class AdminConfigController {

    @PostMapping("/roles")
    public ResponseEntity<?> createOrManageRoles() {return null;}

    @PutMapping("/roles/{roleId}")
    public ResponseEntity<?> updateRolePermissions(@PathVariable Long roleId) {return null;}

    @GetMapping("/roles")
    public ResponseEntity<?> listAllRoles() {return null;}

    @PostMapping("/transaction-types")
    public ResponseEntity<?> configureTransactionTypes() {return null;}

    @PostMapping("/balance-types")
    public ResponseEntity<?> configureBalanceTypes() {return null;}
}
