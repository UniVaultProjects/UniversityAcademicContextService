package com.service.src.Service;

import com.service.src.Entity.InstituteEntity;
import com.service.src.Exceptions.Institute.InstituteNotFoundException;
import com.service.src.Repository.InstituteRepository;
import com.service.src.Exceptions.Institute.InstituteAlreadyExistsException;
import com.service.src.Exceptions.Institute.InstituteServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstituteService {
    private final InstituteRepository InstituteRepo;

    public InstituteService(InstituteRepository InstituteRepo){
        this.InstituteRepo = InstituteRepo;
    }

    public List<InstituteEntity> GetAllInstitutesService(){
        try{
            return InstituteRepo.findAll();
        } catch (Exception e) {
            System.err.println("An error occurred while fetching institutes: "+ e.getMessage());
            throw new InstituteServiceException("An error occurred while fetching all institutes. Please try again later.");
        }
    }

    public InstituteEntity CreateInstituteService(InstituteEntity Institute){
        try{
            Optional<InstituteEntity> CheckForSpecificRecordByIdAndName = InstituteRepo.findById(Institute.getId());

            if (CheckForSpecificRecordByIdAndName.isPresent()) {
                throw new InstituteAlreadyExistsException("Institute " + Institute.getName() + "&" +Institute.getCode() + " already exists.");
            }

            return InstituteRepo.save(Institute);
        } catch (DataAccessException dae) {
            throw new InstituteServiceException("An error has occurred while accessing database.");
        }
        catch (Exception e) {
            throw new InstituteServiceException("An error occurred while creating institute. Please try again later.");
        }
    }

    public void DeleteInstituteService(UUID InstituteId){
        try{
            Optional<InstituteEntity> CheckForSpecificRecordById = InstituteRepo.findById(InstituteId);
            if(CheckForSpecificRecordById.isEmpty()){
                throw new InstituteNotFoundException("Institute with ID"+ InstituteId + " not found. " );
            }
        } catch (DataAccessException dae) {
            throw new InstituteServiceException("An error has occurred while accessing database.");
        }
        catch (Exception e) {
            throw new InstituteServiceException("An error occurred while deleting this institute . Please try again later.");
        }
    }

    public InstituteEntity UpdateInstituteService(InstituteEntity UpdatedInstituteData, UUID InstituteId){
        try{
            Optional<InstituteEntity> CheckForSpecificRecordById = InstituteRepo.findById(InstituteId);

            if(CheckForSpecificRecordById.isEmpty()){
                throw new InstituteNotFoundException("Institute with ID"+ InstituteId + " not found. " );
            }
            InstituteEntity ExistingInstitute = CheckForSpecificRecordById.get();

            ExistingInstitute.setName(UpdatedInstituteData.getName());
            ExistingInstitute.setShortname(UpdatedInstituteData.getShortname());
            ExistingInstitute.setCode(UpdatedInstituteData.getCode());
            ExistingInstitute.setDescription(UpdatedInstituteData.getDescription());

            return InstituteRepo.save(ExistingInstitute);

        } catch (DataAccessException dae) {
            throw new InstituteServiceException("An error has occurred while accessing database.");
        }
        catch (Exception e) {
            throw new InstituteServiceException("An error occurred while updating this institute. Please try again later.");
        }
    }

}
