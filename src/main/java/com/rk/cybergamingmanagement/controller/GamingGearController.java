package com.rk.cybergamingmanagement.controller;

import com.rk.cybergamingmanagement.dto.GamingGearPatchDTO;
import com.rk.cybergamingmanagement.dto.GamingGearRequestDTO;
import com.rk.cybergamingmanagement.dto.GamingGearResponseDTO;
import com.rk.cybergamingmanagement.service.GamingGearService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gears")
@RequiredArgsConstructor
public class GamingGearController {

    private final GamingGearService gamingGearService;

    @PostMapping
    public ResponseEntity<GamingGearResponseDTO> createGear(@Valid @RequestBody GamingGearRequestDTO requestDTO) {
        GamingGearResponseDTO response = gamingGearService.createGear(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<GamingGearResponseDTO>> getAllGears(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<GamingGearResponseDTO> gears = gamingGearService.getAllGears(keyword, pageable);
        return ResponseEntity.ok(gears);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GamingGearResponseDTO> getGearById(@PathVariable Long id) {
        GamingGearResponseDTO response = gamingGearService.getGearById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GamingGearResponseDTO> updateGear(
            @PathVariable Long id,
            @Valid @RequestBody GamingGearRequestDTO requestDTO) {
        GamingGearResponseDTO response = gamingGearService.updateGear(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GamingGearResponseDTO> patchGear(
            @PathVariable Long id,
            @Valid @RequestBody GamingGearPatchDTO patchDTO) {
        GamingGearResponseDTO response = gamingGearService.patchGear(id, patchDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGear(@PathVariable Long id) {
        gamingGearService.deleteGear(id);
        return ResponseEntity.noContent().build();
    }
}
