package com.service.src.Service;

import com.service.src.Entity.InstituteEntity;
import com.service.src.Repository.InstituteRepository;
import com.service.src.Utils.Institute.InstituteAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstituteService {
    private final InstituteRepository instituteRepository;

    public InstituteService(InstituteRepository instituteRepository){
        this.instituteRepository = instituteRepository;
    }

    public List<InstituteEntity> getInstitutes(){

        return instituteRepository.findAll().stream()
                .map()

    }

    public InstituteEntity createInstitute(InstituteEntity instituteEntity){

        // Check if a university with the same name already exists
        Optional<InstituteEntity> existingInstitute = instituteRepository.findByName(instituteEntity.getName());

        if (existingInstitute.isPresent()) {
            throw new InstituteAlreadyExistsException("Institute " + instituteEntity.getName() + " already exists");
        }
        // If the university doesn't exist, save the new university
        return instituteRepository.save(instituteEntity);
    }

}
