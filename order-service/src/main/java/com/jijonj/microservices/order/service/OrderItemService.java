package com.jijonj.microservices.order.service;

import com.jijonj.microservices.order.dto.OrderItemRequest;
import com.jijonj.microservices.order.dto.OrderItemResponse;
import com.jijonj.microservices.order.model.OrderItem;
import com.jijonj.microservices.order.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public List<OrderItemResponse> getAllOrderItems() {
        return orderItemRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<OrderItemResponse> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public Optional<OrderItemResponse> getOrderItemById(Long itemId) {
        return orderItemRepository.findById(itemId).map(this::mapToResponse);
    }

    public OrderItemResponse createOrderItem(OrderItemRequest orderItemRequest) {
        OrderItem orderItem = mapToEntity(orderItemRequest);
        orderItem.setTotalPrice(orderItem.getUnitPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
        return mapToResponse(orderItemRepository.save(orderItem));
    }

    public Optional<OrderItemResponse> updateOrderItem(Long itemId, OrderItemRequest orderItemRequest) {
        return orderItemRepository.findById(itemId).map(orderItem -> {
            orderItem.setProductId(orderItemRequest.getProductId());
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setUnitPrice(orderItemRequest.getUnitPrice());
            orderItem.setTotalPrice(orderItem.getUnitPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
            return mapToResponse(orderItemRepository.save(orderItem));
        });
    }

    public Optional<OrderItemResponse> partialUpdateOrderItem(Long itemId, OrderItemRequest orderItemRequest) {
        return orderItemRepository.findById(itemId).map(orderItem -> {
            if (orderItemRequest.getProductId() != null) {
                orderItem.setProductId(orderItemRequest.getProductId());
            }
            if (orderItemRequest.getQuantity() != null) {
                orderItem.setQuantity(orderItemRequest.getQuantity());
            }
            if (orderItemRequest.getUnitPrice() != null) {
                orderItem.setUnitPrice(orderItemRequest.getUnitPrice());
            }
            orderItem.setTotalPrice(orderItem.getUnitPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
            return mapToResponse(orderItemRepository.save(orderItem));
        });
    }

    public void deleteOrderItem(Long itemId) {
        orderItemRepository.deleteById(itemId);
    }

    public List<OrderItemResponse> bulkCreateOrUpdateOrderItems(List<OrderItemRequest> orderItemRequests) {
        List<OrderItem> orderItems = orderItemRequests.stream().map(this::mapToEntity).collect(Collectors.toList());
        orderItems.forEach(orderItem -> orderItem.setTotalPrice(orderItem.getUnitPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))));
        return orderItemRepository.saveAll(orderItems).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<OrderItemResponse> searchOrderItems(String query) {
        return orderItemRepository.findAll().stream()
                .filter(orderItem -> orderItem.getProductId().toString().contains(query))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<OrderItemResponse> replaceOrderItems(Long orderId, List<OrderItemRequest> orderItemRequests) {
        List<OrderItem> existingItems = orderItemRepository.findByOrderId(orderId);
        orderItemRepository.deleteAll(existingItems);
        return bulkCreateOrUpdateOrderItems(orderItemRequests);
    }

    private OrderItem mapToEntity(OrderItemRequest orderItemRequest) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderItemRequest.getOrderId());
        orderItem.setProductId(orderItemRequest.getProductId());
        orderItem.setQuantity(orderItemRequest.getQuantity());
        orderItem.setUnitPrice(orderItemRequest.getUnitPrice());
        return orderItem;
    }

    private OrderItemResponse mapToResponse(OrderItem orderItem) {
        return new OrderItemResponse(orderItem.getId(), orderItem.getOrderId(), orderItem.getProductId(), orderItem.getQuantity(), orderItem.getUnitPrice(), orderItem.getTotalPrice());
    }
}