package org.jpc.jspring.controllers;

import java.util.Map;

import org.jpc.jspring.core.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ConLibrariesDetails {
    
    @GetMapping("/payment-gateways")
    public ResponseEntity<Response> getPaymentGateways() {
        return ResponseEntity.ok().body(new Response(false, "Gateway Fetched", Map.of("Flutterwave", "v1.0.3")));
    }
}
