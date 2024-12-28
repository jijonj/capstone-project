package com.jijonj.microservices.inventory.service;

import com.jijonj.microservices.inventory.model.Bin;
import com.jijonj.microservices.inventory.repository.BinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BinService {
    private final BinRepository binRepository;

    public List<Bin> getAllBins(Long warehouseId) {
        if (warehouseId != null) {
            return binRepository.findByWarehouseId(warehouseId);
        } else {
            return binRepository.findAll();
        }
    }

    public Bin getBinById(Long id) {
        Optional<Bin> bin = binRepository.findById(id);
        return bin.orElse(null);
    }

    public Bin createBin(Bin bin) {
        return binRepository.save(bin);
    }

    public Bin updateBin(Long id, Bin bin) {
        Optional<Bin> existingBin = binRepository.findById(id);
        if (existingBin.isPresent()) {
            Bin updatedBin = existingBin.get();
            updatedBin.setWarehouseId(bin.getWarehouseId());
            updatedBin.setBinName(bin.getBinName());
            updatedBin.setBinType(bin.getBinType());
            updatedBin.setCreatedAt(bin.getCreatedAt());
            updatedBin.setUpdatedAt(bin.getUpdatedAt());
            return binRepository.save(updatedBin);
        } else {
            return null;
        }
    }

    public void deleteBin(Long id) {
        binRepository.deleteById(id);
    }
}