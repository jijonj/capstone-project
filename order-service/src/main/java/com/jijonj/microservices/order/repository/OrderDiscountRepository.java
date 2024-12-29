package com.jijonj.microservices.order.repository;

import com.jijonj.microservices.order.model.OrderDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDiscountRepository extends JpaRepository<OrderDiscount, Long> {
    List<OrderDiscount> findByOrderId(Long orderId);
    List<OrderDiscount> findByDiscountId(Long discountId);
}
