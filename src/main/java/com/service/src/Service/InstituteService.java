package com.service.src.Service;

import com.service.src.Entity.InstituteEntity;
import com.service.src.Exceptions.Institute.InstituteNotFoundException;
import com.service.src.Repository.InstituteRepository;
import com.service.src.Exceptions.Institute.InstituteAlreadyExistsException;
import com.service.src.Exceptions.Institute.InstituteServiceException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstituteService {

    private final InstituteRepository instituteRepo;

    public InstituteService(InstituteRepository instituteRepo){
        this.instituteRepo = instituteRepo;
    }

    public List<InstituteEntity> getAllInstitutesService(){
        try{
            return instituteRepo.findAll();
        } catch (Exception e) {
            System.err.println("An error occurred while fetching institutes: "+ e.getMessage());
            throw new InstituteServiceException("An error occurred while fetching all institutes. Please try again later.");
        }
    }

    public InstituteEntity postInstituteService(InstituteEntity institute){
        try{
            Optional<InstituteEntity> checkForInstituteWithName = instituteRepo.findByName(institute.getName());

            if (checkForInstituteWithName.isPresent()) {
                throw new InstituteAlreadyExistsException("Institute " + institute.getName() + " already exists.");
            }

            return instituteRepo.save(institute);
        } catch (DataAccessException dae) {
            throw new InstituteServiceException("An error has occurred while accessing the database.");
        }
        catch (Exception e) {
            throw new InstituteServiceException("An error occurred while creating institute. Please try again later.");
        }
    }

    @Transactional
    public void deleteInstituteService(UUID instituteId){
        try{
            InstituteEntity checkForInstituteWithId = instituteRepo.findById(instituteId)
                    .orElseThrow(() -> new InstituteNotFoundException("Institute with ID " + instituteId + " not found."));
            instituteRepo.deleteById(instituteId);
        } catch (DataAccessException dae) {
            throw new InstituteServiceException("An error has occurred while accessing the database.");
        } catch (InstituteNotFoundException e) {
            throw e; // If institute not found, rethrow the custom exception
        } catch (Exception e) {
            throw new InstituteServiceException("An error occurred while deleting this institute. Please try again later.");
        }
    }

    @Transactional
    public InstituteEntity updateInstituteService(UUID instituteId, InstituteEntity updatedInstituteData){
        try{
            Optional<InstituteEntity> checkForSpecificRecordById = instituteRepo.findById(instituteId);

            if (checkForSpecificRecordById.isEmpty()) {
                throw new InstituteNotFoundException("Institute with ID " + instituteId + " not found.");
            }

            InstituteEntity existingInstitute = checkForSpecificRecordById.get();

            existingInstitute.setName(updatedInstituteData.getName());
            existingInstitute.setShortname(updatedInstituteData.getShortname());
            existingInstitute.setCode(updatedInstituteData.getCode());
            existingInstitute.setDescription(updatedInstituteData.getDescription());

            return instituteRepo.save(existingInstitute);

        } catch (DataAccessException dae) {
            throw new InstituteServiceException("An error has occurred while accessing the database.");
        } catch (InstituteNotFoundException e) {
            throw e; // If institute not found, rethrow the custom exception
        } catch (Exception e) {
            throw new InstituteServiceException("An error occurred while updating this institute. Please try again later.");
        }
    }
}
