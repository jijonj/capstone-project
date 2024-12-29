package com.jijonj.microservices.order.repository;

import com.jijonj.microservices.order.model.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {
    List<DiscountCode> findByCodeContaining(String code);
}