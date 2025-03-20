package com.crud.src.Repository;

import com.crud.src.Entity.UniversityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UniversityRepository extends JpaRepository<UniversityEntity, UUID> {
    Optional<UniversityEntity> findByName(String name);
}
