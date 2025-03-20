package com.crud.src.Service;

import com.crud.src.Entity.UniversityEntity;
import com.crud.src.Repository.UniversityRepository;
import com.crud.src.Utils.UniversityAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UniversityService {
    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository){
        this.universityRepository = universityRepository;
    }

    public List<UniversityEntity> getUniversities(){
        return this.universityRepository.findAll();
    }

    public UniversityEntity createUniversity(UniversityEntity universityEntity){
        // Check if a university with the same name already exists
        Optional<UniversityEntity> existingUniversity = universityRepository.findByName(universityEntity.getName());

        if (existingUniversity.isPresent()) {
            throw new UniversityAlreadyExistsException("University with name " + universityEntity.getName() + " already exists");
        }
        // If the university doesn't exist, save the new university
        return universityRepository.save(universityEntity);
    }

    public boolean deleteUniversity(UUID id){
        Optional<UniversityEntity> university = universityRepository.findById(id);
        if (university.isPresent()) {
            universityRepository.deleteById(id);
            return true;  // Successfully deleted
        } else {
            return false;  // University not found
        }
    }
}
