package com.jijonj.microservices.order.repository;

import com.jijonj.microservices.order.model.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
    List<Refund> findByOrderId(Long orderId);
}