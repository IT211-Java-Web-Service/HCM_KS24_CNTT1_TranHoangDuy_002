package com.rk.cybergamingmanagement.repository;

import com.rk.cybergamingmanagement.entity.GamingGear;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GamingGearRepository extends JpaRepository<GamingGear, Long> {

    Page<GamingGear> findByIsDeletedFalse(Pageable pageable);

    @Query("SELECT g FROM GamingGear g WHERE g.isDeleted = false AND " +
            "(LOWER(g.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(g.serialCode) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<GamingGear> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
