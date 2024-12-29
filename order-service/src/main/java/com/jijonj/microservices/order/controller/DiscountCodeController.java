package com.jijonj.microservices.order.controller;

import com.jijonj.microservices.order.dto.DiscountCodeRequest;
import com.jijonj.microservices.order.dto.DiscountCodeResponse;
import com.jijonj.microservices.order.service.DiscountCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order/discount-codes")
@RequiredArgsConstructor
public class DiscountCodeController {

    private final DiscountCodeService discountCodeService;

    @GetMapping
    public List<DiscountCodeResponse> getAllDiscountCodes() {
        return discountCodeService.getAllDiscountCodes();
    }

    @GetMapping("/search")
    public List<DiscountCodeResponse> searchDiscountCodes(@RequestParam String query) {
        return discountCodeService.searchDiscountCodes(query);
    }

    @GetMapping("/{discountId}")
    public Optional<DiscountCodeResponse> getDiscountCodeById(@PathVariable Long discountId) {
        return discountCodeService.getDiscountCodeById(discountId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiscountCodeResponse createDiscountCode(@RequestBody DiscountCodeRequest discountCodeRequest) {
        return discountCodeService.createDiscountCode(discountCodeRequest);
    }

    @PutMapping("/{discountId}")
    public Optional<DiscountCodeResponse> updateDiscountCode(@PathVariable Long discountId, @RequestBody DiscountCodeRequest discountCodeRequest) {
        return discountCodeService.updateDiscountCode(discountId, discountCodeRequest);
    }

    @PatchMapping("/{discountId}")
    public Optional<DiscountCodeResponse> partialUpdateDiscountCode(@PathVariable Long discountId, @RequestBody DiscountCodeRequest discountCodeRequest) {
        return discountCodeService.partialUpdateDiscountCode(discountId, discountCodeRequest);
    }

    @DeleteMapping("/{discountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDiscountCode(@PathVariable Long discountId) {
        discountCodeService.deleteDiscountCode(discountId);
    }

    @PostMapping("/bulk")
    public List<DiscountCodeResponse> bulkCreateOrUpdateDiscountCodes(@RequestBody List<DiscountCodeRequest> discountCodeRequests) {
        return discountCodeService.bulkCreateOrUpdateDiscountCodes(discountCodeRequests);
    }

    @PutMapping("/{discountId}/activate")
    public Optional<DiscountCodeResponse> activateDiscountCode(@PathVariable Long discountId, @RequestParam Boolean isActive) {
        return discountCodeService.activateDiscountCode(discountId, isActive);
    }
}