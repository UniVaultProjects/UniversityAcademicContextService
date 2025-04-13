package com.service.src.Repository;

import com.service.src.Entity.InstituteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstituteRepository extends JpaRepository<InstituteEntity, UUID> {
    Optional<InstituteEntity> FindByNameAndCode(String name, String code);
}