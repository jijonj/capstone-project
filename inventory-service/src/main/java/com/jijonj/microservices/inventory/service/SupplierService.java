package com.jijonj.microservices.inventory.service;

import com.jijonj.microservices.inventory.model.Supplier;
import com.jijonj.microservices.inventory.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.orElse(null);
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(Long id, Supplier supplier) {
        Optional<Supplier> existingSupplier = supplierRepository.findById(id);
        if (existingSupplier.isPresent()) {
            Supplier updatedSupplier = existingSupplier.get();
            updatedSupplier.setSupplierName(supplier.getSupplierName());
            updatedSupplier.setContactPerson(supplier.getContactPerson());
            updatedSupplier.setPhone(supplier.getPhone());
            updatedSupplier.setEmail(supplier.getEmail());
            updatedSupplier.setAddress(supplier.getAddress());
            updatedSupplier.setCreatedAt(supplier.getCreatedAt());
            updatedSupplier.setUpdatedAt(supplier.getUpdatedAt());
            return supplierRepository.save(updatedSupplier);
        } else {
            return null;
        }
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}