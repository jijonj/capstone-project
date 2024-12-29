package com.jijonj.microservices.order.service;

import com.jijonj.microservices.order.dto.CustomerRequest;
import com.jijonj.microservices.order.dto.CustomerResponse;
import com.jijonj.microservices.order.model.Customer;
import com.jijonj.microservices.order.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable).map(this::mapToResponse);
    }

    public Optional<CustomerResponse> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).map(this::mapToResponse);
    }

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = mapToEntity(customerRequest);
        return mapToResponse(customerRepository.save(customer));
    }

    public Optional<CustomerResponse> updateCustomer(Long customerId, CustomerRequest customerRequest) {
        return customerRepository.findById(customerId).map(customer -> {
            customer.setFirstName(customerRequest.getFirstName());
            customer.setLastName(customerRequest.getLastName());
            customer.setEmail(customerRequest.getEmail());
            customer.setPhone(customerRequest.getPhone());
            return mapToResponse(customerRepository.save(customer));
        });
    }

    public Optional<CustomerResponse> partialUpdateCustomer(Long customerId, CustomerRequest customerRequest) {
        return customerRepository.findById(customerId).map(customer -> {
            if (customerRequest.getFirstName() != null) {
                customer.setFirstName(customerRequest.getFirstName());
            }
            if (customerRequest.getLastName() != null) {
                customer.setLastName(customerRequest.getLastName());
            }
            if (customerRequest.getEmail() != null) {
                customer.setEmail(customerRequest.getEmail());
            }
            if (customerRequest.getPhone() != null) {
                customer.setPhone(customerRequest.getPhone());
            }
            return mapToResponse(customerRepository.save(customer));
        });
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public List<CustomerResponse> searchCustomers(String query) {
        // Implement search logic here
        return customerRepository.findAll().stream()
                .filter(customer -> customer.getFirstName().contains(query) ||
                        customer.getLastName().contains(query) ||
                        customer.getEmail().contains(query) ||
                        customer.getPhone().contains(query))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<CustomerResponse> bulkCreateOrUpdateCustomers(List<CustomerRequest> customerRequests) {
        List<Customer> customers = customerRequests.stream().map(this::mapToEntity).collect(Collectors.toList());
        return customerRepository.saveAll(customers).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private Customer mapToEntity(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhone(customerRequest.getPhone());
        return customer;
    }

    private CustomerResponse mapToResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhone());
    }
}