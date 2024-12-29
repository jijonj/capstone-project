package com.jijonj.microservices.order.controller;

import com.jijonj.microservices.order.dto.ShippingDetailsRequest;
import com.jijonj.microservices.order.dto.ShippingDetailsResponse;
import com.jijonj.microservices.order.service.ShippingDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order/shipping-details")
@RequiredArgsConstructor
public class ShippingDetailsController {

    private final ShippingDetailsService shippingDetailsService;

    @GetMapping
    public List<ShippingDetailsResponse> getAllShippingDetails() {
        return shippingDetailsService.getAllShippingDetails();
    }

    @GetMapping("/{shippingId}")
    public Optional<ShippingDetailsResponse> getShippingDetailsById(@PathVariable Long shippingId) {
        return shippingDetailsService.getShippingDetailsById(shippingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShippingDetailsResponse createShippingDetails(@RequestBody ShippingDetailsRequest shippingDetailsRequest) {
        return shippingDetailsService.createShippingDetails(shippingDetailsRequest);
    }

    @PutMapping("/{shippingId}")
    public Optional<ShippingDetailsResponse> updateShippingDetails(@PathVariable Long shippingId, @RequestBody ShippingDetailsRequest shippingDetailsRequest) {
        return shippingDetailsService.updateShippingDetails(shippingId, shippingDetailsRequest);
    }

    @PatchMapping("/{shippingId}")
    public Optional<ShippingDetailsResponse> partialUpdateShippingDetails(@PathVariable Long shippingId, @RequestBody ShippingDetailsRequest shippingDetailsRequest) {
        return shippingDetailsService.partialUpdateShippingDetails(shippingId, shippingDetailsRequest);
    }

    @DeleteMapping("/{shippingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShippingDetails(@PathVariable Long shippingId) {
        shippingDetailsService.deleteShippingDetails(shippingId);
    }

    @PostMapping("/bulk")
    public List<ShippingDetailsResponse> bulkCreateOrUpdateShippingDetails(@RequestBody List<ShippingDetailsRequest> shippingDetailsRequests) {
        return shippingDetailsService.bulkCreateOrUpdateShippingDetails(shippingDetailsRequests);
    }

    @GetMapping("/search")
    public List<ShippingDetailsResponse> searchShippingDetails(@RequestParam String query) {
        return shippingDetailsService.searchShippingDetails(query);
    }

    @PutMapping("/replace")
    public ShippingDetailsResponse replaceShippingDetails(@RequestParam Long orderId, @RequestBody ShippingDetailsRequest shippingDetailsRequest) {
        return shippingDetailsService.replaceShippingDetails(orderId, shippingDetailsRequest);
    }
}