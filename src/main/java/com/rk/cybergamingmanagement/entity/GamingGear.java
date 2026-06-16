package com.rk.cybergamingmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gaming_gears")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GamingGear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "serial_code", nullable = false)
    private String serialCode;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GearType type;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
