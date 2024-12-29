package com.jijonj.microservices.order.controller;

import com.jijonj.microservices.order.dto.CustomerRequest;
import com.jijonj.microservices.order.dto.CustomerResponse;
import com.jijonj.microservices.order.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        return customerService.getAllCustomers(pageable);
    }

    @GetMapping("/{customerId}")
    public Optional<CustomerResponse> getCustomerById(@PathVariable Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.createCustomer(customerRequest);
    }

    @PutMapping("/{customerId}")
    public Optional<CustomerResponse> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerRequest customerRequest) {
        return customerService.updateCustomer(customerId, customerRequest);
    }

    @PatchMapping("/{customerId}")
    public Optional<CustomerResponse> partialUpdateCustomer(@PathVariable Long customerId, @RequestBody CustomerRequest customerRequest) {
        return customerService.partialUpdateCustomer(customerId, customerRequest);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }

    @GetMapping("/search")
    public List<CustomerResponse> searchCustomers(@RequestParam String query) {
        return customerService.searchCustomers(query);
    }

    @PostMapping("/bulk")
    public List<CustomerResponse> bulkCreateOrUpdateCustomers(@RequestBody List<CustomerRequest> customerRequests) {
        return customerService.bulkCreateOrUpdateCustomers(customerRequests);
    }

}