package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Patient;
import peaksoft.repository.HospitalRepo;
import peaksoft.repository.PatientRepo;
import peaksoft.service.PatientService;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;
    private final HospitalRepo hospitalRepo;

    @Autowired
    public PatientServiceImpl(PatientRepo patientRepo, HospitalRepo hospitalRepo) {
        this.patientRepo = patientRepo;
        this.hospitalRepo = hospitalRepo;
    }

    @Override
    public Patient save(Patient newpatient) {
        return patientRepo.save(newpatient);
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
        patientRepo.update(id, newPatient);
    }
}
