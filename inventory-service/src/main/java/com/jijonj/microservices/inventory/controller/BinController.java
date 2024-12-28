package com.jijonj.microservices.inventory.controller;

import com.jijonj.microservices.inventory.model.Bin;
import com.jijonj.microservices.inventory.service.BinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/bins")
@RequiredArgsConstructor
public class BinController {
    private final BinService binService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Bin> getAllBins(@RequestParam(required = false) Long warehouseId) {
        return binService.getAllBins(warehouseId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Bin getBinById(@PathVariable Long id) {
        return binService.getBinById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bin createBin(@RequestBody Bin bin) {
        return binService.createBin(bin);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Bin updateBin(@PathVariable Long id, @RequestBody Bin bin) {
        return binService.updateBin(id, bin);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBin(@PathVariable Long id) {
        binService.deleteBin(id);
    }
}