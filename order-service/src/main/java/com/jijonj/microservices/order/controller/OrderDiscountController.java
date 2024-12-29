package com.jijonj.microservices.order.controller;

import com.jijonj.microservices.order.dto.OrderDiscountRequest;
import com.jijonj.microservices.order.dto.OrderDiscountResponse;
import com.jijonj.microservices.order.service.OrderDiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order/order-discounts")
@RequiredArgsConstructor
public class OrderDiscountController {

    private final OrderDiscountService orderDiscountService;

    @GetMapping
    public List<OrderDiscountResponse> getAllOrderDiscounts() {
        return orderDiscountService.getAllOrderDiscounts();
    }

    @GetMapping("/{mappingId}")
    public Optional<OrderDiscountResponse> getOrderDiscountById(@PathVariable Long mappingId) {
        return orderDiscountService.getOrderDiscountById(mappingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDiscountResponse createOrderDiscount(@RequestBody OrderDiscountRequest orderDiscountRequest) {
        return orderDiscountService.createOrderDiscount(orderDiscountRequest);
    }

    @PutMapping("/{mappingId}")
    public Optional<OrderDiscountResponse> updateOrderDiscount(@PathVariable Long mappingId, @RequestBody OrderDiscountRequest orderDiscountRequest) {
        return orderDiscountService.updateOrderDiscount(mappingId, orderDiscountRequest);
    }

    @PatchMapping("/{mappingId}")
    public Optional<OrderDiscountResponse> partialUpdateOrderDiscount(@PathVariable Long mappingId, @RequestBody OrderDiscountRequest orderDiscountRequest) {
        return orderDiscountService.partialUpdateOrderDiscount(mappingId, orderDiscountRequest);
    }

    @DeleteMapping("/{mappingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderDiscount(@PathVariable Long mappingId) {
        orderDiscountService.deleteOrderDiscount(mappingId);
    }

    @PostMapping("/bulk")
    public List<OrderDiscountResponse> bulkCreateOrUpdateOrderDiscounts(@RequestBody List<OrderDiscountRequest> orderDiscountRequests) {
        return orderDiscountService.bulkCreateOrUpdateOrderDiscounts(orderDiscountRequests);
    }

    @GetMapping("/search")
    public List<OrderDiscountResponse> searchOrderDiscounts(@RequestParam String query) {
        return orderDiscountService.searchOrderDiscounts(query);
    }
}