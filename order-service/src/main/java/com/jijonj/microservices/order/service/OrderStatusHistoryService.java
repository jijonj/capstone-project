package com.jijonj.microservices.order.service;

import com.jijonj.microservices.order.dto.OrderStatusHistoryRequest;
import com.jijonj.microservices.order.dto.OrderStatusHistoryResponse;
import com.jijonj.microservices.order.model.OrderStatusHistory;
import com.jijonj.microservices.order.repository.OrderStatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderStatusHistoryService {

    private final OrderStatusHistoryRepository orderStatusHistoryRepository;

    public List<OrderStatusHistoryResponse> getAllOrderStatusHistories() {
        return orderStatusHistoryRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<OrderStatusHistoryResponse> getOrderStatusHistoriesByOrderId(Long orderId) {
        return orderStatusHistoryRepository.findByOrderId(orderId).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public Optional<OrderStatusHistoryResponse> getOrderStatusHistoryById(Long historyId) {
        return orderStatusHistoryRepository.findById(historyId).map(this::mapToResponse);
    }

    public OrderStatusHistoryResponse createOrderStatusHistory(OrderStatusHistoryRequest orderStatusHistoryRequest) {
        OrderStatusHistory orderStatusHistory = mapToEntity(orderStatusHistoryRequest);
        return mapToResponse(orderStatusHistoryRepository.save(orderStatusHistory));
    }

    public Optional<OrderStatusHistoryResponse> updateOrderStatusHistory(Long historyId, OrderStatusHistoryRequest orderStatusHistoryRequest) {
        return orderStatusHistoryRepository.findById(historyId).map(orderStatusHistory -> {
            orderStatusHistory.setOldStatus(orderStatusHistoryRequest.getOldStatus());
            orderStatusHistory.setNewStatus(orderStatusHistoryRequest.getNewStatus());
            orderStatusHistory.setChangedAt(orderStatusHistoryRequest.getChangedAt());
            orderStatusHistory.setChangedBy(orderStatusHistoryRequest.getChangedBy());
            return mapToResponse(orderStatusHistoryRepository.save(orderStatusHistory));
        });
    }

    public Optional<OrderStatusHistoryResponse> partialUpdateOrderStatusHistory(Long historyId, OrderStatusHistoryRequest orderStatusHistoryRequest) {
        return orderStatusHistoryRepository.findById(historyId).map(orderStatusHistory -> {
            if (orderStatusHistoryRequest.getOldStatus() != null) {
                orderStatusHistory.setOldStatus(orderStatusHistoryRequest.getOldStatus());
            }
            if (orderStatusHistoryRequest.getNewStatus() != null) {
                orderStatusHistory.setNewStatus(orderStatusHistoryRequest.getNewStatus());
            }
            if (orderStatusHistoryRequest.getChangedAt() != null) {
                orderStatusHistory.setChangedAt(orderStatusHistoryRequest.getChangedAt());
            }
            if (orderStatusHistoryRequest.getChangedBy() != null) {
                orderStatusHistory.setChangedBy(orderStatusHistoryRequest.getChangedBy());
            }
            return mapToResponse(orderStatusHistoryRepository.save(orderStatusHistory));
        });
    }

    public void deleteOrderStatusHistory(Long historyId) {
        orderStatusHistoryRepository.deleteById(historyId);
    }

    public List<OrderStatusHistoryResponse> bulkCreateOrUpdateOrderStatusHistories(List<OrderStatusHistoryRequest> orderStatusHistoryRequests) {
        List<OrderStatusHistory> orderStatusHistories = orderStatusHistoryRequests.stream().map(this::mapToEntity).collect(Collectors.toList());
        return orderStatusHistoryRepository.saveAll(orderStatusHistories).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<OrderStatusHistoryResponse> searchOrderStatusHistories(String query) {
        // Implement search logic here
        return orderStatusHistoryRepository.findAll().stream()
                .filter(orderStatusHistory -> orderStatusHistory.getNewStatus().contains(query) ||
                        orderStatusHistory.getOldStatus().contains(query))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private OrderStatusHistory mapToEntity(OrderStatusHistoryRequest orderStatusHistoryRequest) {
        OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
        orderStatusHistory.setOrderId(orderStatusHistoryRequest.getOrderId());
        orderStatusHistory.setOldStatus(orderStatusHistoryRequest.getOldStatus());
        orderStatusHistory.setNewStatus(orderStatusHistoryRequest.getNewStatus());
        orderStatusHistory.setChangedAt(orderStatusHistoryRequest.getChangedAt());
        orderStatusHistory.setChangedBy(orderStatusHistoryRequest.getChangedBy());
        return orderStatusHistory;
    }

    private OrderStatusHistoryResponse mapToResponse(OrderStatusHistory orderStatusHistory) {
        return new OrderStatusHistoryResponse(orderStatusHistory.getId(), orderStatusHistory.getOrderId(), orderStatusHistory.getOldStatus(), orderStatusHistory.getNewStatus(), orderStatusHistory.getChangedAt(), orderStatusHistory.getChangedBy());
    }
}