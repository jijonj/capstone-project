package com.jijonj.microservices.order.repository;

import com.jijonj.microservices.order.model.ShippingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingDetailsRepository extends JpaRepository<ShippingDetails, Long> {
    List<ShippingDetails> findByOrderId(Long orderId);
}