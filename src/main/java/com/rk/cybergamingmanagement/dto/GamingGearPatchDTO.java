package com.rk.cybergamingmanagement.dto;

import com.rk.cybergamingmanagement.entity.GearType;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GamingGearPatchDTO {

    private String productName;

    private String serialCode;

    @Positive(message = "Price must be greater than 0")
    private Double price;

    private GearType type;

    private Long brandId;
}
