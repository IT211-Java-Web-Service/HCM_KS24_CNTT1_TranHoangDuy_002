package com.rk.cybergamingmanagement.service;

import com.rk.cybergamingmanagement.dto.GamingGearPatchDTO;
import com.rk.cybergamingmanagement.dto.GamingGearRequestDTO;
import com.rk.cybergamingmanagement.dto.GamingGearResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GamingGearService {

    GamingGearResponseDTO createGear(GamingGearRequestDTO requestDTO);

    Page<GamingGearResponseDTO> getAllGears(String keyword, Pageable pageable);

    GamingGearResponseDTO getGearById(Long id);

    GamingGearResponseDTO updateGear(Long id, GamingGearRequestDTO requestDTO);

    GamingGearResponseDTO patchGear(Long id, GamingGearPatchDTO patchDTO);

    void deleteGear(Long id);
}
