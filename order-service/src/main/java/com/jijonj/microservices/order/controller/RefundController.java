package com.jijonj.microservices.order.controller;

import com.jijonj.microservices.order.dto.RefundRequest;
import com.jijonj.microservices.order.dto.RefundResponse;
import com.jijonj.microservices.order.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order/refunds")
@RequiredArgsConstructor
public class RefundController {

    private final RefundService refundService;

    @GetMapping
    public List<RefundResponse> getAllRefunds() {
        return refundService.getAllRefunds();
    }

    @GetMapping("/{refundId}")
    public Optional<RefundResponse> getRefundById(@PathVariable Long refundId) {
        return refundService.getRefundById(refundId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RefundResponse createRefund(@RequestBody RefundRequest refundRequest) {
        return refundService.createRefund(refundRequest);
    }

    @PutMapping("/{refundId}")
    public Optional<RefundResponse> updateRefund(@PathVariable Long refundId, @RequestBody RefundRequest refundRequest) {
        return refundService.updateRefund(refundId, refundRequest);
    }

    @PatchMapping("/{refundId}")
    public Optional<RefundResponse> partialUpdateRefund(@PathVariable Long refundId, @RequestBody RefundRequest refundRequest) {
        return refundService.partialUpdateRefund(refundId, refundRequest);
    }

    @DeleteMapping("/{refundId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRefund(@PathVariable Long refundId) {
        refundService.deleteRefund(refundId);
    }

    @PostMapping("/bulk")
    public List<RefundResponse> bulkCreateOrUpdateRefunds(@RequestBody List<RefundRequest> refundRequests) {
        return refundService.bulkCreateOrUpdateRefunds(refundRequests);
    }

    @GetMapping("/search")
    public List<RefundResponse> searchRefunds(@RequestParam String query) {
        return refundService.searchRefunds(query);
    }

    @PutMapping("/{refundId}/cancel")
    public Optional<RefundResponse> cancelRefund(@PathVariable Long refundId) {
        return refundService.cancelRefund(refundId);
    }
}