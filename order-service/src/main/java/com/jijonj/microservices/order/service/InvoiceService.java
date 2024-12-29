package com.jijonj.microservices.order.service;

import com.jijonj.microservices.order.dto.InvoiceRequest;
import com.jijonj.microservices.order.dto.InvoiceResponse;
import com.jijonj.microservices.order.model.Invoice;
import com.jijonj.microservices.order.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public List<InvoiceResponse> getAllInvoices() {
        return invoiceRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<InvoiceResponse> getInvoicesByOrderId(Long orderId) {
        return invoiceRepository.findByOrderId(orderId).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public Optional<InvoiceResponse> getInvoiceById(Long invoiceId) {
        return invoiceRepository.findById(invoiceId).map(this::mapToResponse);
    }

    public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) {
        Invoice invoice = mapToEntity(invoiceRequest);
        return mapToResponse(invoiceRepository.save(invoice));
    }

    public Optional<InvoiceResponse> updateInvoice(Long invoiceId, InvoiceRequest invoiceRequest) {
        return invoiceRepository.findById(invoiceId).map(invoice -> {
            invoice.setOrderId(invoiceRequest.getOrderId());
            invoice.setInvoiceNumber(invoiceRequest.getInvoiceNumber());
            invoice.setInvoiceDate(invoiceRequest.getInvoiceDate());
            invoice.setDueDate(invoiceRequest.getDueDate());
            invoice.setTotalAmount(invoiceRequest.getTotalAmount());
            invoice.setStatus(invoiceRequest.getStatus());
            return mapToResponse(invoiceRepository.save(invoice));
        });
    }

    public Optional<InvoiceResponse> partialUpdateInvoice(Long invoiceId, InvoiceRequest invoiceRequest) {
        return invoiceRepository.findById(invoiceId).map(invoice -> {
            if (invoiceRequest.getOrderId() != null) {
                invoice.setOrderId(invoiceRequest.getOrderId());
            }
            if (invoiceRequest.getInvoiceNumber() != null) {
                invoice.setInvoiceNumber(invoiceRequest.getInvoiceNumber());
            }
            if (invoiceRequest.getInvoiceDate() != null) {
                invoice.setInvoiceDate(invoiceRequest.getInvoiceDate());
            }
            if (invoiceRequest.getDueDate() != null) {
                invoice.setDueDate(invoiceRequest.getDueDate());
            }
            if (invoiceRequest.getTotalAmount() != null) {
                invoice.setTotalAmount(invoiceRequest.getTotalAmount());
            }
            if (invoiceRequest.getStatus() != null) {
                invoice.setStatus(invoiceRequest.getStatus());
            }
            return mapToResponse(invoiceRepository.save(invoice));
        });
    }

    public void deleteInvoice(Long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }

    public List<InvoiceResponse> bulkCreateOrUpdateInvoices(List<InvoiceRequest> invoiceRequests) {
        List<Invoice> invoices = invoiceRequests.stream().map(this::mapToEntity).collect(Collectors.toList());
        return invoiceRepository.saveAll(invoices).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<InvoiceResponse> searchInvoices(String query) {
        // Implement search logic here
        return invoiceRepository.findAll().stream()
                .filter(invoice -> invoice.getInvoiceNumber().contains(query) ||
                        invoice.getStatus().contains(query))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<InvoiceResponse> voidInvoice(Long invoiceId) {
        return invoiceRepository.findById(invoiceId).map(invoice -> {
            invoice.setStatus("VOID");
            return mapToResponse(invoiceRepository.save(invoice));
        });
    }

    private Invoice mapToEntity(InvoiceRequest invoiceRequest) {
        Invoice invoice = new Invoice();
        invoice.setOrderId(invoiceRequest.getOrderId());
        invoice.setInvoiceNumber(invoiceRequest.getInvoiceNumber());
        invoice.setInvoiceDate(invoiceRequest.getInvoiceDate());
        invoice.setDueDate(invoiceRequest.getDueDate());
        invoice.setTotalAmount(invoiceRequest.getTotalAmount());
        invoice.setStatus(invoiceRequest.getStatus());
        return invoice;
    }

    private InvoiceResponse mapToResponse(Invoice invoice) {
        return new InvoiceResponse(invoice.getId(), invoice.getOrderId(), invoice.getInvoiceNumber(), invoice.getInvoiceDate(), invoice.getDueDate(), invoice.getTotalAmount(), invoice.getStatus());
    }
}