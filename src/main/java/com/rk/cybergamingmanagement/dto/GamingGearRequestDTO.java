package com.rk.cybergamingmanagement.dto;

import com.rk.cybergamingmanagement.entity.GearType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GamingGearRequestDTO {

    @NotBlank(message = "Product name must not be blank")
    private String productName;

    @NotBlank(message = "Serial code must not be blank")
    private String serialCode;

    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Type must not be null")
    private GearType type;

    private Long brandId;
}
