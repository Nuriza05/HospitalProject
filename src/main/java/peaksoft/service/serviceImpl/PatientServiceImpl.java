package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import peaksoft.model.Patient;
import peaksoft.repository.PatientRepo;
import peaksoft.repository.repositoryImpl.PatientRepoImpl;
import peaksoft.service.PatientService;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private PatientRepo patientRepo;
    @Override
    public Patient save(Patient patient) {
        return patientRepo.save(patient);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepo.getAll();
    }

    @Override
    public void deleteById(Long id) {
       patientRepo.deleteById(id);
    }

    @Override
    public Patient getById(Long id) {
        return patientRepo.getById(id);
    }

    @Override
    public void update(Long id, Patient newPatient) {
      patientRepo.update(id,newPatient);
    }
}
