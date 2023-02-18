package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Patient;
import peaksoft.repository.PatientRepo;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Repository
@Transactional
public class PatientRepoImpl implements PatientRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired

    public PatientRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Patient save(Patient patient) {
        entityManager.persist(patient);
        return patient;
    }

    @Override
    public List<Patient> getAll() {
        return entityManager.createQuery("select p from Patient p", Patient.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(Patient.class, id));
    }

    @Override
    public Patient getById(Long id) {
        return entityManager.find(Patient.class, id);
    }

    @Override
    public void update(Long id, Patient newPatient) {
        Patient patient = entityManager.find(Patient.class, id);
        patient.setFirstName(newPatient.getFirstName());
        patient.setLastName(newPatient.getLastName());
        patient.setPhoneNumber(newPatient.getPhoneNumber());
        patient.setGender(newPatient.getGender());
        patient.setEmail(newPatient.getEmail());
        patient.setHospital(newPatient.getHospital());
        patient.setAppoitmentList(newPatient.getAppoitmentList());
    }
}
