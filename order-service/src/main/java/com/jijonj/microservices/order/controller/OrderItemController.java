package com.jijonj.microservices.order.controller;

import com.jijonj.microservices.order.dto.OrderItemRequest;
import com.jijonj.microservices.order.dto.OrderItemResponse;
import com.jijonj.microservices.order.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemResponse> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{itemId}")
    public Optional<OrderItemResponse> getOrderItemById(@PathVariable Long itemId) {
        return orderItemService.getOrderItemById(itemId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderItemResponse createOrderItem(@RequestBody OrderItemRequest orderItemRequest) {
        return orderItemService.createOrderItem(orderItemRequest);
    }

    @PutMapping("/{itemId}")
    public Optional<OrderItemResponse> updateOrderItem(@PathVariable Long itemId, @RequestBody OrderItemRequest orderItemRequest) {
        return orderItemService.updateOrderItem(itemId, orderItemRequest);
    }

    @PatchMapping("/{itemId}")
    public Optional<OrderItemResponse> partialUpdateOrderItem(@PathVariable Long itemId, @RequestBody OrderItemRequest orderItemRequest) {
        return orderItemService.partialUpdateOrderItem(itemId, orderItemRequest);
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderItem(@PathVariable Long itemId) {
        orderItemService.deleteOrderItem(itemId);
    }

    @PostMapping("/bulk")
    public List<OrderItemResponse> bulkCreateOrUpdateOrderItems(@RequestBody List<OrderItemRequest> orderItemRequests) {
        return orderItemService.bulkCreateOrUpdateOrderItems(orderItemRequests);
    }

    @GetMapping("/search")
    public List<OrderItemResponse> searchOrderItems(@RequestParam String query) {
        return orderItemService.searchOrderItems(query);
    }

    @PutMapping("/replace")
    public List<OrderItemResponse> replaceOrderItems(@RequestParam Long orderId, @RequestBody List<OrderItemRequest> orderItemRequests) {
        return orderItemService.replaceOrderItems(orderId, orderItemRequests);
    }
}