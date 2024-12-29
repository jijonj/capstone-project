package com.jijonj.microservices.order.controller;

import com.jijonj.microservices.order.dto.InvoiceRequest;
import com.jijonj.microservices.order.dto.InvoiceResponse;
import com.jijonj.microservices.order.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public List<InvoiceResponse> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{invoiceId}")
    public Optional<InvoiceResponse> getInvoiceById(@PathVariable Long invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceResponse createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        return invoiceService.createInvoice(invoiceRequest);
    }

    @PutMapping("/{invoiceId}")
    public Optional<InvoiceResponse> updateInvoice(@PathVariable Long invoiceId, @RequestBody InvoiceRequest invoiceRequest) {
        return invoiceService.updateInvoice(invoiceId, invoiceRequest);
    }

    @PatchMapping("/{invoiceId}")
    public Optional<InvoiceResponse> partialUpdateInvoice(@PathVariable Long invoiceId, @RequestBody InvoiceRequest invoiceRequest) {
        return invoiceService.partialUpdateInvoice(invoiceId, invoiceRequest);
    }

    @DeleteMapping("/{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
    }

    @PostMapping("/bulk")
    public List<InvoiceResponse> bulkCreateOrUpdateInvoices(@RequestBody List<InvoiceRequest> invoiceRequests) {
        return invoiceService.bulkCreateOrUpdateInvoices(invoiceRequests);
    }

    @GetMapping("/search")
    public List<InvoiceResponse> searchInvoices(@RequestParam String query) {
        return invoiceService.searchInvoices(query);
    }

    @PutMapping("/{invoiceId}/void")
    public Optional<InvoiceResponse> voidInvoice(@PathVariable Long invoiceId) {
        return invoiceService.voidInvoice(invoiceId);
    }
}