package com.jijonj.microservices.inventory.repository;

import com.jijonj.microservices.inventory.model.AdjustmentReason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdjustmentReasonRepository extends JpaRepository<AdjustmentReason, Long> {
}