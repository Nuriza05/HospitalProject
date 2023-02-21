package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Doctor;
import peaksoft.repository.DoctorRepo;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Repository
@Transactional
public class DoctorRepoImpl implements DoctorRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public DoctorRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Doctor save(Doctor doctor) {
        entityManager.persist(doctor);
        return doctor;
    }

    @Override
    public List<Doctor> getAll() {
        return entityManager.createQuery("select h from Doctor h", Doctor.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(Doctor.class, id));
    }

    @Override
    public Doctor getById(Long id) {
        return entityManager.find(Doctor.class, id);
    }

    @Override
    public void update(Long id, Doctor newDoctor) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        doctor.setFirstName(newDoctor.getFirstName());
        doctor.setLastName(newDoctor.getLastName());
        doctor.setPosition(newDoctor.getPosition());
        doctor.setEmail(newDoctor.getEmail());
        doctor.setDepartments(newDoctor.getDepartments());
        doctor.setHospital(newDoctor.getHospital());
    }
}
