package com.rk.cybergamingmanagement.service.impl;

import com.rk.cybergamingmanagement.dto.GamingGearPatchDTO;
import com.rk.cybergamingmanagement.dto.GamingGearRequestDTO;
import com.rk.cybergamingmanagement.dto.GamingGearResponseDTO;
import com.rk.cybergamingmanagement.entity.Brand;
import com.rk.cybergamingmanagement.entity.GamingGear;
import com.rk.cybergamingmanagement.exception.ResourceNotFoundException;
import com.rk.cybergamingmanagement.repository.BrandRepository;
import com.rk.cybergamingmanagement.repository.GamingGearRepository;
import com.rk.cybergamingmanagement.service.GamingGearService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GamingGearServiceImpl implements GamingGearService {

    private final GamingGearRepository gamingGearRepository;
    private final BrandRepository brandRepository;

    @Override
    public GamingGearResponseDTO createGear(GamingGearRequestDTO requestDTO) {
        GamingGear gear = new GamingGear();
        gear.setProductName(requestDTO.getProductName());
        gear.setSerialCode(requestDTO.getSerialCode());
        gear.setPrice(requestDTO.getPrice());
        gear.setType(requestDTO.getType());
        gear.setIsDeleted(false);

        if (requestDTO.getBrandId() != null) {
            Brand brand = brandRepository.findById(requestDTO.getBrandId())
                    .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + requestDTO.getBrandId()));
            gear.setBrand(brand);
        }

        GamingGear saved = gamingGearRepository.save(gear);
        return mapToResponseDTO(saved);
    }

    @Override
    public Page<GamingGearResponseDTO> getAllGears(String keyword, Pageable pageable) {
        Page<GamingGear> gearPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            gearPage = gamingGearRepository.searchByKeyword(keyword, pageable);
        } else {
            gearPage = gamingGearRepository.findByIsDeletedFalse(pageable);
        }

        return gearPage.map(this::mapToResponseDTO);
    }

    @Override
    public GamingGearResponseDTO getGearById(Long id) {
        GamingGear gear = findGearOrThrow(id);
        return mapToResponseDTO(gear);
    }

    @Override
    public GamingGearResponseDTO updateGear(Long id, GamingGearRequestDTO requestDTO) {
        GamingGear gear = findGearOrThrow(id);

        gear.setProductName(requestDTO.getProductName());
        gear.setSerialCode(requestDTO.getSerialCode());
        gear.setPrice(requestDTO.getPrice());
        gear.setType(requestDTO.getType());

        if (requestDTO.getBrandId() != null) {
            Brand brand = brandRepository.findById(requestDTO.getBrandId())
                    .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + requestDTO.getBrandId()));
            gear.setBrand(brand);
        } else {
            gear.setBrand(null);
        }

        GamingGear updated = gamingGearRepository.save(gear);
        return mapToResponseDTO(updated);
    }

    @Override
    public GamingGearResponseDTO patchGear(Long id, GamingGearPatchDTO patchDTO) {
        GamingGear gear = findGearOrThrow(id);

        if (patchDTO.getProductName() != null) {
            gear.setProductName(patchDTO.getProductName());
        }
        if (patchDTO.getSerialCode() != null) {
            gear.setSerialCode(patchDTO.getSerialCode());
        }
        if (patchDTO.getPrice() != null) {
            gear.setPrice(patchDTO.getPrice());
        }
        if (patchDTO.getType() != null) {
            gear.setType(patchDTO.getType());
        }
        if (patchDTO.getBrandId() != null) {
            Brand brand = brandRepository.findById(patchDTO.getBrandId())
                    .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + patchDTO.getBrandId()));
            gear.setBrand(brand);
        }

        GamingGear updated = gamingGearRepository.save(gear);
        return mapToResponseDTO(updated);
    }

    @Override
    public void deleteGear(Long id) {
        GamingGear gear = findGearOrThrow(id);
        gear.setIsDeleted(true);
        gamingGearRepository.save(gear);
    }

    private GamingGear findGearOrThrow(Long id) {
        GamingGear gear = gamingGearRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GamingGear not found with id: " + id));
        if (Boolean.TRUE.equals(gear.getIsDeleted())) {
            throw new ResourceNotFoundException("GamingGear not found with id: " + id);
        }
        return gear;
    }

    private GamingGearResponseDTO mapToResponseDTO(GamingGear gear) {
        return GamingGearResponseDTO.builder()
                .id(gear.getId())
                .productName(gear.getProductName())
                .serialCode(gear.getSerialCode())
                .price(gear.getPrice())
                .type(gear.getType())
                .isDeleted(gear.getIsDeleted())
                .brandName(gear.getBrand() != null ? gear.getBrand().getName() : null)
                .build();
    }
}
