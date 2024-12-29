package com.jijonj.microservices.order.controller;

import com.jijonj.microservices.order.dto.OrderStatusHistoryRequest;
import com.jijonj.microservices.order.dto.OrderStatusHistoryResponse;
import com.jijonj.microservices.order.service.OrderStatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order/order-status-history")
@RequiredArgsConstructor
public class OrderStatusHistoryController {

    private final OrderStatusHistoryService orderStatusHistoryService;

    @GetMapping
    public List<OrderStatusHistoryResponse> getAllOrderStatusHistories() {
        return orderStatusHistoryService.getAllOrderStatusHistories();
    }

    @GetMapping("/{historyId}")
    public Optional<OrderStatusHistoryResponse> getOrderStatusHistoryById(@PathVariable Long historyId) {
        return orderStatusHistoryService.getOrderStatusHistoryById(historyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderStatusHistoryResponse createOrderStatusHistory(@RequestBody OrderStatusHistoryRequest orderStatusHistoryRequest) {
        return orderStatusHistoryService.createOrderStatusHistory(orderStatusHistoryRequest);
    }

    @PutMapping("/{historyId}")
    public Optional<OrderStatusHistoryResponse> updateOrderStatusHistory(@PathVariable Long historyId, @RequestBody OrderStatusHistoryRequest orderStatusHistoryRequest) {
        return orderStatusHistoryService.updateOrderStatusHistory(historyId, orderStatusHistoryRequest);
    }

    @PatchMapping("/{historyId}")
    public Optional<OrderStatusHistoryResponse> partialUpdateOrderStatusHistory(@PathVariable Long historyId, @RequestBody OrderStatusHistoryRequest orderStatusHistoryRequest) {
        return orderStatusHistoryService.partialUpdateOrderStatusHistory(historyId, orderStatusHistoryRequest);
    }

    @DeleteMapping("/{historyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderStatusHistory(@PathVariable Long historyId) {
        orderStatusHistoryService.deleteOrderStatusHistory(historyId);
    }

    @PostMapping("/bulk")
    public List<OrderStatusHistoryResponse> bulkCreateOrUpdateOrderStatusHistories(@RequestBody List<OrderStatusHistoryRequest> orderStatusHistoryRequests) {
        return orderStatusHistoryService.bulkCreateOrUpdateOrderStatusHistories(orderStatusHistoryRequests);
    }

    @GetMapping("/search")
    public List<OrderStatusHistoryResponse> searchOrderStatusHistories(@RequestParam String query) {
        return orderStatusHistoryService.searchOrderStatusHistories(query);
    }
}