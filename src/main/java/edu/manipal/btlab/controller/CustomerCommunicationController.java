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
@RequestMapping("/api/v1/customer/communications")
public class CustomerCommunicationController {

    @GetMapping
    public ResponseEntity<?> getCustomerNotifications() {return null;}

    @PostMapping("/preferences")
    public ResponseEntity<?> setCommunicationPreferences() {return null;}
}

