package com.jijonj.microservices.order.service;

import com.jijonj.microservices.order.dto.PaymentRequest;
import com.jijonj.microservices.order.dto.PaymentResponse;
import com.jijonj.microservices.order.model.Payment;
import com.jijonj.microservices.order.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<PaymentResponse> getPaymentsByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public Optional<PaymentResponse> getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).map(this::mapToResponse);
    }

    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        Payment payment = mapToEntity(paymentRequest);
        return mapToResponse(paymentRepository.save(payment));
    }

    public Optional<PaymentResponse> updatePayment(Long paymentId, PaymentRequest paymentRequest) {
        return paymentRepository.findById(paymentId).map(payment -> {
            payment.setPaymentMethod(paymentRequest.getPaymentMethod());
            payment.setPaymentStatus(paymentRequest.getPaymentStatus());
            payment.setAmountPaid(paymentRequest.getAmountPaid());
            payment.setTransactionId(paymentRequest.getTransactionId());
            return mapToResponse(paymentRepository.save(payment));
        });
    }

    public Optional<PaymentResponse> partialUpdatePayment(Long paymentId, PaymentRequest paymentRequest) {
        return paymentRepository.findById(paymentId).map(payment -> {
            if (paymentRequest.getPaymentMethod() != null) {
                payment.setPaymentMethod(paymentRequest.getPaymentMethod());
            }
            if (paymentRequest.getPaymentStatus() != null) {
                payment.setPaymentStatus(paymentRequest.getPaymentStatus());
            }
            if (paymentRequest.getAmountPaid() != null) {
                payment.setAmountPaid(paymentRequest.getAmountPaid());
            }
            if (paymentRequest.getTransactionId() != null) {
                payment.setTransactionId(paymentRequest.getTransactionId());
            }
            return mapToResponse(paymentRepository.save(payment));
        });
    }

    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    public List<PaymentResponse> bulkCreateOrUpdatePayments(List<PaymentRequest> paymentRequests) {
        List<Payment> payments = paymentRequests.stream().map(this::mapToEntity).collect(Collectors.toList());
        return paymentRepository.saveAll(payments).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<PaymentResponse> searchPayments(String query) {
        // Implement search logic here
        return paymentRepository.findAll().stream()
                .filter(payment -> payment.getPaymentMethod().contains(query) ||
                        payment.getPaymentStatus().contains(query) ||
                        payment.getTransactionId().contains(query))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<PaymentResponse> refundPayment(Long paymentId) {
        return paymentRepository.findById(paymentId).map(payment -> {
            payment.setPaymentStatus("REFUNDED");
            return mapToResponse(paymentRepository.save(payment));
        });
    }

    private Payment mapToEntity(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setOrderId(paymentRequest.getOrderId());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setPaymentStatus(paymentRequest.getPaymentStatus());
        payment.setAmountPaid(paymentRequest.getAmountPaid());
        payment.setTransactionId(paymentRequest.getTransactionId());
        return payment;
    }

    private PaymentResponse mapToResponse(Payment payment) {
        return new PaymentResponse(payment.getId(), payment.getOrderId(), payment.getPaymentMethod(), payment.getPaymentStatus(), payment.getAmountPaid(), payment.getTransactionId());
    }
}