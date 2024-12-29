package com.jijonj.microservices.order.service;

import com.jijonj.microservices.order.dto.ShippingDetailsRequest;
import com.jijonj.microservices.order.dto.ShippingDetailsResponse;
import com.jijonj.microservices.order.model.ShippingDetails;
import com.jijonj.microservices.order.repository.ShippingDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShippingDetailsService {

    private final ShippingDetailsRepository shippingDetailsRepository;

    public List<ShippingDetailsResponse> getAllShippingDetails() {
        return shippingDetailsRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<ShippingDetailsResponse> getShippingDetailsByOrderId(Long orderId) {
        return shippingDetailsRepository.findByOrderId(orderId).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public Optional<ShippingDetailsResponse> getShippingDetailsById(Long shippingId) {
        return shippingDetailsRepository.findById(shippingId).map(this::mapToResponse);
    }

    public ShippingDetailsResponse createShippingDetails(ShippingDetailsRequest shippingDetailsRequest) {
        ShippingDetails shippingDetails = mapToEntity(shippingDetailsRequest);
        return mapToResponse(shippingDetailsRepository.save(shippingDetails));
    }

    public Optional<ShippingDetailsResponse> updateShippingDetails(Long shippingId, ShippingDetailsRequest shippingDetailsRequest) {
        return shippingDetailsRepository.findById(shippingId).map(shippingDetails -> {
            shippingDetails.setAddressLine1(shippingDetailsRequest.getAddressLine1());
            shippingDetails.setAddressLine2(shippingDetailsRequest.getAddressLine2());
            shippingDetails.setCity(shippingDetailsRequest.getCity());
            shippingDetails.setState(shippingDetailsRequest.getState());
            shippingDetails.setPostalCode(shippingDetailsRequest.getPostalCode());
            shippingDetails.setCountry(shippingDetailsRequest.getCountry());
            return mapToResponse(shippingDetailsRepository.save(shippingDetails));
        });
    }

    public Optional<ShippingDetailsResponse> partialUpdateShippingDetails(Long shippingId, ShippingDetailsRequest shippingDetailsRequest) {
        return shippingDetailsRepository.findById(shippingId).map(shippingDetails -> {
            if (shippingDetailsRequest.getAddressLine1() != null) {
                shippingDetails.setAddressLine1(shippingDetailsRequest.getAddressLine1());
            }
            if (shippingDetailsRequest.getAddressLine2() != null) {
                shippingDetails.setAddressLine2(shippingDetailsRequest.getAddressLine2());
            }
            if (shippingDetailsRequest.getCity() != null) {
                shippingDetails.setCity(shippingDetailsRequest.getCity());
            }
            if (shippingDetailsRequest.getState() != null) {
                shippingDetails.setState(shippingDetailsRequest.getState());
            }
            if (shippingDetailsRequest.getPostalCode() != null) {
                shippingDetails.setPostalCode(shippingDetailsRequest.getPostalCode());
            }
            if (shippingDetailsRequest.getCountry() != null) {
                shippingDetails.setCountry(shippingDetailsRequest.getCountry());
            }
            return mapToResponse(shippingDetailsRepository.save(shippingDetails));
        });
    }

    public void deleteShippingDetails(Long shippingId) {
        shippingDetailsRepository.deleteById(shippingId);
    }

    public List<ShippingDetailsResponse> bulkCreateOrUpdateShippingDetails(List<ShippingDetailsRequest> shippingDetailsRequests) {
        List<ShippingDetails> shippingDetailsList = shippingDetailsRequests.stream().map(this::mapToEntity).collect(Collectors.toList());
        return shippingDetailsRepository.saveAll(shippingDetailsList).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<ShippingDetailsResponse> searchShippingDetails(String query) {
        // Implement search logic here
        return shippingDetailsRepository.findAll().stream()
                .filter(shippingDetails -> shippingDetails.getCity().contains(query) ||
                        shippingDetails.getPostalCode().contains(query) ||
                        shippingDetails.getCountry().contains(query))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ShippingDetailsResponse replaceShippingDetails(Long orderId, ShippingDetailsRequest shippingDetailsRequest) {
        List<ShippingDetails> existingDetails = shippingDetailsRepository.findByOrderId(orderId);
        shippingDetailsRepository.deleteAll(existingDetails);
        shippingDetailsRequest.setOrderId(orderId);
        return createShippingDetails(shippingDetailsRequest);
    }

    private ShippingDetails mapToEntity(ShippingDetailsRequest shippingDetailsRequest) {
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setOrderId(shippingDetailsRequest.getOrderId());
        shippingDetails.setAddressLine1(shippingDetailsRequest.getAddressLine1());
        shippingDetails.setAddressLine2(shippingDetailsRequest.getAddressLine2());
        shippingDetails.setCity(shippingDetailsRequest.getCity());
        shippingDetails.setState(shippingDetailsRequest.getState());
        shippingDetails.setPostalCode(shippingDetailsRequest.getPostalCode());
        shippingDetails.setCountry(shippingDetailsRequest.getCountry());
        return shippingDetails;
    }

    private ShippingDetailsResponse mapToResponse(ShippingDetails shippingDetails) {
        return new ShippingDetailsResponse(shippingDetails.getId(), shippingDetails.getOrderId(), shippingDetails.getAddressLine1(), shippingDetails.getAddressLine2(), shippingDetails.getCity(), shippingDetails.getState(), shippingDetails.getPostalCode(), shippingDetails.getCountry());
    }
}