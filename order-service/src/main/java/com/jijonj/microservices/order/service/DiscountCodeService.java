package com.jijonj.microservices.order.service;

import com.jijonj.microservices.order.dto.DiscountCodeRequest;
import com.jijonj.microservices.order.dto.DiscountCodeResponse;
import com.jijonj.microservices.order.model.DiscountCode;
import com.jijonj.microservices.order.repository.DiscountCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscountCodeService {

    private final DiscountCodeRepository discountCodeRepository;

    public List<DiscountCodeResponse> getAllDiscountCodes() {
        return discountCodeRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<DiscountCodeResponse> searchDiscountCodes(String query) {
        return discountCodeRepository.findByCodeContaining(query).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public Optional<DiscountCodeResponse> getDiscountCodeById(Long discountId) {
        return discountCodeRepository.findById(discountId).map(this::mapToResponse);
    }

    public DiscountCodeResponse createDiscountCode(DiscountCodeRequest discountCodeRequest) {
        DiscountCode discountCode = mapToEntity(discountCodeRequest);
        return mapToResponse(discountCodeRepository.save(discountCode));
    }

    public Optional<DiscountCodeResponse> updateDiscountCode(Long discountId, DiscountCodeRequest discountCodeRequest) {
        return discountCodeRepository.findById(discountId).map(discountCode -> {
            discountCode.setCode(discountCodeRequest.getCode());
            discountCode.setDiscountType(discountCodeRequest.getDiscountType());
            discountCode.setAmount(discountCodeRequest.getAmount());
            discountCode.setStartDate(discountCodeRequest.getStartDate());
            discountCode.setEndDate(discountCodeRequest.getEndDate());
            discountCode.setUsageLimit(discountCodeRequest.getUsageLimit());
            discountCode.setIsActive(discountCodeRequest.getIsActive());
            return mapToResponse(discountCodeRepository.save(discountCode));
        });
    }

    public Optional<DiscountCodeResponse> partialUpdateDiscountCode(Long discountId, DiscountCodeRequest discountCodeRequest) {
        return discountCodeRepository.findById(discountId).map(discountCode -> {
            if (discountCodeRequest.getCode() != null) {
                discountCode.setCode(discountCodeRequest.getCode());
            }
            if (discountCodeRequest.getDiscountType() != null) {
                discountCode.setDiscountType(discountCodeRequest.getDiscountType());
            }
            if (discountCodeRequest.getAmount() != null) {
                discountCode.setAmount(discountCodeRequest.getAmount());
            }
            if (discountCodeRequest.getStartDate() != null) {
                discountCode.setStartDate(discountCodeRequest.getStartDate());
            }
            if (discountCodeRequest.getEndDate() != null) {
                discountCode.setEndDate(discountCodeRequest.getEndDate());
            }
            if (discountCodeRequest.getUsageLimit() != null) {
                discountCode.setUsageLimit(discountCodeRequest.getUsageLimit());
            }
            if (discountCodeRequest.getIsActive() != null) {
                discountCode.setIsActive(discountCodeRequest.getIsActive());
            }
            return mapToResponse(discountCodeRepository.save(discountCode));
        });
    }

    public void deleteDiscountCode(Long discountId) {
        discountCodeRepository.deleteById(discountId);
    }

    public List<DiscountCodeResponse> bulkCreateOrUpdateDiscountCodes(List<DiscountCodeRequest> discountCodeRequests) {
        List<DiscountCode> discountCodes = discountCodeRequests.stream().map(this::mapToEntity).collect(Collectors.toList());
        return discountCodeRepository.saveAll(discountCodes).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public Optional<DiscountCodeResponse> activateDiscountCode(Long discountId, Boolean isActive) {
        return discountCodeRepository.findById(discountId).map(discountCode -> {
            discountCode.setIsActive(isActive);
            return mapToResponse(discountCodeRepository.save(discountCode));
        });
    }

    private DiscountCode mapToEntity(DiscountCodeRequest discountCodeRequest) {
        DiscountCode discountCode = new DiscountCode();
        discountCode.setCode(discountCodeRequest.getCode());
        discountCode.setDiscountType(discountCodeRequest.getDiscountType());
        discountCode.setAmount(discountCodeRequest.getAmount());
        discountCode.setStartDate(discountCodeRequest.getStartDate());
        discountCode.setEndDate(discountCodeRequest.getEndDate());
        discountCode.setUsageLimit(discountCodeRequest.getUsageLimit());
        discountCode.setIsActive(discountCodeRequest.getIsActive());
        return discountCode;
    }

    private DiscountCodeResponse mapToResponse(DiscountCode discountCode) {
        return new DiscountCodeResponse(discountCode.getId(), discountCode.getCode(), discountCode.getDiscountType(), discountCode.getAmount(), discountCode.getStartDate(), discountCode.getEndDate(), discountCode.getUsageLimit(), discountCode.getIsActive());
    }
}