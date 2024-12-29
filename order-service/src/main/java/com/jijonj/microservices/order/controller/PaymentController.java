package com.jijonj.microservices.order.controller;

import com.jijonj.microservices.order.dto.PaymentRequest;
import com.jijonj.microservices.order.dto.PaymentResponse;
import com.jijonj.microservices.order.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public List<PaymentResponse> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{paymentId}")
    public Optional<PaymentResponse> getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.createPayment(paymentRequest);
    }

    @PutMapping("/{paymentId}")
    public Optional<PaymentResponse> updatePayment(@PathVariable Long paymentId, @RequestBody PaymentRequest paymentRequest) {
        return paymentService.updatePayment(paymentId, paymentRequest);
    }

    @PatchMapping("/{paymentId}")
    public Optional<PaymentResponse> partialUpdatePayment(@PathVariable Long paymentId, @RequestBody PaymentRequest paymentRequest) {
        return paymentService.partialUpdatePayment(paymentId, paymentRequest);
    }

    @DeleteMapping("/{paymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePayment(@PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
    }

    @PostMapping("/bulk")
    public List<PaymentResponse> bulkCreateOrUpdatePayments(@RequestBody List<PaymentRequest> paymentRequests) {
        return paymentService.bulkCreateOrUpdatePayments(paymentRequests);
    }

    @GetMapping("/search")
    public List<PaymentResponse> searchPayments(@RequestParam String query) {
        return paymentService.searchPayments(query);
    }

    @PutMapping("/{paymentId}/refund")
    public Optional<PaymentResponse> refundPayment(@PathVariable Long paymentId) {
        return paymentService.refundPayment(paymentId);
    }
}