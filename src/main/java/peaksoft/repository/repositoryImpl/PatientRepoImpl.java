package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.exceptions.MyException;
import peaksoft.model.Hospital;
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
    public List<Patient> getAll(Long id) {
        return entityManager.createQuery("select l from Patient l join l.hospital h where h.id=:id", Patient.class).setParameter("id",id).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(Patient.class,id));
    }

    @Override
    public Patient getById(Long id) {
        return entityManager.find(Patient.class, id);
    }

    @Override
    public void update(Long id, Patient newPatient) throws MyException {
        if(newPatient.getPhoneNumber().startsWith("+996") && newPatient.getPhoneNumber().chars().count()==13) {
            Patient patient = entityManager.find(Patient.class, id);
            patient.setFirstName(newPatient.getFirstName());
            patient.setLastName(newPatient.getLastName());
            patient.setPhoneNumber(newPatient.getPhoneNumber());
            patient.setGender(newPatient.getGender());
            patient.setEmail(newPatient.getEmail());
            patient.setAppoitmentList(newPatient.getAppoitmentList());
        }else {
            throw new MyException("Phone number should be starts with +996 and size == 13!");
        }
    }
}
