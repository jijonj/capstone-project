package com.jijonj.microservices.order.service;

import com.jijonj.microservices.order.dto.RefundRequest;
import com.jijonj.microservices.order.dto.RefundResponse;
import com.jijonj.microservices.order.model.Refund;
import com.jijonj.microservices.order.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefundService {

    private final RefundRepository refundRepository;

    public List<RefundResponse> getAllRefunds() {
        return refundRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<RefundResponse> getRefundsByOrderId(Long orderId) {
        return refundRepository.findByOrderId(orderId).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public Optional<RefundResponse> getRefundById(Long refundId) {
        return refundRepository.findById(refundId).map(this::mapToResponse);
    }

    public RefundResponse createRefund(RefundRequest refundRequest) {
        Refund refund = mapToEntity(refundRequest);
        return mapToResponse(refundRepository.save(refund));
    }

    public Optional<RefundResponse> updateRefund(Long refundId, RefundRequest refundRequest) {
        return refundRepository.findById(refundId).map(refund -> {
            refund.setOrderId(refundRequest.getOrderId());
            refund.setRefundAmount(refundRequest.getRefundAmount());
            refund.setRefundReason(refundRequest.getRefundReason());
            refund.setProcessedBy(refundRequest.getProcessedBy());
            refund.setProcessedAt(refundRequest.getProcessedAt());
            return mapToResponse(refundRepository.save(refund));
        });
    }

    public Optional<RefundResponse> partialUpdateRefund(Long refundId, RefundRequest refundRequest) {
        return refundRepository.findById(refundId).map(refund -> {
            if (refundRequest.getOrderId() != null) {
                refund.setOrderId(refundRequest.getOrderId());
            }
            if (refundRequest.getRefundAmount() != null) {
                refund.setRefundAmount(refundRequest.getRefundAmount());
            }
            if (refundRequest.getRefundReason() != null) {
                refund.setRefundReason(refundRequest.getRefundReason());
            }
            if (refundRequest.getProcessedBy() != null) {
                refund.setProcessedBy(refundRequest.getProcessedBy());
            }
            if (refundRequest.getProcessedAt() != null) {
                refund.setProcessedAt(refundRequest.getProcessedAt());
            }
            return mapToResponse(refundRepository.save(refund));
        });
    }

    public void deleteRefund(Long refundId) {
        refundRepository.deleteById(refundId);
    }

    public List<RefundResponse> bulkCreateOrUpdateRefunds(List<RefundRequest> refundRequests) {
        List<Refund> refunds = refundRequests.stream().map(this::mapToEntity).collect(Collectors.toList());
        return refundRepository.saveAll(refunds).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<RefundResponse> searchRefunds(String query) {
        // Implement search logic here
        return refundRepository.findAll().stream()
                .filter(refund -> refund.getRefundReason().contains(query) ||
                        refund.getOrderId().toString().contains(query))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<RefundResponse> cancelRefund(Long refundId) {
        return refundRepository.findById(refundId).map(refund -> {
            refund.setRefundReason("CANCELLED");
            return mapToResponse(refundRepository.save(refund));
        });
    }

    private Refund mapToEntity(RefundRequest refundRequest) {
        Refund refund = new Refund();
        refund.setOrderId(refundRequest.getOrderId());
        refund.setRefundAmount(refundRequest.getRefundAmount());
        refund.setRefundReason(refundRequest.getRefundReason());
        refund.setProcessedBy(refundRequest.getProcessedBy());
        refund.setProcessedAt(refundRequest.getProcessedAt());
        return refund;
    }

    private RefundResponse mapToResponse(Refund refund) {
        return new RefundResponse(refund.getId(), refund.getOrderId(), refund.getRefundAmount(), refund.getRefundReason(), refund.getProcessedBy(), refund.getProcessedAt());
    }
}