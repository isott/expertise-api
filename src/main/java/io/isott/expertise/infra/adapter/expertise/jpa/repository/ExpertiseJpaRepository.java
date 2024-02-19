package io.isott.expertise.infra.adapter.expertise.jpa.repository;

import io.isott.expertise.infra.adapter.expertise.jpa.entity.ExpertiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpertiseJpaRepository extends JpaRepository<ExpertiseEntity, Long> {
    @Query("SELECT e FROM expertise e WHERE e.carId = :carId ORDER BY e.createdAt DESC")
    ExpertiseEntity findLatestByCarId(@Param("carId") String carId);
}
