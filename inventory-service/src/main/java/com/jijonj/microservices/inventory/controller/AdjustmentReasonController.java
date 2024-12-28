package com.jijonj.microservices.inventory.controller;

import com.jijonj.microservices.inventory.model.AdjustmentReason;
import com.jijonj.microservices.inventory.service.AdjustmentReasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/adjustment-reasons")
@RequiredArgsConstructor
public class AdjustmentReasonController {
    private final AdjustmentReasonService adjustmentReasonService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AdjustmentReason> getAllAdjustmentReasons() {
        return adjustmentReasonService.getAllAdjustmentReasons();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdjustmentReason getAdjustmentReasonById(@PathVariable Long id) {
        return adjustmentReasonService.getAdjustmentReasonById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdjustmentReason createAdjustmentReason(@RequestBody AdjustmentReason adjustmentReason) {
        return adjustmentReasonService.createAdjustmentReason(adjustmentReason);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdjustmentReason updateAdjustmentReason(@PathVariable Long id, @RequestBody AdjustmentReason adjustmentReason) {
        return adjustmentReasonService.updateAdjustmentReason(id, adjustmentReason);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdjustmentReason(@PathVariable Long id) {
        adjustmentReasonService.deleteAdjustmentReason(id);
    }
}