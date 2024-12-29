package com.jijonj.microservices.order.service;

import com.jijonj.microservices.order.dto.OrderDiscountRequest;
import com.jijonj.microservices.order.dto.OrderDiscountResponse;
import com.jijonj.microservices.order.model.OrderDiscount;
import com.jijonj.microservices.order.repository.OrderDiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDiscountService {

    private final OrderDiscountRepository orderDiscountRepository;

    public List<OrderDiscountResponse> getAllOrderDiscounts() {
        return orderDiscountRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<OrderDiscountResponse> getOrderDiscountsByOrderId(Long orderId) {
        return orderDiscountRepository.findByOrderId(orderId).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<OrderDiscountResponse> getOrderDiscountsByDiscountId(Long discountId) {
        return orderDiscountRepository.findByDiscountId(discountId).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public Optional<OrderDiscountResponse> getOrderDiscountById(Long mappingId) {
        return orderDiscountRepository.findById(mappingId).map(this::mapToResponse);
    }

    public OrderDiscountResponse createOrderDiscount(OrderDiscountRequest orderDiscountRequest) {
        OrderDiscount orderDiscount = mapToEntity(orderDiscountRequest);
        return mapToResponse(orderDiscountRepository.save(orderDiscount));
    }

    public Optional<OrderDiscountResponse> updateOrderDiscount(Long mappingId, OrderDiscountRequest orderDiscountRequest) {
        return orderDiscountRepository.findById(mappingId).map(orderDiscount -> {
            orderDiscount.setOrderId(orderDiscountRequest.getOrderId());
            orderDiscount.setDiscountId(orderDiscountRequest.getDiscountId());
            orderDiscount.setDiscountAmount(orderDiscountRequest.getDiscountAmount());
            orderDiscount.setAppliedAt(orderDiscountRequest.getAppliedAt());
            return mapToResponse(orderDiscountRepository.save(orderDiscount));
        });
    }

    public Optional<OrderDiscountResponse> partialUpdateOrderDiscount(Long mappingId, OrderDiscountRequest orderDiscountRequest) {
        return orderDiscountRepository.findById(mappingId).map(orderDiscount -> {
            if (orderDiscountRequest.getOrderId() != null) {
                orderDiscount.setOrderId(orderDiscountRequest.getOrderId());
            }
            if (orderDiscountRequest.getDiscountId() != null) {
                orderDiscount.setDiscountId(orderDiscountRequest.getDiscountId());
            }
            if (orderDiscountRequest.getDiscountAmount() != null) {
                orderDiscount.setDiscountAmount(orderDiscountRequest.getDiscountAmount());
            }
            if (orderDiscountRequest.getAppliedAt() != null) {
                orderDiscount.setAppliedAt(orderDiscountRequest.getAppliedAt());
            }
            return mapToResponse(orderDiscountRepository.save(orderDiscount));
        });
    }

    public void deleteOrderDiscount(Long mappingId) {
        orderDiscountRepository.deleteById(mappingId);
    }

    public List<OrderDiscountResponse> bulkCreateOrUpdateOrderDiscounts(List<OrderDiscountRequest> orderDiscountRequests) {
        List<OrderDiscount> orderDiscounts = orderDiscountRequests.stream().map(this::mapToEntity).collect(Collectors.toList());
        return orderDiscountRepository.saveAll(orderDiscounts).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<OrderDiscountResponse> searchOrderDiscounts(String query) {
        return orderDiscountRepository.findAll().stream()
                .filter(orderDiscount -> orderDiscount.getDiscountId().toString().contains(query) ||
                        orderDiscount.getOrderId().toString().contains(query))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private OrderDiscount mapToEntity(OrderDiscountRequest orderDiscountRequest) {
        OrderDiscount orderDiscount = new OrderDiscount();
        orderDiscount.setOrderId(orderDiscountRequest.getOrderId());
        orderDiscount.setDiscountId(orderDiscountRequest.getDiscountId());
        orderDiscount.setDiscountAmount(orderDiscountRequest.getDiscountAmount());
        orderDiscount.setAppliedAt(orderDiscountRequest.getAppliedAt());
        return orderDiscount;
    }

    private OrderDiscountResponse mapToResponse(OrderDiscount orderDiscount) {
        return new OrderDiscountResponse(orderDiscount.getId(), orderDiscount.getOrderId(), orderDiscount.getDiscountId(), orderDiscount.getDiscountAmount(), orderDiscount.getAppliedAt());
    }
}