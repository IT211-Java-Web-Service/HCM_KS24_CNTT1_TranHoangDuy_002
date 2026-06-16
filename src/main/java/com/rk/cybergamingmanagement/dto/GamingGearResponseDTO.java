package com.rk.cybergamingmanagement.dto;

import com.rk.cybergamingmanagement.entity.GearType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GamingGearResponseDTO {

    private Long id;
    private String productName;
    private String serialCode;
    private Double price;
    private GearType type;
    private Boolean isDeleted;
    private String brandName;
}
