package com.jijonj.microservices.inventory.service;

import com.jijonj.microservices.inventory.model.AdjustmentReason;
import com.jijonj.microservices.inventory.repository.AdjustmentReasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdjustmentReasonService {
    private final AdjustmentReasonRepository adjustmentReasonRepository;

    public List<AdjustmentReason> getAllAdjustmentReasons() {
        return adjustmentReasonRepository.findAll();
    }

    public AdjustmentReason getAdjustmentReasonById(Long id) {
        Optional<AdjustmentReason> adjustmentReason = adjustmentReasonRepository.findById(id);
        return adjustmentReason.orElse(null);
    }

    public AdjustmentReason createAdjustmentReason(AdjustmentReason adjustmentReason) {
        return adjustmentReasonRepository.save(adjustmentReason);
    }

    public AdjustmentReason updateAdjustmentReason(Long id, AdjustmentReason adjustmentReason) {
        Optional<AdjustmentReason> existingAdjustmentReason = adjustmentReasonRepository.findById(id);
        if (existingAdjustmentReason.isPresent()) {
            AdjustmentReason updatedAdjustmentReason = existingAdjustmentReason.get();
            updatedAdjustmentReason.setReasonCode(adjustmentReason.getReasonCode());
            updatedAdjustmentReason.setDescription(adjustmentReason.getDescription());
            updatedAdjustmentReason.setCreatedAt(adjustmentReason.getCreatedAt());
            updatedAdjustmentReason.setUpdatedAt(adjustmentReason.getUpdatedAt());
            return adjustmentReasonRepository.save(updatedAdjustmentReason);
        } else {
            return null;
        }
    }

    public void deleteAdjustmentReason(Long id) {
        adjustmentReasonRepository.deleteById(id);
    }
}